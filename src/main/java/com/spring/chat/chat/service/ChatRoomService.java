package com.spring.chat.chat.service;

import com.spring.chat.chat.model.ChatRoom;
import com.spring.chat.chat.repository.ChatRoomRepository;
import com.spring.chat.chat.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

//                    ChatRoom senderRecipient = ChatRoom
//                            .builder()
//                            .chatId(chatId)
//                            .senderId(senderId)
//                            .recipientId(recipientId)
//                            .build();

                    ChatRoom senderRecipient = new ChatRoom(chatId, chatId, senderId, recipientId);
                    ChatRoom recipientSender = new ChatRoom(chatId, chatId, senderId, recipientId);

                    /*ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();*/
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}