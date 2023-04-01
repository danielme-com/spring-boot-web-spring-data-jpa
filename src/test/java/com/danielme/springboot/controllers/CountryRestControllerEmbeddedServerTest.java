package com.danielme.springboot.controllers;

import com.danielme.springboot.entities.Country;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static com.danielme.springboot.Dataset.SPAIN_ID;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-mysql.sql")
class CountryRestControllerEmbeddedServerTest {

    @LocalServerPort
    private Integer port;

    @Test
    void testGetSpain() {
        Country spain = given()
                .contentType(ContentType.JSON)
                .when()
                .baseUri("http://localhost:" + port)
                .get(CountryRestController.COUNTRIES_RESOURCE + "/{id}", SPAIN_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(Country.class);

        assertThat(spain.getId())
                .isEqualTo(SPAIN_ID);
    }

}
