package com.danielme.springboot.controllers;

import com.danielme.springboot.services.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("countriesList", countryService.findAll());
        return "countriesList";
    }
}
