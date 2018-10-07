package com.danielme.springboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.danielme.springboot.repositories.CountryRepository;

@SpringBootApplication
@EnableConfigurationProperties(CustomProperties.class)
public class DemoApp extends SpringBootServletInitializer// implements CommandLineRunner
{

    /*
     * private static final Logger logger = Logger.getLogger(DemoApp.class);
     * 
     * @Autowired CountryRepository countryRepository;
     */

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    /*
     * @Override 
     * public void run(String... arg0) throws Exception {
     * logger.info(countryRepository.count()); }
     */

}
