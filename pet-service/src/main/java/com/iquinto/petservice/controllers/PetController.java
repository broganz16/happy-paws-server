package com.iquinto.petservice.controllers;


import javax.validation.Valid;

import com.iquinto.petservice.client.UserClient;
import com.iquinto.petservice.services.PetService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iquinto.petservice.payload.request.CreatePetRequest;

import java.util.HashMap;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest")
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	private UserClient userClient;

	@GetMapping("/monitor")
	public ResponseEntity<?> monitor() {

		log.info("[c:PetController  m:monitor] PET SERVICE IS WORKING : ");

		HashMap hashMap = new HashMap<>();
		hashMap.put("pet-service", "PET SERVICE IS WORKING");
		hashMap.put("user-service", userClient.checkUserViaFeign());


		return ResponseEntity.ok(hashMap);
	}


	@GetMapping("/create")
	public ResponseEntity<?> createPet(@Valid @RequestBody CreatePetRequest loginRequest) {

		log.info("[c:PetController  m:authenticateUser] starts : " + loginRequest);

		return ResponseEntity.ok("dd");
	}


}
