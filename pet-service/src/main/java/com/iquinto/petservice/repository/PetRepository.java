package com.iquinto.petservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iquinto.petservice.models.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
