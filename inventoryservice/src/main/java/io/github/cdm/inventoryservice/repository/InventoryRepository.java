package io.github.cdm.inventoryservice.repository;

import io.github.cdm.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByProductIdIn(List<String> skuCode);
    Optional<Inventory> findByProductIdContainsAllIgnoreCase(String skuCode);


    Optional<Inventory> findByProductId(String productId);
}
