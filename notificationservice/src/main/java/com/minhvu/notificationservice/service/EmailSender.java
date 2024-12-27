package com.minhvu.notificationservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSender {
    private final EmailService emailService;

    public void sendPasswordEmail(String email, String password) {
        String to = email;
        String subject = "Your password has been reset, please use this password to login";
        String text = password;

        emailService.sendEmail(to, subject, text);
    }


    public void sendOrderConfirmationEmail(String email, String order) {
        String to = email;
        String subject = "Order confirmation";
        String text = order;

        emailService.sendEmail(to, subject, text);
    }

    public void createCarAppointment(String carId, String email, String date, String time, String note, String phone) {
        String to = "cdm2153@gmail.com";
        String subject = "Car appointment confirmation";
        String text = "Car appointment for user " + carId + ". \n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Note: " + note + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n";

        emailService.sendEmail(to, subject, text);
    }
}
