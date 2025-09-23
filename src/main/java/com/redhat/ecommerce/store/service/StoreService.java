package com.redhat.ecommerce.store.service;

import com.redhat.ecommerce.store.model.Store;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

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
@Named("storeService")
@ApplicationScoped
@RegisterForReflection
public class StoreService {

    @Transactional
    public List<Store> getStores() {
        return Store.listAll(Sort.ascending("storeId"));
    }
}
