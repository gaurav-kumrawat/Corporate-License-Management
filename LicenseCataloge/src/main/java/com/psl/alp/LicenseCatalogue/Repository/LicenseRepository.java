package com.psl.alp.LicenseCatalogue.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.LicenseCatalogue.Entity.License;


@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {

	
}
