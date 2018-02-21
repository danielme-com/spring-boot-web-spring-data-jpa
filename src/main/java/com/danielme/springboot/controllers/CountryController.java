package com.danielme.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danielme.springboot.repositories.CountryRepository;

@Controller
public class CountryController {

	@Autowired
	CountryRepository countryRepository;

	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute("countriesList", countryRepository.findAll());
		return "countriesList";
	}
}
