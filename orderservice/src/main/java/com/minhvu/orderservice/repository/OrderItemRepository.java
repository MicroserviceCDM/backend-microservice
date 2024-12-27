package com.minhvu.orderservice.repository;

import com.minhvu.orderservice.model.OrderItem;
import com.minhvu.orderservice.model.OrderItemPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, OrderItemPK> {
    Page<OrderItem> findById_OrderIdAllIgnoreCase(String orderId, Pageable pageable);

    List<OrderItem> findById_OrderId(String orderId, Pageable pageable);

    // Additional custom query methods can be added here
}

