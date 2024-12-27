package com.minhvu.productservice;


import com.minhvu.productservice.model.Car;
import com.minhvu.productservice.model.Energy;
import com.minhvu.productservice.model.Shop;
import com.minhvu.productservice.model.Status;
import com.minhvu.productservice.repository.CarRepository;
import com.minhvu.productservice.repository.EnergyRepository;
import com.minhvu.productservice.repository.ShopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ProductServiceApplication.class, args);
    }
}
