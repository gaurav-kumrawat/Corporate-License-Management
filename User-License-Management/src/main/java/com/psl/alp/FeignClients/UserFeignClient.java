package com.psl.alp.FeignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-MANAGEMENT")
public interface UserFeignClient {

	@GetMapping("/api/v1/users/{user_id}")
	public boolean validateUser(@PathVariable long userId);

}