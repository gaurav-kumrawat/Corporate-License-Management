package com.psl.alp.userlicensemanagements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;

@Repository
public interface UserLicenseRepository extends JpaRepository<UserLicenseManagement,String>{

	List<UserLicenseManagement> findByUserId(long userId);
}
