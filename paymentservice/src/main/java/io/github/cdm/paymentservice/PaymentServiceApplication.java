package io.github.cdm.paymentservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
