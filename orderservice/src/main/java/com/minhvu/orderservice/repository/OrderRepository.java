package com.minhvu.orderservice.repository;

import com.minhvu.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findById(String id);
    List<Order> findByEmail(String email);
    List<Order> findByShippingStatus(String shippingStatus);
    List<Order> findByPaymentStatus(String paymentStatus);
    List<Order> findAllByOrderByOrderDateDesc();
}

