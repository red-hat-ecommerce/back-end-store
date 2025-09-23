package com.redhat.ecommerce.store.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * <pre>
 *  com.redhat.ecommerce.store.model.Store
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 17 Sep 2025 15:45
 */
@Entity
@Table(name = "t_store")
@RegisterForReflection
public class Store extends PanacheEntityBase {

    @Id
    @Column(name = "store_id", length = 12)
    private String storeId;

    @Column(name = "store_name", length = 20)
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
