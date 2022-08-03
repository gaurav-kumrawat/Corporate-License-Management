package com.psl.alp.userlicensemanagements.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;
import com.psl.alp.userlicensemanagements.service.UserLicenseService;

@RestController
@RequestMapping("/api/v1")
public class userLicenseController {
	@Autowired
	UserLicenseService userlicenseService;

	@PostMapping("user/{user_id}/license/{licenseId}")
	public String assignUser(@PathVariable long user_id,@PathVariable long licenseId) {
		return userlicenseService.assignLicense(user_id, licenseId);
	}

	@GetMapping("user/{user_id}/license")
	public List<UserLicenseManagement> allUserLicense(@PathVariable long user_id) {
		return userlicenseService.getAllLicenses(user_id);
	}

	@DeleteMapping("user/{user_id}/license/{license_key}")
	public String deleteUserLicense(@PathVariable String license_key, @PathVariable long user_id) {
		return userlicenseService.deleteUserLicense(license_key);	
	}

}
