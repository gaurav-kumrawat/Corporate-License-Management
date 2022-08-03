package com.psl.alp.userlicensemanagements.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userAssociatedLicense")
public class UserLicenseManagement {

	@Id
	private String licenseKey;
	
	@Column(name="licenseId")
	private long licenseId;
	
	
	@Column(name="userId")
	private long userId;
	
	
	public UserLicenseManagement() {
		super();
	}


	public UserLicenseManagement(String licenseKey, long licenseId, long userId) {
		super();
		this.licenseKey = licenseKey;
		this.licenseId = licenseId;
		this.userId = userId;
	}


	public String getLicenseKey() {
		return licenseKey;
	}


	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}


	public long getLicenseId() {
		return licenseId;
	}


	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


}
