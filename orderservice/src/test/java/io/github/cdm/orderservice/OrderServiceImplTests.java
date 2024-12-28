package io.github.cdm.orderservice;

import io.github.cdm.orderservice.dto.CreateOrderRequest;
import io.github.cdm.orderservice.dto.CreateOrderItemRequest;
import io.github.cdm.orderservice.dto.UpdateOrderRequest;
import io.github.cdm.orderservice.event.OrderPlaceEvent;
import io.github.cdm.orderservice.external.InventoryService;
import io.github.cdm.orderservice.model.Order;
import io.github.cdm.orderservice.model.OrderItem;
import io.github.cdm.orderservice.model.Status;
import io.github.cdm.orderservice.repository.OrderItemRepository;
import io.github.cdm.orderservice.repository.OrderRepository;
import io.github.cdm.orderservice.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateTotalRevenueReturnsCorrectValue() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(
                new Order("1", BigDecimal.valueOf(100)),
                new Order("2", BigDecimal.valueOf(200)),
                new Order("3", BigDecimal.valueOf(300))
        ));

        BigDecimal totalRevenue = orderService.calculateTotalRevenue();

        assertEquals(BigDecimal.valueOf(600), totalRevenue);
    }

    @Test
    void createOrderSavesOrderAndOrderItems() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setEmail("test@test.com");
        createOrderRequest.setTotalAmount(BigDecimal.valueOf(100));
        createOrderRequest.setCreateOrderItemRequestList(List.of(
                new CreateOrderItemRequest("1", 1, BigDecimal.valueOf(100), "M", "Red", BigDecimal.ZERO, BigDecimal.ZERO)
        ));

        Order order = new Order();
        order.setId("1"); // manually set the ID
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.createOrder(createOrderRequest);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }

    @Test
    void findByEmailReturnsCorrectOrders() {
        when(orderRepository.findByEmail("test@test.com")).thenReturn(Arrays.asList(
                new Order("1", "test@test.com", LocalDateTime.now(), BigDecimal.valueOf(100), "Pending", "Pending", "UIT", 0, 10),
                new Order("2", "test@test.com", LocalDateTime.now(), BigDecimal.valueOf(200), "Pending", "Pending", "UIt", 0, 10)
        ));

        List<Order> orders = orderService.findByEmail("test@test.com");

        assertEquals(2, orders.size());
        assertEquals("1", orders.get(0).getId());
        assertEquals("2", orders.get(1).getId());
    }

    @Test
    void updateOrderUpdatesAndSavesOrder() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(new Order("1", "test@test.com", LocalDateTime.now(), BigDecimal.valueOf(100), "Pending", "Pending", "UIT", 0, 0)));

        UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest();
        updateOrderRequest.setId("658474385298c4204c559e99");
        updateOrderRequest.setShippingStatus(Status.APPROVED.toString());
        updateOrderRequest.setPaymentStatus(Status.APPROVED.toString());

        orderService.updateOrder(updateOrderRequest);

        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
