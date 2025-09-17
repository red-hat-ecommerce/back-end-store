package com.redhat.ecommerce.store.service;

import com.redhat.ecommerce.store.model.Store;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <pre>
 *  com.redhat.ecommerce.store.service.StoreService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 15:44
 */
@RegisterForReflection
@ApplicationScoped
public class StoreService {

    private static final Logger LOG = LoggerFactory.getLogger(StoreService.class);

    public List<Store> getStores() {
        return getStoreList();
    }

    private List<Store> getStoreList() {
        LOG.debug("get store list");
        return List.of(
                new Store("jakarta", "Jakarta"),
                new Store("bandung", "Bandung"),
                new Store("medan", "Medan"),
                new Store("jogjakarta", "Jogjakarta")
        );
    }

}
