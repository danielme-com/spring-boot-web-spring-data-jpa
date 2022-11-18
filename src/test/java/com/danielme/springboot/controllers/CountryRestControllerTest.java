package com.danielme.springboot.controllers;

import com.danielme.springboot.entities.Country;
import com.danielme.springboot.model.CountryRequest;
import com.danielme.springboot.repositories.CountryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static com.danielme.springboot.Dataset.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class CountryRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CountryRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectmapper;

    @Autowired
    CountryRepository countryRepository;

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
        mockMvc.perform(get(CountryRestController.COUNTRIES_RESOURCE + "/{id}/", SPAIN_ID))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(NAME_SPAIN)))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void testGetSpainAgainstFile() throws Exception {
        String spainResponseJson = mockMvc.perform(get(CountryRestController.COUNTRIES_RESOURCE + "/{id}/", SPAIN_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Resource resource = new ClassPathResource("/responses/spain.json");
        assertThat(resource.getFile())
                .hasContent(spainResponseJson);
    }

    @Test
    void testAddGermany() throws Exception {
        CountryRequest country = new CountryRequest("Germany", 79778000);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRIES_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info(response);
    }

    @Test
    void testNoNameCreateCountry() throws Exception {
        CountryRequest country = new CountryRequest(null, 1);

        String response = mockMvc
                .perform(post(CountryRestController.COUNTRIES_RESOURCE)
                        .content(objectmapper.writeValueAsString(country))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info(response);
    }

    @Test
    void testGetSpainRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        String response = when().get(CountryRestController.COUNTRIES_RESOURCE + "/{id}", SPAIN_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(NAME_SPAIN))
                .extract()
                .asString();

        logger.info(response);
    }

    @Test
    void testGetSpainWebTestClient(@Autowired WebTestClient client) {
        byte[] response = client.get().uri(CountryRestController.COUNTRIES_RESOURCE + "/{id}", SPAIN_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("name").isEqualTo(NAME_SPAIN)
                .returnResult()
                .getResponseBody();

        logger.info(new String(response, StandardCharsets.UTF_8));
    }

    @Test
    void testGetAllRestAssured() throws JsonProcessingException {
        RestAssuredMockMvc.mockMvc(mockMvc);

        List<Country> countries = when().get(CountryRestController.COUNTRIES_RESOURCE)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(countries)
                .extracting(Country::getName)
                .containsExactlyInAnyOrder(
                        NAME_COLOMBIA,
                        NAME_MEXICO,
                        NAME_SPAIN);

        logger.info(objectmapper.writeValueAsString(countries));
    }

    @Test
    void testUpdateRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        String newName = "new name";
        Integer newPopulation = 1000;
        CountryRequest countryRequest = new CountryRequest(newName, newPopulation);

        given()
                .header("Content-type", "application/json")
                .and()
                .body(countryRequest)
                .when()
                .put(CountryRestController.COUNTRIES_RESOURCE + "/" + SPAIN_ID)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        Optional<Country> country = countryRepository.findById((long) SPAIN_ID);
        assertThat(country).isNotEmpty();
        assertThat(country.get().getName()).isEqualTo(newName);
        assertThat(country.get().getPopulation()).isEqualTo(newPopulation);
    }

    @Test
    void testUpdateNotFoundEntityRestAssured() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        String newName = "new name";
        Integer newPopulation = 1000;
        CountryRequest countryRequest = new CountryRequest(newName, newPopulation);

        given()
                .header("Content-type", "application/json")
                .and()
                .body(countryRequest)
                .when()
                .put(CountryRestController.COUNTRIES_RESOURCE + "/" + NON_EXISTS_ID)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @ParameterizedTest
    @ValueSource(longs = {SPAIN_ID, NON_EXISTS_ID})
    void testDeleteRestAssured(Long id) {
        RestAssuredMockMvc.mockMvc(mockMvc);

        given()
                .when()
                .delete(CountryRestController.COUNTRIES_RESOURCE + "/" + id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        Optional<Country> country = countryRepository.findById((long) id);
        assertThat(country).isEmpty();
    }

}
