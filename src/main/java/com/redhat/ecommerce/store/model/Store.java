package com.redhat.ecommerce.store.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * <pre>
 *  com.redhat.ecommerce.store.model.Store
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 15:45
 */
@RegisterForReflection
public class Store {

    private String storeId;
    private String storeName;

    public Store() {
    }

    public Store(String storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
