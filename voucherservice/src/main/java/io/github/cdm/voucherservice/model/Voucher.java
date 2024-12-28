package io.github.cdm.voucherservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vouchers")
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
