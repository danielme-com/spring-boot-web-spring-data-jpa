package com.danielme.springboot.controllers;

import com.danielme.springboot.entities.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.danielme.springboot.Dataset.NAME_SPAIN;
import static com.danielme.springboot.Dataset.SPAIN_ID;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
@AutoConfigureMockMvc
class CountryRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CountryRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @TestConfiguration
    static class TestConfigurationApp {
        @Bean
        ObjectMapper objectMapperPrettyPrinting() {
            return JsonMapper.builder()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .addModule(new JavaTimeModule())
                    .build();
        }
    }

    @Test
    void testGetSpain() throws Exception {
        String response = mockMvc.perform(get(CountryRestController.COUNTRY_RESOURCE + "/{id}/", SPAIN_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name", is(NAME_SPAIN)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info("response: " + response);
    }

    @Test
    void testAddGermany() throws Exception {
        Country country = new Country("Germany", 79778000);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRY_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info(response);
    }

    @Test
    void testAddDuplicateCountry() throws Exception {
        Country country = new Country(NAME_SPAIN, SPAIN_ID);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRY_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info(response);
    }

    @Test
    void testGetSpainRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        String response = when().get(CountryRestController.COUNTRY_RESOURCE + "/{id}/", SPAIN_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(NAME_SPAIN))
                .extract()
                .asString();

        logger.info(response);
    }

}
