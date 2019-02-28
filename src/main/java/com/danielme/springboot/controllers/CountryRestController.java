package com.danielme.springboot.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.services.CountryService;

@RestController
@RequestMapping(CountryRestController.COUNTRY_RESOURCE)
public class CountryRestController {

    private static final Logger logger = LoggerFactory.getLogger(CountryRestController.class);
    
    public static final String COUNTRY_RESOURCE = "/api/country";

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Country> getById(@PathVariable("id") Long id) {
        Optional<Country> country = countryService.findById(id);
        if (country.isPresent()) {
            return new ResponseEntity<>(country.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addWithoutValidations(@RequestBody Country country) {
        try {
            Long id = countryService.insert(country);
            return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
