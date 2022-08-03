package com.psl.alp.userlicensemanagements;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication 
@EnableFeignClients("com.psl.alp.FeignClients")
@EnableEurekaClient
public class UserLicenseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLicenseManagementApplication.class, args);
	}
	
	@Bean
	public NewTopic topic() {
	    return TopicBuilder.name("users-licenses")
	            .build();
	}
}
