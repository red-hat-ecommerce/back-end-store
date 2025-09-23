package com.redhat.ecommerce.store;

import com.redhat.ecommerce.store.model.Store;
import com.redhat.ecommerce.store.service.StoreService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
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
    EntityManager entityManager;

    @Inject
    StoreService storeService;

    @BeforeEach
    @Transactional
    void setup() {
        // Clear any existing data
        entityManager.createQuery("DELETE FROM Store").executeUpdate();

        // Insert 4 random test stores
        Store store1 = new Store("STORE001", "Downtown Store");
        Store store2 = new Store("STORE002", "Mall Store");
        Store store3 = new Store("STORE003", "Airport Store");
        Store store4 = new Store("STORE004", "Online Store");

        entityManager.persist(store1);
        entityManager.persist(store2);
        entityManager.persist(store3);
        entityManager.persist(store4);
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
                .   get("/api/store/")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("$", hasSize(4));
    }
}
