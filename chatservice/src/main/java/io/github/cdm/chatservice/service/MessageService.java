package io.github.cdm.chatservice.service;

import io.github.cdm.chatservice.model.Message;
import io.github.cdm.chatservice.model.Status;
import io.github.cdm.chatservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        message.setDate(LocalDateTime.now().toString());
        return messageRepository.save(message);
    }

    public List<Message> loadPublicMessages() {
        return messageRepository.findByReceiverNameAndStatusAllIgnoreCaseOrderByDateAsc("public", Status.MESSAGE);
    }

    public List<Message> loadPrivateMessages(String user1, String user2) {
        List<Message> messagesUser1ToUser2 = messageRepository.findBySenderNameAndReceiverNameAndStatusAllIgnoreCaseOrderByDateAsc(user1, user2, Status.MESSAGE);
        List<Message> messagesUser2ToUser1 = messageRepository.findBySenderNameAndReceiverNameAndStatusAllIgnoreCaseOrderByDateAsc(user2, user1, Status.MESSAGE);

        messagesUser1ToUser2.addAll(messagesUser2ToUser1);
        messagesUser1ToUser2.sort(Comparator.comparing(Message::getDate));

        return messagesUser1ToUser2;
    }


}