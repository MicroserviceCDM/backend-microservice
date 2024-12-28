package io.github.cdm.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id;

    private Integer quantity;
    private BigDecimal pricePerUnit;
    private Integer voucherValue;
    private Integer shippingValue;
    private String size;
    private String color;
}

