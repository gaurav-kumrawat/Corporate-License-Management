package com.psl.alp.LicenseCatalogue.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.alp.LicenseCatalogue.Entity.License;
import com.psl.alp.LicenseCatalogue.Exception.NoLicensesExistException;
import com.psl.alp.LicenseCatalogue.Repository.LicenseRepository;

@Service
public class LicenseService {

    @Autowired
    LicenseRepository licenseRepository;


    public License createLicense(License license) {
        licenseRepository.save(license);
        return license;
    }


    public List<License> getLicense(){



        List<License> license = new ArrayList<License>();
        licenseRepository.findAll().forEach(license1->license.add(license1));

        if(license.isEmpty()) {
            throw new NoLicensesExistException("No Licenses found");
        }
        else {
            return license;
        }



    }

    public boolean getLicenseById(long licenseId) {
    	return licenseRepository.findById(licenseId).isPresent();
    }



    public  String deleteLicense(long licenseId) {

        if(getLicenseById(licenseId)) {
            licenseRepository.deleteById(licenseId);
            return "License "+licenseId+" deleted Successfully";
        }
        else {
            throw new NoLicensesExistException("LicenseId is Invalid");
        }

    }



    public License updateLicense(License license,long licenseId) {
    	if(getLicenseById(licenseId)) {
    		License getLicense = licenseRepository.findById(licenseId).get();

            getLicense.setName(license.getName());
            getLicense.setDescription(license.getDescription());
            getLicense.setProductId(license.getProductId());

            licenseRepository.save(getLicense);
            return getLicense;
        }
        else {
            throw new NoLicensesExistException("LicenseId is Invalid");
        }
    	
    }
	
	
	
}
