package com.psl.alp.ordermanagement.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDHexGenerator;

@Entity
@Table(name = "PurchaseLicenseStatus")
public class LicenseStatus {

	@Id
	@Column(name = "LicenseKey")
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String licenseKey;

	@Column(name = "Status")
	private String licenseKeyStatus;
	 

	private long licenseId;
	
	private long orderId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public LicenseStatus(long licenseId) {
		super();
		this.licenseId = licenseId;
		this.licenseKeyStatus = "NOT CONSUMED";
	}

	public LicenseStatus() {
		super();
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getLicenseKeyStatus() {
		return licenseKeyStatus;
	}

	public void setLicenseKeyStatus(String licenseKeyStatus) {
		this.licenseKeyStatus = licenseKeyStatus;
	}

	public long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}



}
