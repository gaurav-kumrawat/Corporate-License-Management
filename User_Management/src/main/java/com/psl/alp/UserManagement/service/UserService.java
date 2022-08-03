package com.psl.alp.UserManagement.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.psl.alp.UserManagement.Entity.User;
import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;
import com.psl.alp.UserManagement.repository.UserRepository;
import com.psl.alp.UserManagement.repository.UsersLicenseRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsersLicenseRepository userLicenseRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(long userId) {
		return userRepository.findById(userId).get();
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}
	
	public void updateLicense(UserLicenseManagement obj) {
		Optional<UserLicenseManagement> tmp = userLicenseRepository.findById(obj.getLicenseKey());
		if(tmp.isPresent()) {
			userLicenseRepository.deleteById(obj.getLicenseKey());
		}else {
			userLicenseRepository.save(obj);
		}
	}

	public User updateUser(long userId, Map<Object, Object> fields) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			fields.forEach((key,value)->{
				Field field=ReflectionUtils.findField(User.class,(String)key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, user.get(), value);
				
			});
			User updatedUser = userRepository.save(user.get());
			return updatedUser;
		}
		return null;
	}


	public boolean getUserById(long userId) {
		return userRepository.findById(userId).isPresent();
	}
	
}
