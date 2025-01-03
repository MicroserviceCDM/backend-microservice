package io.github.cdm.orderservice.service;

import io.github.cdm.orderservice.dto.CreateOrderItemRequest;
import io.github.cdm.orderservice.dto.CreateOrderRequest;
import io.github.cdm.orderservice.dto.UpdateOrderRequest;
import io.github.cdm.orderservice.event.OrderPlaceEvent;
import io.github.cdm.orderservice.external.InventoryService;
import io.github.cdm.orderservice.model.Order;
import io.github.cdm.orderservice.model.OrderItem;
import io.github.cdm.orderservice.model.OrderItemPK;
import io.github.cdm.orderservice.repository.OrderItemRepository;
import io.github.cdm.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final InventoryService inventoryService;
    private final KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;

    @Override
    public List<Order> viewAll() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    @Override
    public Map<Month, BigDecimal> calculateMonthlyRevenue() {
        Map<Month, BigDecimal> monthlyRevenue = new TreeMap<>(Arrays.stream(Month.values())
                .collect(Collectors.toMap(Function.identity(), month -> BigDecimal.ZERO)));

        int currentYear = LocalDateTime.now().getYear();

        orderRepository.findAll().stream()
                .filter(order -> order.getOrderDate().getYear() == currentYear)
                .forEach(order -> {
                    Month month = order.getOrderDate().getMonth();
                    BigDecimal currentAmount = monthlyRevenue.get(month);
                    monthlyRevenue.put(month, currentAmount.add(order.getTotalAmount()));
                });

        return monthlyRevenue;
    }
    @Override
    public BigDecimal calculateAverageOrderValue() {
        List<Order> allOrders = orderRepository.findAll();
        BigDecimal totalAmount = allOrders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalAmount.divide(BigDecimal.valueOf(allOrders.size()), RoundingMode.HALF_UP);
    }

    @Override
    public Map<Month, Long> calculateOrdersPerMonth() {
        Map<Month, Long> ordersPerMonth = new TreeMap<>();

        orderRepository.findAll().forEach(order -> {
            Month month = order.getOrderDate().getMonth();
            ordersPerMonth.put(month, ordersPerMonth.getOrDefault(month, 0L) + 1);
        });

        return ordersPerMonth;
    }

    @Override
    public List<Order> findByShippingStatus(String shippingStatus) {
        return orderRepository.findByShippingStatus(shippingStatus);
    }

    @Override
    public BigDecimal calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Order> findByPaymentStatus(String shippingStatus) {
        return orderRepository.findByPaymentStatus(shippingStatus);
    }

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setEmail(createOrderRequest.getEmail());
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentStatus("Pending");
        order.setShippingStatus("Pending");
        order.setVoucherValue(createOrderRequest.getVoucherValue());
        order.setShippingValue(createOrderRequest.getShippingValue());
        order.setShippingAddress(createOrderRequest.getShippingAddress());
        order.setTotalAmount(createOrderRequest.getTotalAmount());

        orderRepository.save(order);


        for (CreateOrderItemRequest createOrderItemRequestList : createOrderRequest.getCreateOrderItemRequestList()) {
            inventoryService.reduceQuantity(createOrderItemRequestList.getProductId(), createOrderItemRequestList.getQuantity());

            OrderItemPK orderItemPK = OrderItemPK.builder()
                    .orderId(order.getId())
                    .productId(createOrderItemRequestList.getProductId())
                    .build();

            OrderItem orderItem = OrderItem.builder()
                    .id(orderItemPK)
                    .quantity(createOrderItemRequestList.getQuantity())
                    .pricePerUnit(createOrderItemRequestList.getPricePerUnit())
                    .size(createOrderItemRequestList.getSize())
                    .color(createOrderItemRequestList.getColor())
                    .voucherValue(createOrderItemRequestList.getVoucher())
                    .shippingValue(createOrderItemRequestList.getShipping())
                    .build();

            orderItemRepository.save(orderItem);
        }
        kafkaTemplate.send("order-topic", new OrderPlaceEvent(order.getId(), order.getEmail()));
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }


    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order updateOrder(UpdateOrderRequest order) {
        Order order1 = orderRepository.findById(order.getId()).orElse(null);
        if (order1 == null) throw new RuntimeException("Order not found");
        order1.setShippingStatus(String.valueOf(order.getShippingStatus()));
        order1.setPaymentStatus(String.valueOf(order.getPaymentStatus()));
        orderRepository.save(order1);
        return order1;
    }
}
