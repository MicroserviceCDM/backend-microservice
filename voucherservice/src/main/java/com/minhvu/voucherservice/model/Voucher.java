package com.minhvu.voucherservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    private String id;

    private String code;

    private BigDecimal discount;

    private LocalDate expirationDate;

    private String title;

    private String description;

    public Voucher(String code, BigDecimal discount, LocalDate expirationDate, String title, String description) {
        this.code = code;
        this.discount = discount;
        this.expirationDate = expirationDate;
        this.title = title;
        this.description = description;
    }
}
