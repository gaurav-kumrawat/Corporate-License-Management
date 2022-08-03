package com.psl.alp.userlicensemanagements.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.psl.alp.FeignClients.LicenseFeignClient;
import com.psl.alp.FeignClients.OrderFeignClient;
import com.psl.alp.FeignClients.UserFeignClient;
import com.psl.alp.OrderProxy.LicenseStatusProxy;
import com.psl.alp.userlicensemanagement.response.LicenseStatusException;
import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;
import com.psl.alp.userlicensemanagements.repository.UserLicenseRepository;

@Service
public class UserLicenseService {

	@Autowired
	UserLicenseRepository userlicenseRepository;

	@Autowired
	OrderFeignClient orderFeignClient;

	@Autowired
	UserFeignClient userFeignClient;
	
	@Autowired
	LicenseFeignClient licenseFeignClient;
	
	@Autowired
	KafkaTemplate<String, UserLicenseManagement> kafkaTemplate;

	public String assignLicense(long userid, long licenseId) {
//		if(userFeignClient.validateUser(userid)==false) {
//			return "Invalid User-Id";
//		}
		if(licenseFeignClient.validateLicenseId(licenseId)==false) {
			return "Invalid License-Id";
		}
		List<LicenseStatusProxy> lstatus = new ArrayList<>();
		lstatus = orderFeignClient.getLicenseById(licenseId);
		for (LicenseStatusProxy lsp : lstatus) {
			if (lsp.getLicenseKeyStatus().equals("NOT CONSUMED")) {
				orderFeignClient.updateStatus(lsp.getLicenseKey());
				lsp.setLicenseKeyStatus("CONSUMED");
				UserLicenseManagement obj = new UserLicenseManagement(lsp.getLicenseKey(),licenseId, userid);
				userlicenseRepository.save(obj);
				
				// send userId & licensekey to kafka server to add the licensekey association
				Message<UserLicenseManagement> message = MessageBuilder.withPayload(obj)
						.setHeader(KafkaHeaders.TOPIC, "users-licenses").build();
				kafkaTemplate.send(message);
				return "license key \" "+lsp.getLicenseKey()+"\" associated to  user with user-Id : "+userid;
			}
		}

		return "No License key available"; 
	}

	public List<UserLicenseManagement> getAllLicenses(long userId) {
		return userlicenseRepository.findByUserId(userId);
	}

	public String deleteUserLicense(String license_key) {
		
		Optional<UserLicenseManagement> obj = userlicenseRepository.findById(license_key);
		
		if(obj.isPresent()){
			orderFeignClient.updateStatus(license_key);
			userlicenseRepository.deleteById(license_key);
			
			// send userId & licensekey to kafka server to add the user & licensekey association
			Message<UserLicenseManagement> message=MessageBuilder
	    			.withPayload(obj.get())
	    			.setHeader(KafkaHeaders.TOPIC,"users-licenses")
	    			.build();
	    	kafkaTemplate.send(message);
	    	return license_key +"deleted for the user ";
		}else {
			//return wrong license key
			throw new LicenseStatusException("licenseId is not valid");
		}
	}

}
