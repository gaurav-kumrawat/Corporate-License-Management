package com.psl.alp.ordermanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.ordermanagement.Entity.LicenseStatus;

@Repository
public interface LicenseStatusRepository extends JpaRepository<LicenseStatus, String> {

	List<LicenseStatus> findByLicenseId(long license_id);

	LicenseStatus findByLicenseKey(String licenseKey);

	List<LicenseStatus> findByOrderId(long orderId);

	



}
