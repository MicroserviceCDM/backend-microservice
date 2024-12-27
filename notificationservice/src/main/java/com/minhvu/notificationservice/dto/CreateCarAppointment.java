package com.minhvu.notificationservice.dto;

import lombok.Data;

@Data
public class CreateCarAppointment {
    private String carId;
    private String email;
    private String phone;
    private String date;
    private String time;
    private String note;
}
