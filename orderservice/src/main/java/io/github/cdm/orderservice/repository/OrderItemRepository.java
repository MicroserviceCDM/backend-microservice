package io.github.cdm.orderservice.repository;

import io.github.cdm.orderservice.model.OrderItem;
import io.github.cdm.orderservice.model.OrderItemPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, OrderItemPK> {
    Page<OrderItem> findById_OrderIdAllIgnoreCase(String orderId, Pageable pageable);

    List<OrderItem> findById_OrderId(String orderId, Pageable pageable);

    // Additional custom query methods can be added here
}

