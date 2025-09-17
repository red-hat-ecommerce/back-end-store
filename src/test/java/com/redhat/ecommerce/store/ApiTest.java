package com.redhat.ecommerce.store;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

/**
 * <pre>
 *  com.redhat.ecommerce.store.ApiTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 16:16
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest {

    @Test
    @Order(1)
    @DisplayName("This test is to check if api get all stores is working as expected")
    public void testGetAllStores() {
        given()
                .log().all()
                .when()
                .   get("/api/store/")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", hasSize(4));
    }

}
