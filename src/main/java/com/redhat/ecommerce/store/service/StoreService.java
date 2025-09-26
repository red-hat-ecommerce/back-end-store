package com.redhat.ecommerce.store.service;

import com.redhat.ecommerce.store.model.Store;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *  com.redhat.ecommerce.store.service.StoreService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 15:44
 */
@Named("storeService")
@ApplicationScoped
@RegisterForReflection
public class StoreService {

    @Transactional
    public List<Store> getStores() {
        return Store.listAll(Sort.ascending("storeId"));
    }

    @Transactional
    public void deleteStores() {
        Store.deleteAll();
    }

    @Transactional
    public HashMap addStore(Store store) {
        store.persist();
        return new HashMap() {{
            put("storeId", store.getStoreId());
            put("status", "success");
        }};
    }
}
