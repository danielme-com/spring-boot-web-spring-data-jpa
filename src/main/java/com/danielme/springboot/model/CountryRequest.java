package com.danielme.springboot.model;

import jakarta.validation.constraints.*;

public class CountryRequest {

    @NotEmpty
    @Size(max = 255)
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

    @Override
    public String toString() {
        return "CountryRequest{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
