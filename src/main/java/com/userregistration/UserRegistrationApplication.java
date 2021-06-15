package com.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class UserRegistrationApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UserRegistrationApplication.class, args);
		log.info("User Registration App Started in {} Environment",
				  context.getEnvironment().getProperty("environment"));
		log.info("User Registration DB user is {} ",
				  context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
