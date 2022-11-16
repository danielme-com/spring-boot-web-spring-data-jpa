package com.danielme.springboot.repository;

import com.danielme.springboot.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void testFindAll() {
        assertThat(countryRepository.findAll()).hasSize(3);
    }

}
