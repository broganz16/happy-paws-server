package com.iquinto.petservice.services;

import com.github.javafaker.Faker;
import com.iquinto.petservice.models.Category;
import com.iquinto.petservice.models.Pet;
import com.iquinto.petservice.repository.CategoryRepository;
import com.iquinto.petservice.repository.PetRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PetServiceImpl implements PetService {

    private  final CategoryRepository categoryRepository;

    private final PetRepository petRepository;

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void loadData() {
        Arrays.asList("Dog", "Cat", "Turtle", "Bird", "Lion", "Tiger").stream().forEach((c)-> {
            Category category = categoryRepository.save(new Category(c));
            for (int i = 0; i < 5; i++){
                Faker f = new Faker();
                Pet pet = new Pet(category, f.funnyName().name(), 5);
                pet.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");
                petRepository.save(pet);
            }

        });
        System.out.println("USERS IS ADDED : " + this.findAllCategories().size());
        System.out.println("USERS IS ADDED : " + this.findAllPets().size());
    }


}
