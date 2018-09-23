package com.danielme.springboot.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danielme.springboot.CustomProperties;
import com.danielme.springboot.repositories.CountryRepository;

@Controller
public class CountryController {

    private static final Logger logger = Logger.getLogger(CountryController.class);
    
	private final CountryRepository countryRepository;
	private final CustomProperties customProperties;
	
	public CountryController(CountryRepository countryRepository, CustomProperties customProperties) {
	    this.countryRepository = countryRepository;
	    this.customProperties = customProperties;
	}

	@RequestMapping("/")
	public String list(Model model) {
	    logger.info(customProperties.getValue());
		model.addAttribute("countriesList", countryRepository.findAll());
		return "countriesList";
	}
}
