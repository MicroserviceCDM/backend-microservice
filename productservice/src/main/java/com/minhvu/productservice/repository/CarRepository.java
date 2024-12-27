package com.minhvu.productservice.repository;

import com.minhvu.productservice.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findAllByModelIgnoreCase(String model);

    List<Car> findAllByTrimContainsIgnoreCase(String name);
}
