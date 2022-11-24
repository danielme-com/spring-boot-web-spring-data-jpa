package com.danielme.springboot.services;


import com.danielme.springboot.entities.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
public class CountryServiceJUnit4Test {

    @Autowired
    CountryService countryService;

    @Test
    public void testFindAll() {
        List<Country> allCountries = countryService.findAll();

        assertThat(allCountries).hasSize(3);
    }

}
