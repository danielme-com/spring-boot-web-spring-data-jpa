package com.danielme.springboot.controllers;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.model.CountryRequest;
import com.danielme.springboot.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(CountryRestController.COUNTRIES_RESOURCE)
public class CountryRestController {

    public static final String COUNTRIES_RESOURCE = "/api/countries";

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll() {
        return countryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Country> getById(@PathVariable Long id) {
        return countryService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid CountryRequest country) {
        Long id = countryService.create(country);
        return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid CountryRequest countryRequest) {
        boolean wasUpdated = countryService.update(id, countryRequest);
        return wasUpdated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        countryService.delete(id);
    }

}
