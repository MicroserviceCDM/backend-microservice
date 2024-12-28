package io.github.cdm.orderservice.service;


import io.github.cdm.orderservice.model.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderItemService {

    Page<OrderItem> findByOrderId(String orderId, int page, int size);

    List<OrderItem> findAll();

//    OrderItem createOrderItem(CreateOrderItemRequest createOrderItemRequest);
}
