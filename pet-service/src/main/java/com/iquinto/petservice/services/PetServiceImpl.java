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
    public void loadData() {

    }
}
