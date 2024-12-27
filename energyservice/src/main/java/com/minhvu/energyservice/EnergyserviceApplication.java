package com.minhvu.energyservice;

import com.minhvu.energyservice.model.Energy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EnergyserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyserviceApplication.class, args);
    }
}
