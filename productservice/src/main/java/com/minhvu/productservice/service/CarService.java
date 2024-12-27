package com.minhvu.productservice.service;

import com.minhvu.productservice.dto.CreateCarRequest;
import com.minhvu.productservice.dto.UpdateCarRequest;
import com.minhvu.productservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.math.BigDecimal;
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
