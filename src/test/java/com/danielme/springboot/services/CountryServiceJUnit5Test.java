package com.danielme.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
public class CountryServiceJUnit5Test {

    @Autowired
    CountryService countryService;

    @Test
    public void test() {
        assertEquals(3, countryService.findAll().size());
        // assertj
        assertThat(countryService.findAll()).hasSize(3);
    }

    @EnabledIf(expression = "#{'${spring.datasource.username}' != 'demo' }", loadContext = true)
    @Test
    public void testIf1() {
        fail("fail");
    }

    @EnabledIf("#{systemProperties['os.name'].toLowerCase().contains('linux')}")
    @Test
    public void testIf2() {
        fail("fail");
    }

}
