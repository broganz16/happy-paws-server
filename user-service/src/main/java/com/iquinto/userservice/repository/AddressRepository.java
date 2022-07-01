package com.iquinto.userservice.repository;

import com.iquinto.userservice.models.Address;
import com.iquinto.userservice.models.ERole;
import com.iquinto.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    @Query("select ad from Address ad where lower(ad.city) like %:query% or lower(ad.postalCode) like %:query%" )
    List<Address> findAllByQuery(String query);
}
