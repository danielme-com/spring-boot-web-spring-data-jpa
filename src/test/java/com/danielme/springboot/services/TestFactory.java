package com.danielme.springboot.services;

import com.danielme.springboot.repositories.CountryRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
class TestFactory {

    @Bean
    @Primary
    CountryRepository countryRepositoryMock() {
        CountryRepository mock = mock(CountryRepository.class);
        when(mock.findAll()).thenReturn(Collections.emptyList());
        return mock;
    }

}
