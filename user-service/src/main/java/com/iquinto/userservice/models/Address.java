package com.iquinto.userservice.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String province;

    private String postalCode;


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}


