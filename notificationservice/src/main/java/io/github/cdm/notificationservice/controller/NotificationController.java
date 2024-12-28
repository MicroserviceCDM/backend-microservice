package io.github.cdm.notificationservice.controller;

import io.github.cdm.notificationservice.dto.CreateCarAppointment;
import io.github.cdm.notificationservice.event.ChangePasswordEvent;
import io.github.cdm.notificationservice.event.OrderPlaceEvent;
import io.github.cdm.notificationservice.service.EmailSender;
import io.github.cdm.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@Slf4j
public class NotificationController {
    private final EmailService emailService;
    private final EmailSender emailSender;

    @PostMapping("/sendResetPasswordEmail")
    public ResponseEntity<String> sendResetPasswordEmail(@RequestParam String email, @RequestParam String password) {
        emailSender.sendPasswordEmail(email, password);
        return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
    }

    @PostMapping("/createCarAppointment")
    public ResponseEntity<String> createCarAppointment(@RequestBody CreateCarAppointment createCarAppointment) {
        emailSender.createCarAppointment(createCarAppointment.getCarId(),
                createCarAppointment.getEmail(),
                createCarAppointment.getDate(),
                createCarAppointment.getTime(),
                createCarAppointment.getNote(),
                createCarAppointment.getPhone());
        return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
    }

    @KafkaListener(topics = "order-topic", groupId = "notification-group-id")
    public void consume(OrderPlaceEvent message) {
        try {
            log.info("Email sent for order id: {}", message.getOrderId());
            emailSender.sendOrderConfirmationEmail(message.getEmail(), "Thank you for your order. Your order id is " + message.getOrderId());
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "change-password", groupId = "notification-group-id")
    public void consume(ChangePasswordEvent changePasswordEvent) {
        try {
            log.info("Email sent for email: {}", changePasswordEvent.getEmail());
            emailSender.sendPasswordEmail(changePasswordEvent.getEmail(), changePasswordEvent.getPassword());
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage());
        }
    }
}
