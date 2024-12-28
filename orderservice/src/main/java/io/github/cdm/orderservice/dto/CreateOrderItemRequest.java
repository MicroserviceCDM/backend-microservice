package io.github.cdm.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderItemRequest {
    private String productId;
    private int quantity;
    private BigDecimal pricePerUnit;
    private String size;
    private String color;
    private int voucher;
    private int shipping;

    public CreateOrderItemRequest(String number, int i, BigDecimal bigDecimal, String m, String red, BigDecimal zero, BigDecimal zero1) {
        this.productId = number;
        this.quantity = i;
        this.pricePerUnit = bigDecimal;
        this.size = m;
        this.color = red;
        this.voucher = 0;
        this.shipping = 0;
    }
}