package io.github.cdm.productservice.repository;

import io.github.cdm.productservice.model.Shop;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShopRepository extends MongoRepository<Shop, String>{
    List<Shop> findDistinctByNameAllIgnoreCaseOrderByPriceAsc(String name, Sort sort);

    List<Shop> findDistinctByTypeAllIgnoreCase(String type);

    List<Shop> findByNameContains(String name);
}
