package sami.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sami.pojo.User;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate template;

	public UserDao() {
		System.out.println("UserDao.MySqlDatabase()");
	}
	
	private User convertMapToUserObject(Map<String,Object> mapObj ) {
		
		User u = new User();
		
		u.setId((Integer)mapObj.get("Id"));
		u.setName((String)mapObj.get("UserName"));
		u.setPassword((String)mapObj.get("passwd"));		
		u.setUserId(mapObj.get("Id").toString());
		
		return u;
	}
	
	public List<User> getUsers() {
		
		List<User> finalList = new ArrayList<User>();
		List<Map<String,Object>> userdata = template.queryForList("Select * from users");		
		ListIterator<Map<String,Object>> itr = userdata.listIterator();
		
		while(itr.hasNext()) {
			Map<String,Object> mapObj = (Map<String,Object>) itr.next();
			finalList.add(convertMapToUserObject(mapObj));
			
			System.out.println("Printing map object");
			System.out.println(mapObj);
		}		
		return finalList;
	}
	
	public User getUser(Integer userId) {
		Map<String,Object> mapObj = (Map<String,Object>) template.queryForMap("SELECT * from users WHERE Id =?", userId);
		return convertMapToUserObject(mapObj);
	}
	
	
	public User addUser(Integer userId, String userName, String email, String passwd) {
		Integer numberOfRowsInserted = template.update("INSERT INTO users (Id, UserName, email, passwd) values (?, ?, ?, ?)", userId, userName, email, passwd );
		if (numberOfRowsInserted > 0) {
			return getUser(userId);
		}
		System.out.println("Nof of rows inserted " + numberOfRowsInserted );
		return null;
	}	
	
	public Boolean deleteUser(Integer userId) {
		Integer numberOfRows = template.update("DELETE FROM users WHERE Id = ?", userId );
		System.out.println("Nof of rows deleted " + numberOfRows);
		return numberOfRows > 0;
	}
	

}
