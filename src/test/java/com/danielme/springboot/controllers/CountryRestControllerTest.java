package com.danielme.springboot.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.danielme.springboot.entities.Country;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
@AutoConfigureMockMvc
public class CountryRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CountryRestControllerTest.class);
    
    private static final int SPAIN_ID = 2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Test
    public void testGetSpain() throws Exception {
        String response = mockMvc.perform(get(CountryRestController.COUNTRY_RESOURCE + "/{id}/", SPAIN_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name", is("Spain"))).andReturn().getResponse()
                .getContentAsString();

        logger.info("response: " + response);
    }

    @Test
    public void testAddGermany() throws Exception {
        Country country = new Country("Germany", 79778000);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRY_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value())).andReturn().getResponse()
                .getContentAsString();

        logger.info(response);
    }

    @Test
    public void testAddDuplicateCountry() throws Exception {
        Country country = new Country("Spain", 1);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRY_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value())).andReturn()
                .getResponse().getContentAsString();

        logger.info(response);
    }

    @Test
    public void testRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        
        when().get(CountryRestController.COUNTRY_RESOURCE + "/{id}/", SPAIN_ID)
        .then().statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Spain"));
    }

}
