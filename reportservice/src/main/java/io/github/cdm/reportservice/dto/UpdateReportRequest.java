package io.github.cdm.reportservice.dto;

import lombok.Data;

@Data
public class UpdateReportRequest {
    private String id;
    private String status;
}
