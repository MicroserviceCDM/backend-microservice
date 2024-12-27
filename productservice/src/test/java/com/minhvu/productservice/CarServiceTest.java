package com.minhvu.productservice;

import com.minhvu.productservice.dto.CreateCarRequest;
import com.minhvu.productservice.dto.UpdateCarRequest;
import com.minhvu.productservice.model.Car;
import com.minhvu.productservice.repository.CarRepository;
import com.minhvu.productservice.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductsReturnsPageOfProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        when(carRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<Car> result = carService.getAllProducts(pageable);

        assertNotNull(result);
    }

    @Test
    public void getProductByIdReturnsProduct() {
        Car car = new Car();
        when(carRepository.findById(any())).thenReturn(Optional.of(car));

        Car result = carService.getProductById("1");

        assertEquals(car, result);
    }

    @Test
    public void createProductReturnsCreatedProduct() throws Exception {
        CreateCarRequest request = new CreateCarRequest();
        Car car = new Car();
        when(carRepository.save(any())).thenReturn(car);

        Car result = carService.createProduct(request);

        assertEquals(car, result);
    }

    @Test
    public void updateProductReturnsUpdatedProduct() {
        UpdateCarRequest request = new UpdateCarRequest();
        Car car = new Car();
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(carRepository.save(any())).thenReturn(car);

        Car result = carService.updateProduct(request);

        assertEquals(car, result);
    }

    @Test
    public void findProductByModelIgnoreCaseReturnsListOfProducts() {
        when(carRepository.findAllByModelIgnoreCase(any())).thenReturn(Collections.singletonList(new Car()));

        var result = carService.findProductByModelIgnoreCase("model");

        assertEquals(1, result.size());
    }

    // @Test
    // public void findCarsByNameContainsReturnsListOfCars() {
    //     when(carRepository.findAllByModelContainsIgnoreCase(any())).thenReturn(Collections.singletonList(new Car()));

    //     var result = carService.findCarsByNameContains("name");

    //     assertEquals(1, result.size());
    // }
}
