package com.iquinto.userservice;


import com.iquinto.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
	UserService userService ;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("LOADING DATA ..");
		userService.loadData();
	}
}
