package io.github.cdm.voucherservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CreateVoucherRequest {
    private String code;

    private BigDecimal discount;

    private LocalDate expirationDate;

    private String title;

    private String description;
}
