package com.danielme.springboot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CountryRequest {

    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    @Max(2000000000)
    private Integer population;

    public CountryRequest() {
    }

    public CountryRequest(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
