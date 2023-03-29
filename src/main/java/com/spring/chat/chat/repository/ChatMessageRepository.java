package com.spring.chat.chat.repository;

import com.spring.chat.chat.model.ChatMessage;
import com.spring.chat.chat.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {//MongoRepository<ChatMessage, String>

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
