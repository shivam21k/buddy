package com.chatapp.buddy.service;
import com.chatapp.buddy.dto.ChatMessageDTO;
import com.chatapp.buddy.models.ChatMessageModel;
import com.chatapp.buddy.repository.ChatMessageRepository;
import com.chatapp.buddy.util.ChatMessageBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    private final ChatMessageBinder chatMessageBinder;

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageBinder chatMessageBinder, ChatMessageRepository chatMessageRepository) {
        this.chatMessageBinder = chatMessageBinder;
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessageDTO saveChatMessageToDB(ChatMessageDTO chatMessageDTO) {
        ChatMessageModel chatMessage = chatMessageBinder.bind(chatMessageDTO);
        chatMessageRepository.save(chatMessage);
        return chatMessageDTO;
    }
}