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
import com.psl.alp.UserManagement.repository.UserRepository;
import com.psl.alp.UserManagement.service.UserService;

@SpringBootTest(classes= {ServiceMackitoTests.class})
public class ServiceMackitoTests {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	public List<User> available_users;
	
	@Test
	@Order(1)
	public void testGetAllUsers() {
		
		available_users=new ArrayList<>();
		available_users.add(new User("Gaurav","Kumrawat","gaurav@kumrawat.com"));
		
		when(userRepository.findAll()).thenReturn(available_users);
		assertEquals(1, userService.getAllUsers().size());
	}
	
	@Test
	@Order(2)
	public void testCreateUser() {
		User user=new User("Gaurav","Kumrawat","gaurav@kumrawat.com");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.createUser(user));
	}
	
	@Test
	@Order(3)
	public void testDeleteUser() {
		long userId=1L;

		userService.deleteUser(userId);
		verify(userRepository,times(1)).deleteById(userId);
	}
	
}
