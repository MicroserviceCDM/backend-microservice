package com.minhvu.notificationservice;


import com.minhvu.notificationservice.event.OrderPlaceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

//    @KafkaListener(topics = "order-topic", groupId = "notification-group-id")
//    public void consume(OrderPlaceEvent message) {
//        log.info("Notification sent for order id: {}", message.getOrderId());
//    }
}
