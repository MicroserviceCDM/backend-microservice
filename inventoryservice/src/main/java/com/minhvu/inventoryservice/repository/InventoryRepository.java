package com.minhvu.inventoryservice.repository;

import com.minhvu.inventoryservice.model.Inventory;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByProductIdIn(List<String> skuCode);
    Optional<Inventory> findByProductIdContainsAllIgnoreCase(String skuCode);


    Optional<Inventory> findByProductId(String productId);
}
