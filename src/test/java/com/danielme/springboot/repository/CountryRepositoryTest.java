package com.danielme.springboot.repository;

import com.danielme.springboot.repositories.CountryRepository;
import com.danielme.springboot.repositories.CustomAuditorAware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = ASSIGNABLE_TYPE,
        classes = {CustomAuditorAware.class}
))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void testFindAll() {
        assertThat(countryRepository.findAll()).hasSize(3);
    }

}
