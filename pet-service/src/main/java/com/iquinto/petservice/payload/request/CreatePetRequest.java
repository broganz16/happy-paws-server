package com.iquinto.petservice.payload.request;

import com.iquinto.petservice.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
public class CreatePetRequest {

    private String name;

    private int age;

    private Long categoryId;

    private String description;

    private String username;


}
