package io.github.cdm.inventoryservice.service;

import io.github.cdm.inventoryservice.dto.InventoryRequest;
import io.github.cdm.inventoryservice.dto.InventoryResponse;
import io.github.cdm.inventoryservice.dto.ProductResponse;
import io.github.cdm.inventoryservice.model.Inventory;
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
