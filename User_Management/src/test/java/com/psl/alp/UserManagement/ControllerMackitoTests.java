package com.psl.alp.UserManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.alp.UserManagement.Entity.User;
import com.psl.alp.UserManagement.controller.UserController;
import com.psl.alp.UserManagement.service.UserService;

@SpringBootTest(classes= {ControllerMackitoTests.class})
public class ControllerMackitoTests {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	public List<User> available_users;
	
	@Test
	@Order(1)
	public void testListAllUsers() {
		
		available_users=new ArrayList<>();
		available_users.add(new User("Gaurav","Kumrawat","gaurav@kumrawat.com"));
		
		when(userService.getAllUsers()).thenReturn(available_users);
		assertEquals(1, userController.listAllUsers().size());
	}
	
	@Test
	@Order(2)
	public void testAddUser() {
		User user=new User("Gaurav","Kumrawat","gaurav@kumrawat.com");
		when(userService.createUser(user)).thenReturn(user);
		assertEquals(user,userController.addUser(user));
	}
	
	@Test
	@Order(3)
	public void testRemoveUser() {
		User user=new User("Gaurav","Kumrawat","gaurav@kumrawat.com");
		long id=1;
		when(userService.getUser(id)).thenReturn(user);
		assertEquals(user,userController.removeUser(id));
	}
	
}
