package com.iquinto.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String province;

    private String postalCode;

    private String description;

    public Address(String city, String postalCode, String province) {
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        setDescriptio(city, postalCode, province);
    }

    public Address() {
    }

    void setDescriptio(String city, String postalCode, String province){
        this.description = city + " " + postalCode + " " + province;
    }

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


