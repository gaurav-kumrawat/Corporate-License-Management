package com.psl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.psl.alp.LicenseCatalogue.Controller.LicenseController;
import com.psl.alp.LicenseCatalogue.Entity.License;
import com.psl.alp.LicenseCatalogue.Repository.LicenseRepository;
import com.psl.alp.LicenseCatalogue.Service.LicenseService;

@SpringBootTest(classes = LicenseCatalogueControllerLayerTest.class)
public class LicenseCatalogueControllerLayerTest {

	    @Mock
	    LicenseService licenseService;
	    
	    @InjectMocks
	    LicenseController licenseController;

	    public List<License> license;

	    @Test
	    @Order(1)
	    public void testGetLicense() {

	        license=new ArrayList<>();
	        license.add(new License(1,"Standard Subscription","all services can access","Udemy"));

	        when(licenseService.getLicense()).thenReturn(license);
	        assertEquals(1, licenseController.getLicense().size());
	    }

	    @Test
	    @Order(2)
	    public void testCreateLicense() {
	        License license=new License(2,"Standard Subscription","all services can access","Udemy");
	        when(licenseService.createLicense(license)).thenReturn(license);
	        assertEquals(license,licenseController.createLicense(license));
	    }

	    @Test
	    @Order(3)
	    public void testDeleteLicense() {
	        long licenseId= 3;

	        licenseController.deleteLiscense(licenseId);;
	        verify(licenseService,times(1)).deleteLicense(licenseId);
	    }
	    
	    
	    
	    @Test
	    @Order(4)
	    public void testUpdateLicense() {
	    	long LicenseId3 = 4;
	        License licenses1=new License(LicenseId3,"Mobile","two mobiles can access","Hostar");
	   
	      when(licenseService.updateLicense(licenses1, LicenseId3)).thenReturn(licenses1);    
	      assertEquals(licenses1,licenseController.updateLicense(licenses1, LicenseId3));
	  
	            
	    }
	    
	    @Test
	    @Order(5)
	    public void testgetLicenseById() {
	    	long licenseId2 = 5;
	    	License license2 = new License(licenseId2,"Business Suscription","Organitions can acess","Udemy");
	        
	    	when(licenseService.getLicenseById(licenseId2)).thenReturn(true);
	        assertEquals(true,licenseController.validateLicenseId(licenseId2));
	    }
}
