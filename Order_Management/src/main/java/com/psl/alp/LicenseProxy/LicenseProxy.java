package com.psl.alp.LicenseProxy;

public class LicenseProxy {

	private long licenseId;
	
	private String name;
	
	private String description;
	
	private String  productId;
	
	
	
	

	public LicenseProxy(long licenseId, String name, String description, String productId) {
		super();
		this.licenseId = licenseId;
		this.name = name;
		this.description = description;
		this.productId = productId;
	}



	public long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
