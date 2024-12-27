package com.minhvu.orderservice.controller;

import com.minhvu.orderservice.dto.CreateOrderItemRequest;
import com.minhvu.orderservice.dto.CreateOrderRequest;
import com.minhvu.orderservice.dto.UpdateOrderRequest;
import com.minhvu.orderservice.model.Order;
import com.minhvu.orderservice.model.OrderItem;
import com.minhvu.orderservice.service.OrderItemService;
import com.minhvu.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> checkout(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok("Success!");
    }

    @GetMapping("/getTotalRevenue")
    public ResponseEntity<String> getRevenue() {
        return ResponseEntity.ok(orderService.calculateTotalRevenue().toString());
    }

    @GetMapping("/getMonthlyRevenue")
    public ResponseEntity<Map<Month, BigDecimal>> getMonthlyRevenue() {
        return ResponseEntity.ok(orderService.calculateMonthlyRevenue());
    }

    @GetMapping("/getAverageOrderValue")
    public ResponseEntity<String> getAverageOrderValue() {
        return ResponseEntity.ok(orderService.calculateAverageOrderValue().toString());
    }

    @GetMapping("/getOrdersPerMonth")
    public ResponseEntity<Map<Month, Long>> getOrdersPerMonth() {
        return ResponseEntity.ok(orderService.calculateOrdersPerMonth());
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.viewAll());
    }

    @GetMapping("/getOrderItemsByOrderId")
    public ResponseEntity<Page<OrderItem>> getOrderItemsByOrderId(@RequestParam("orderId") String orderId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(orderItemService.findByOrderId(orderId, page, pageSize));
    }

    @GetMapping("/getAllOrderItems")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/getOrderByUserId")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestParam("email") String email) {
        return ResponseEntity.ok(orderService.findByEmail(email));
    }

    @GetMapping("/countOrders")
    public ResponseEntity<Integer> countProducts() {
        return ResponseEntity.ok(orderService.viewAll().size());
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody UpdateOrderRequest order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok("Success!");
    }


//    @PostMapping("/createItem")
//    public ResponseEntity<String> createOrderItem(@RequestBody CreateOrderItemRequest createOrderItemRequest) {
//        orderItemService.createOrderItem(createOrderItemRequest);
//        return ResponseEntity.ok("Thành công!");
//    }

//    @GetMapping("/getOrders")
//    public ResponseEntity<List<Order>> findOrdersByUserId(@RequestParam("userId") int id,
//                                                   @RequestParam("shippingAddress") String address,
//                                                   @RequestParam("firstName") String firstName,
//                                                   @RequestParam("lastName") String lastName,
//                                                   @RequestParam("email") String email,
//                                                   @RequestParam("phoneNumber") String phone) {
//        User user = new User(id,email,firstName,lastName,phone,address);
//
//        List<Order> orders = orderService.getOrdersByUser(user);
//        return ResponseEntity.ok(orders);
//    }
}
