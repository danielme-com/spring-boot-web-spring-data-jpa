package com.danielme.springboot.services;

import com.danielme.springboot.repositories.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
public class CountryMockServiceTest {

    @MockBean
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Test
    public void test() {
        when(countryRepository.findAll()).thenReturn(new LinkedList<>());

        assertThat(countryService.findAll().isEmpty());
        verify(countryRepository, times(1)).findAll();
    }

}
