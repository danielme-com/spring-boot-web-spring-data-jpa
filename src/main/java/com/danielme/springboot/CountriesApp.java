package com.danielme.springboot;

import com.danielme.springboot.services.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CountriesApp extends SpringBootServletInitializer /*implements CommandLineRunner*/ {

    //private static final Logger logger = LoggerFactory.getLogger(CountriesApp.class);

    /*@Autowired
    CountryService countryService;*/

    public static void main(String[] args) {
        SpringApplication.run(CountriesApp.class, args);
    }

    /*@Override
    public void run(String... arg0) {
        int size = countryService.findAll().size();
        logger.info("countries: {}", size);
    }*/

}
