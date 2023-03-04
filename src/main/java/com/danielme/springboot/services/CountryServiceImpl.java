package com.danielme.springboot.services;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.model.CountryRequest;
import com.danielme.springboot.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {


    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Long create(CountryRequest newCountry) {
        Country countryEntity = new Country(newCountry.getName(), newCountry.getPopulation());
        return countryRepository.save(countryEntity).getId();
    }

    @Override
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

    @Override
    public void delete(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        }
    }

}
