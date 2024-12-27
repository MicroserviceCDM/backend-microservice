package com.minhvu.orderservice.controller;

import com.minhvu.orderservice.dto.CreateOrderItemRequest;
import com.minhvu.orderservice.model.Order;
import com.minhvu.orderservice.model.OrderItem;
import com.minhvu.orderservice.service.OrderItemService;
import com.minhvu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

//    @PostMapping("/createItem")
//    public ResponseEntity<String> createOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest) {
//
//
//
//        orderItemService.createOrderItem(createOrderItemRequest);
//
//        return ResponseEntity.ok("Thành công!");
//    }
}
