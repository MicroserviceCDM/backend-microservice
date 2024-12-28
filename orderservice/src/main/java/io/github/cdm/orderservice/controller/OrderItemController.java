package io.github.cdm.orderservice.controller;

import io.github.cdm.orderservice.service.OrderItemService;
import io.github.cdm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
