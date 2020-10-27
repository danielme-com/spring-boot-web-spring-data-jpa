package com.danielme.springboot.controllers;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.services.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CountryRestController.class)
public class CountryRestControllerOnlyWebTest {

    private static final Logger logger = LoggerFactory.getLogger(CountryRestControllerOnlyWebTest.class);

    private static final long SPAIN_ID = 2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CountryService countryService;

    @Test
    public void testGetSpain() throws Exception {
        Mockito.when(countryService.findById(SPAIN_ID)).thenReturn(Optional.of(new Country("Spain", 0)));
        String response = mockMvc.perform(get(CountryRestController.COUNTRY_RESOURCE + "/{id}/", SPAIN_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name", is("Spain"))).andReturn().getResponse()
                .getContentAsString();

        logger.info("response: " + response);
    }

}
