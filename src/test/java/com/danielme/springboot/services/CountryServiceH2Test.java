package com.danielme.springboot.services;

import com.danielme.springboot.Dataset;
import com.danielme.springboot.entities.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"})
@Sql("/test-h2.sql")
@AutoConfigureTestDatabase
class CountryServiceH2Test {

    @Autowired
    CountryService countryService;

    @Test
    void testFindAll() {
        List<Country> countries = countryService.findAll();

        assertThat(countries)
                .extracting("name")
                .containsExactlyInAnyOrder(
                        Dataset.NAME_MEXICO,
                        Dataset.NAME_SPAIN);
    }

}
