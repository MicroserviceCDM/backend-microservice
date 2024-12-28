package io.github.cdm.productservice.service;

import io.github.cdm.productservice.dto.CreateCarRequest;
import io.github.cdm.productservice.dto.UpdateCarRequest;
import io.github.cdm.productservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CarService {
    Page<Car> getAllProducts(Pageable pageable);

    Car getProductById(String id);

    Car createProduct(CreateCarRequest createCarRequest) throws IOException;

    Car updateProduct(UpdateCarRequest updateCarRequest);
    List<Car> findProductByModelIgnoreCase(String category);

    void deleteProduct(String id);

    List<Car> findCarsByNameContains(String name);
}
