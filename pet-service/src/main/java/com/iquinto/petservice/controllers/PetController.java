package com.iquinto.petservice.controllers;


import javax.validation.Valid;

import com.iquinto.petservice.client.UserClient;
import com.iquinto.petservice.interceptor.JwtUtils;
import com.iquinto.petservice.models.Category;
import com.iquinto.petservice.models.Pet;
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

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/monitor")
	public ResponseEntity<?> monitor() {

		log.info("[c:PetController  m:monitor] PET SERVICE IS WORKING : ");

		HashMap hashMap = new HashMap<>();
		hashMap.put("pet-service", "PET SERVICE IS WORKING");
		hashMap.put("user-service", userClient.checkUserViaFeign());

		return ResponseEntity.ok(hashMap);
	}

	@GetMapping("/test-token")
	public ResponseEntity<?> testToken(@RequestHeader("Authorization") String token) {

		log.info("[c:PetController  m:testToken] PET SERVICE IS WORKING : " + token);
		String res = userClient.testauth();
		log.info("[c:PetController  m:testToken] USERNAME: " + res);

		return ResponseEntity.ok(res);
	}


	@GetMapping("/create")
	public ResponseEntity<?> createPet(@Valid @RequestBody CreatePetRequest createPetRequest) {

		log.info("[c:PetController  m:createPet] starts : " + createPetRequest);
		Category category = petService.findCategoryById(createPetRequest.getCategoryId());

		Pet pet = new Pet(category, createPetRequest.getName(), createPetRequest.getAge());
		return ResponseEntity.ok(petService.savePet(pet));
	}


}
