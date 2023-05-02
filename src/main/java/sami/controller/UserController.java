package sami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sami.pojo.User;
import sami.service.UserService;

@RestController
@RequestMapping("dbOp")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(path="/Users")
	public List<User> getUsers() {
		return service.getUsers();
	}
	
	@GetMapping(path="/Users/{userId}")
	public User getUser(@PathVariable String userId) {
		Integer id = Integer.parseInt(userId); 
		User userobj = service.getUser(id);
		System.out.println(userobj);
		return userobj;
	}
	
	@PostMapping (path = "/users", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 
	public User addUser(@RequestBody User userobj) {
		return service.addUser(userobj);		
	}
	
	@DeleteMapping(path="/Users/{id}")
	public Boolean deleteUser(@PathVariable String id) {
		Integer userId = Integer.parseInt(id); 
		return service.deleteUser(userId);		
	}
	
	@PutMapping(path = "/users/{id}")
	public User UpdateUser(@RequestBody User userObj, @PathVariable Integer id) {
		return service.addUser(userObj);
	}
}
