package com.psl.alp.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.psl.alp.OrderProxy.LicenseStatusProxy;

@FeignClient(name = "ORDER-MANAGEMENT")
public interface OrderFeignClient {

	@PutMapping("/api/v1/orders/license_keys/{licenseKey}")
	public LicenseStatusProxy updateStatus(@PathVariable String licenseKey);

	@GetMapping("/api/v1/orders/licenses/{licenseId}")
	public List<LicenseStatusProxy> getLicenseById(@PathVariable long licenseId);

}