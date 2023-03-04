package com.danielme.springboot.services;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.model.CountryRequest;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);

    Long create(CountryRequest newCountry);

    boolean update(Long id, CountryRequest countryRequest);

    default void copy(CountryRequest countryRequest, Country country) {
        country.setName(countryRequest.getName());
        country.setPopulation(countryRequest.getPopulation());
    }

    void delete(Long id);
}
