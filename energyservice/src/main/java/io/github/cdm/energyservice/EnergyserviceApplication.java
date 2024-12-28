package io.github.cdm.energyservice;

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
