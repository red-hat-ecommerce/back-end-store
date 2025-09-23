package com.redhat.ecommerce.store.route;

import com.redhat.ecommerce.store.service.StoreService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
                    .to("direct:get-stores");

        from("direct:get-stores")
                .routeId("get-stores-api")
                    .log("calling get-stores")
                .bean("storeService", "getStores")
                .marshal().json(JsonLibrary.Jackson);
    }

}
