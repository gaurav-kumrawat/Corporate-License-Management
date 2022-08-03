package com.psl.alp.userlicensemanagement.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LicenseStatusException extends RuntimeException {

	private String message;
	
	public LicenseStatusException() {
	}

	public LicenseStatusException(String message) {
		super(message);
	}
     
}
