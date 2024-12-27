package com.minhvu.productservice.repository;

import com.minhvu.productservice.model.Energy;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnergyRepository extends MongoRepository<Energy, String> {
    List<Energy> findDistinctByNameAllIgnoreCaseOrderByPriceAsc(String name, Sort sort);

    List<Energy> findByNameContains(String name);
}
