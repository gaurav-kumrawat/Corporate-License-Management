package com.psl.alp.UserManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.alp.UserManagement.Entity.User;
import com.psl.alp.UserManagement.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/users")
	public List<User> listAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/users/{user_id}")
	public User removeUser(@PathVariable long userId) {
		User user=userService.getUser(userId);
		userService.deleteUser(userId);
		return user;
	}
	
	@PatchMapping("/users/{user_id}")
	public User updateUser(@PathVariable long user_id,@RequestBody Map<Object,Object> fields) {
		return userService.updateUser(user_id,fields);
	}
	

	@GetMapping("/users/{user_id}")
	public boolean validateUser(@PathVariable long userId) {
		return userService.getUserById(userId);
	}
}
