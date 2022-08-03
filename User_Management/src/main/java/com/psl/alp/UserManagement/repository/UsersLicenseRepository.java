package com.psl.alp.UserManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.userlicensemanagements.Entity.UserLicenseManagement;

@Repository
public interface UsersLicenseRepository extends JpaRepository<UserLicenseManagement,String> {

}
