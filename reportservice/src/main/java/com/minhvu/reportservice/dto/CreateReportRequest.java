package com.minhvu.reportservice.dto;

import lombok.Data;

@Data
public class CreateReportRequest {
    private String title;
    private String content;
    private String userId;
    private String image;
    private String description;
    private String type;
}
