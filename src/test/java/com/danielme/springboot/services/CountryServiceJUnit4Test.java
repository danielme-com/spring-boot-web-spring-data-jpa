package com.danielme.springboot.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertThat(countryService.findAll()).hasSize(3);
    }

}
