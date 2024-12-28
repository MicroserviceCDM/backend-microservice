package io.github.cdm.chatservice.repository;

import io.github.cdm.chatservice.model.Message;
import io.github.cdm.chatservice.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAll();
    List<Message> findBySenderNameAndReceiverName(String senderName, String receiverName);
    List<Message> findByReceiverNameAndStatusAllIgnoreCaseOrderByDateAsc(String receiverName, Status status);
    List<Message> findBySenderNameAndReceiverNameAndStatusAllIgnoreCaseOrderByDateAsc(String senderName, String receiverName, Status status);

}