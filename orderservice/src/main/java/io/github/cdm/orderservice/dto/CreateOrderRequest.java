package io.github.cdm.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private String email;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private Integer voucherValue;
    private Integer shippingValue;
    private List<CreateOrderItemRequest> createOrderItemRequestList;

}