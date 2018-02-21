package com.danielme.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielme.springboot.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
