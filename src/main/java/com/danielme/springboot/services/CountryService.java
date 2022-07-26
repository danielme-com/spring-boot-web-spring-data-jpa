package com.danielme.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.repositories.CountryRepository;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }
    
    public Long create(Country country) {
        country.setId(null);
        return countryRepository.save(country).getId();
    }
}
