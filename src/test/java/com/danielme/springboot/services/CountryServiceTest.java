package com.danielme.springboot.services;

import com.danielme.springboot.entities.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.danielme.springboot.Dataset.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
class CountryServiceTest {

    @Autowired
    CountryService countryService;

    @Test
    void testFindAll() {
        List<Country> countries = countryService.findAll();

        assertEquals(3, countries.size());
        //assertj
        assertThat(countries)
                .extracting(Country::getName)
                .containsExactlyInAnyOrder(
                        NAME_COLOMBIA,
                        NAME_MEXICO,
                        NAME_SPAIN);
    }

}
