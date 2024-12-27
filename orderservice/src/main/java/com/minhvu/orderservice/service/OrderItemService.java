package com.minhvu.orderservice.service;


import com.minhvu.orderservice.dto.CreateOrderItemRequest;
import com.minhvu.orderservice.dto.CreateOrderRequest;
import com.minhvu.orderservice.model.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderItemService {

    Page<OrderItem> findByOrderId(String orderId, int page, int size);

    List<OrderItem> findAll();

//    OrderItem createOrderItem(CreateOrderItemRequest createOrderItemRequest);
}
