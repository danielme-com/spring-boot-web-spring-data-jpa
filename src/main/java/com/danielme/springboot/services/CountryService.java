package com.danielme.springboot.services;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.model.CountryRequest;
import com.danielme.springboot.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Long create(CountryRequest newCountry) {
        Country countryEntity = new Country(newCountry.getName(), newCountry.getPopulation());
        return countryRepository.save(countryEntity).getId();
    }

    @Transactional
    public boolean update(Long id, CountryRequest countryRequest) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            country.get().setName(countryRequest.getName());
            country.get().setPopulation(countryRequest.getPopulation());
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        }
    }

}
