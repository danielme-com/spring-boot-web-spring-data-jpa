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
        return countryRepository.findById(id)
                .map(country -> {
                    copy(countryRequest, country);
                    return true;
                })
                .orElse(false);
    }

    private void copy(CountryRequest countryRequest, Country country) {
        country.setName(countryRequest.getName());
        country.setPopulation(countryRequest.getPopulation());
    }

    public void delete(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        }
    }

}
