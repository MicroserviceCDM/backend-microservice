package io.github.cdm.orderservice.service;


import io.github.cdm.orderservice.dto.CreateOrderRequest;
import io.github.cdm.orderservice.dto.UpdateOrderRequest;
import io.github.cdm.orderservice.model.Order;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> viewAll();
    BigDecimal calculateTotalRevenue();
    Map<Month, BigDecimal> calculateMonthlyRevenue();
    BigDecimal calculateAverageOrderValue();
    Map<Month, Long> calculateOrdersPerMonth();
    List<Order> findByShippingStatus(String shippingStatus);
    List<Order> findByPaymentStatus(String shippingStatus);

    void createOrder(CreateOrderRequest createOrderRequest);
    Order findById(String id);

    List<Order> findByEmail(String email);

    //    Order createOrderFromCartItems(List<Product> cartItems, User user);
    void saveOrder(Order order);
    Order updateOrder(UpdateOrderRequest order);
}
