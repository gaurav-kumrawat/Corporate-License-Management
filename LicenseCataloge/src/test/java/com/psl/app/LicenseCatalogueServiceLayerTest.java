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

import com.psl.alp.LicenseCatalogue.Entity.License;
import com.psl.alp.LicenseCatalogue.Repository.LicenseRepository;
import com.psl.alp.LicenseCatalogue.Service.LicenseService;

@SpringBootTest(classes= {LicenseCatalogueServiceLayerTest.class})
public class LicenseCatalogueServiceLayerTest {

	    @Mock
	    LicenseRepository licenseRepository;

	    @InjectMocks
	    LicenseService licenseService;

	    public List<License> license;

	    @Test
	    @Order(1)
	    public void testGetLicense() {

	        license=new ArrayList<>();
	        license.add(new License(1,"Standard Subscription","all services can access","Udemy"));

	        when(licenseRepository.findAll()).thenReturn(license);
	        assertEquals(1, licenseService.getLicense().size());
	    }

	    @Test
	    @Order(2)
	    public void testCreateLicense() {
	        License license=new License(2,"Standard Subscription","all services can access","Udemy");
	        when(licenseRepository.save(license)).thenReturn(license);
	        assertEquals(license,licenseService.createLicense(license));
	    }

	    @Test
	    @Order(3)
	    public void testDeleteLicense() {
	        long licenseId=3;

	        licenseService.deleteLicense(licenseId);
	        verify(licenseRepository,times(1)).deleteById(licenseId);
	    }
	    
	    
	    
	    @Test
	    @Order(4)
	    public void testUpdateLicense() {
	    	long LicenseId3 = 4;
	        License licenses1=new License(LicenseId3,"Mobile","two mobiles can access","Hostar");
	   
	       when(licenseRepository.getById(LicenseId3)).thenReturn(licenses1);
	       when(licenseRepository.save(licenses1)).thenReturn(licenses1);
	        
	        License res = licenseRepository.getById(LicenseId3);
	         assertEquals("Mobile",res.getName());
	         assertEquals("two mobiles can access",res.getDescription());
	        assertEquals("Hostar",res.getProductId());
	        
	        
	        
	    }
	    
	    
	    @Test
	    @Order(5)
	    public void testgetLicenenseById() {
	    	long licenseId2 = 5;
	    	boolean val;
	    	License license2 = new License(licenseId2,"Business Suscription","Organitions can acess","Udemy");
	    	
	    	when(licenseRepository.getById(licenseId2)).thenReturn(license2);
	        License res = licenseRepository.getById(licenseId2);
	        assertEquals(licenseId2,res.getLicenseId());
	    }

}
