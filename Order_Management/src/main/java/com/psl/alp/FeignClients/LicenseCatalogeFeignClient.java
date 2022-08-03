package com.psl.alp.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LICENSECATALOGE")
public interface LicenseCatalogeFeignClient {

	@GetMapping("/api/v1/licenses/{licenseId}")
    public boolean validateLicenseId(@PathVariable long licenseId);
}
