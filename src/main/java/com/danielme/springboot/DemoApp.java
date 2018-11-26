package com.danielme.springboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.danielme.springboot.services.CountryService;

@SpringBootApplication
@EnableConfigurationProperties(CustomProperties.class)
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
public class DemoApp extends SpringBootServletInitializer// implements CommandLineRunner
{

    // private static final Logger logger = Logger.getLogger(DemoApp.class);

    // @Autowired
    // CountryService countryService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    /*
     * @Override public void run(String... arg0) throws Exception {
     * logger.info(countryService.findAll().size()); }
     */

}
