package sami.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sami.pojo.User;
import sami.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public List<User> getUsers() {
		
		// The service layer will authenticate the user here if he is allowed 
		// to see all users data or not before requesting the db. only admin or CEO are allowed kind of..
		
		// Also after getting the list of users check if the SSN are masked before returning back to controller
		return dao.getUsers();
	}
	
	public User getUser(Integer userId) {		
		return dao.getUser(userId);
	}
	
	public User addUser(User userobj) {
		
		if(!validateUser(userobj)) {
			return null;
		}		
		
		return dao.addUser(userobj.getId(), userobj.getName(), "123@gmail.com", userobj.getPassword());
	}
	
	public Boolean deleteUser(Integer id) {
		
		if(!validateId(id)) {
			return false;
		}		
		return dao.deleteUser(id); 
	}
	
	private Boolean validateUser(User userobj) {
		return userobj != null;
	}
	
	private Boolean validateId(Integer id) {
		return id > 0;
	}
}
