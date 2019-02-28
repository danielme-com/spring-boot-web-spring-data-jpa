package com.danielme.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danielme.springboot.CustomProperties;
import com.danielme.springboot.services.CountryService;

@Controller
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;
    private final CustomProperties customProperties;

    public CountryController(CountryService countryService,
            CustomProperties customProperties) {
        this.countryService = countryService;
        this.customProperties = customProperties;
    }

    @RequestMapping("/")
    public String list(Model model) {
        logger.info(customProperties.getValue());
        model.addAttribute("countriesList", countryService.findAll());
        return "countriesList";
    }
}
