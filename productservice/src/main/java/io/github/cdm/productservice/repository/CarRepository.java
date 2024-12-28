package io.github.cdm.productservice.repository;

import io.github.cdm.productservice.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findAllByModelIgnoreCase(String model);

    List<Car> findAllByTrimContainsIgnoreCase(String name);
}
