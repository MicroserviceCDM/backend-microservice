package com.minhvu.inventoryservice.service;

import com.minhvu.inventoryservice.dto.InventoryRequest;
import com.minhvu.inventoryservice.dto.InventoryResponse;
import com.minhvu.inventoryservice.dto.ProductResponse;
import com.minhvu.inventoryservice.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
//    List<InventoryResponse> isInStock(List<String> skuCode);

    Page<InventoryResponse> findAll(Pageable pageable);
    String create(InventoryRequest inventory);
    Inventory update(Inventory inventory);
    String delete(Inventory inventory);
    Inventory findById(String id);
    Optional<Inventory> findByProductIdContainsAllIgnoreCase(String skuCode);
    List<ProductResponse> getProducts();

    void reduceQuantity(String productId, long quantity);

    List<Inventory> findAllInventory();
}
