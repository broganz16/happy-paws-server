package com.iquinto.petservice;


import com.iquinto.petservice.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PetServiceApplication {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
    PetService userService ;

	public static void main(String[] args) {
		SpringApplication.run(PetServiceApplication.class, args);
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("LOADING DATA ..");
		userService.loadData();
	}
}
