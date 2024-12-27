package com.minhvu.productservice.controller;

import com.minhvu.productservice.dto.*;
import com.minhvu.productservice.model.Car;
import com.minhvu.productservice.model.Energy;
import com.minhvu.productservice.model.Shop;
import com.minhvu.productservice.service.CarService;
import com.minhvu.productservice.service.EnergyService;
import com.minhvu.productservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final CarService carService;
    private final EnergyService energyService;
    private final ShopService shopService;

    // ///////////////
    // ///////////////
    // ///////////////
    //      CAR
    // ///////////////
    // ///////////////
    // ///////////////

    @GetMapping("/getAllCars")
    public ResponseEntity<Page<Car>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "model") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<Car> carPage = carService.getAllProducts(PageRequest.of(page, size, Sort.by(direction, sortBy)));
        return ResponseEntity.ok(carPage);
    }

    @GetMapping("/getCarsByNameContains")
    public List<Car> getCarsByNameContains(@RequestParam("name") String name) {
        return carService.findCarsByNameContains(name);
    }

    @GetMapping("/getCarById/{id}")
    public Car getCarById(@PathVariable String id) {
        return carService.getProductById(id);
    }

    @GetMapping("/getCarByModel/{model}")
    public List<Car> getProductByModel(@PathVariable String model) {
        return carService.findProductByModelIgnoreCase(model);
    }

    @PostMapping("/createCar")
    public ResponseEntity<Car> createProduct(@RequestBody CreateCarRequest createCarRequest) {
        try {
            Car car = carService.createProduct(createCarRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(car);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateCar")
    public ResponseEntity<Car> updateProduct(@RequestBody UpdateCarRequest updateCarRequest) {
        Car updatedProduct = carService.updateProduct(updateCarRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        carService.deleteProduct(id);
        return ResponseEntity.ok("Delete product successfully");
    }
    // ///////////////
    // ///////////////
    // ///////////////
    //     ENERGY
    // ///////////////
    // ///////////////
    // ///////////////

    @GetMapping("/getAllEnergies")
    public ResponseEntity<Page<Energy>> getAllEnergies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<Energy> energyPage = energyService.findAll(PageRequest.of(page, size, Sort.by(direction, sortBy)));
        return ResponseEntity.ok(energyPage);
    }

    @GetMapping("/getEnergyByNameContains")
    public List<Energy> getEnergyByNameContains(@RequestParam("name") String name) {
        return energyService.findEnergyByNameContains(name);
    }

    @GetMapping("/getEnergyById/{id}")
    public Energy getEnergyById(@PathVariable String id) {
        return energyService.getProductById(id);
    }

    @GetMapping("/getEnergyByNameOrderedByPriceDesc")
    public List<Energy> getEnergyByNameOrderedByPriceDesc(@RequestParam("name") String name,
                                                          @RequestParam("isAsc") boolean isAsc) {
        return energyService.findEnergyByNameOrderedByPriceDesc(name, isAsc);
    }

    @PostMapping("/createEnergy")
    public ResponseEntity<Energy> createEnergy(@RequestBody CreateEnergyRequest createEnergyRequest) throws IOException {
        Energy createdProduct = energyService.createProduct(createEnergyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/updateEnergy")
    public ResponseEntity<Energy> updateEnergy(@RequestBody UpdateEnergyRequest updateEnergyRequest) {
        Energy updatedProduct = energyService.updateProduct(updateEnergyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/deleteEnergy/{id}")
    public ResponseEntity<String> deleteEnergy(@PathVariable String id) {
        energyService.deleteProduct(id);
        return ResponseEntity.ok("Delete product successfully");
    }

    // ///////////////
    // ///////////////
    // ///////////////
    //      SHOP
    // ///////////////
    // ///////////////
    // ///////////////

    @GetMapping("/getAllShopWithoutPagination")
    public ResponseEntity<List<Shop>> getAllShopWithoutPagination() {
        List<Shop> shopList = shopService.findAll();
        return ResponseEntity.ok(shopList);
    }

    @GetMapping("/getAllShops")
    public ResponseEntity<Page<Shop>> getAllShops(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<Shop> shopPage = shopService.findAll(PageRequest.of(page, size, Sort.by(direction, sortBy)));
        return ResponseEntity.ok(shopPage);
    }

    @GetMapping("/getShopByNameContains")
    public List<Shop> getShopByNameContains(@RequestParam("name") String name) {
        return shopService.findShopByNameContains(name);
    }

    @GetMapping("/getShopById/{id}")
    public Shop getShopById(@PathVariable String id) {
        return shopService.getProductById(id);
    }
    @GetMapping("/getShopByType/{type}")
    public List<Shop> getShopByType(@PathVariable String type) {
        return shopService.findProductByTypeIgnoreCase(type);
    }

    @GetMapping("/getShopByNameOrderedByPriceDesc")
    public List<Shop> getShopByNameOrderedByPriceDesc(@RequestParam("name") String name,
                                                          @RequestParam("isAsc") boolean isAsc) {
        return shopService.findShopsByNameOrderedByPriceDesc(name, isAsc);
    }

    @PostMapping("/createShop")
    public ResponseEntity<Shop> createShop(@RequestBody CreateShopRequest createShopRequest) throws IOException {
        Shop createdProduct = shopService.createProduct(createShopRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/updateShop")
    public ResponseEntity<Shop> updateShop(@RequestBody UpdateShopRequest updateShopRequest) {
        Shop updatedProduct = shopService.updateProduct(updateShopRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/deleteShop/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable String id) {
        shopService.deleteProduct(id);
        return ResponseEntity.ok("Delete product successfully");
    }
}
