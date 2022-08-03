package com.psl.alp.UserManagement.kafkaConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;
import com.psl.alp.UserManagement.service.UserService;

@Component
public class KafkaConsumer {

	@Autowired
	private UserService userService;
	                          
    @KafkaListener(topics = "users-licenses", groupId = "group_id")
    public void consume(UserLicenseManagement message)
    {
    	userService.updateLicense(message);
    	System.out.println("message"+message);
    }
}