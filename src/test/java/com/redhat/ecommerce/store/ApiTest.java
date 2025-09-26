package com.redhat.ecommerce.store;

import com.redhat.ecommerce.store.model.Store;
import com.redhat.ecommerce.store.service.StoreService;
import io.quarkus.arc.Arc;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
@ActivateRequestContext
public class ApiTest {

    @Inject
    StoreService storeService;

    @BeforeAll
    @Transactional
    static void setup() {
        StoreService storeService = Arc.container().instance(StoreService.class).get();
        storeService.deleteStores();

        // Insert 4 random test stores
        Store store1 = new Store("STORE001", "Downtown Store");
        Store store2 = new Store("STORE002", "Mall Store");
        Store store3 = new Store("STORE003", "Airport Store");
        Store store4 = new Store("STORE004", "Online Store");

        storeService.addStore(store1);
        storeService.addStore(store2);
        storeService.addStore(store3);
        storeService.addStore(store4);
    }

    @Test
    @Order(1)
    @DisplayName("Test direct service call to get all stores")
    @Transactional
    public void testStoreService() {
        List<Store> stores = storeService.getStores();
        assertEquals(4, stores.size());
        System.out.println("[DEBUG_LOG] Found " + stores.size() + " stores in direct service test");
        for (Store store : stores) {
            System.out.println("[DEBUG_LOG] Store: " + store.getStoreId() + " - " + store.getStoreName());
        }
    }

    @Test
    @Order(2)
    @DisplayName("This test is to check if api get all stores is working as expected")
    public void testGetAllStores() {
        given()
                .log().all()
                .when()
                    .get("/api/store/")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", hasSize(4));
    }

    @Test
    @Order(3)
    @DisplayName("This test is to check if api create store is working as expected")
    public void testCreateNewStore() {
        given()
            .log().all()
            .contentType("application/json")
            .body("{\n" +
                    "  \"storeId\": \"STORE005\",\n" +
                    "  \"storeName\": \"Test Store\"\n" +
                    "}")
            .when()
                .post("/api/store/")
            .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(4)
    @DisplayName("This test is to check if api create store is working as expected when being hit with a wrong json format")
    public void testCreateAWrongNewStore() {
        given()
                .log().all()
                .contentType("application/json")
                .body("{\n" +
                        "  \"storeIdWrongParameter\": \"STORE006\",\n" +
                        "  \"storeWrongParameter\": \"Test Store 6\"\n" +
                        "}")
                .when()
                    .post("/api/store/")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("error", is("invalid structure or missing required fields"));
    }
}
