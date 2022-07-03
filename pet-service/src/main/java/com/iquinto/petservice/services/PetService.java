package com.iquinto.petservice.services;

import com.iquinto.petservice.models.Category;
import com.iquinto.petservice.models.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Category> findAllCategories();



    List<Pet> findAllPets();


    void loadData();



}
