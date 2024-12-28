package com.minhvu.chatservice.controller;

import com.minhvu.chatservice.model.Message;
import com.minhvu.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @GetMapping("/public-messages")
    public ResponseEntity<List<Message>> getPublicMessages() {
        List<Message> messages = messageService.loadPublicMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/private-messages/{senderName}/{receiverName}")
    public ResponseEntity<List<Message>> getPrivateMessages(@PathVariable String senderName, @PathVariable String receiverName) {
        List<Message> messages = messageService.loadPrivateMessages(senderName, receiverName);
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {
        messageService.saveMessage(message);
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message) {
        messageService.saveMessage(message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }
}
