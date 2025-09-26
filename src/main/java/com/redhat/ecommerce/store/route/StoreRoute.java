package com.redhat.ecommerce.store.route;

import com.redhat.ecommerce.store.model.Store;
import com.redhat.ecommerce.store.service.StoreService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * <pre>
 *  com.redhat.ecommerce.store.route.StoreRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 15:43
 */
@ApplicationScoped
public class StoreRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/api/store")
                .get("/")
                    .produces("application/json")
                    .to("direct:get-stores")
                .post("/")
                    .consumes("application/json")
                    .produces("application/json")
                    .to("direct:create-store");

        from("direct:get-stores")
                .routeId("get-stores-api")
                    .log("calling get-stores")
                .bean("storeService", "getStores")
                .marshal().json(JsonLibrary.Jackson);

        from("direct:create-store")
                .routeId("create-store-api")
                    .log("calling create-store")
                .doTry()
                    .unmarshal().json(JsonLibrary.Jackson, Store.class)
                    .bean("storeService", "addStore")
                    .marshal().json(JsonLibrary.Jackson)
                .endDoTry()
                .doCatch(Exception.class)
                    .log("json is incorrectly set")
                    .setHeader("Content-Type", constant("application/json"))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                    .transform().simple("{\"error\": \"invalid structure or missing required fields\"}")
                .end();
    }

}
