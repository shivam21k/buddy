package com.chatapp.buddy.controller;
import com.chatapp.buddy.dto.ChatMessageDTO;
import com.chatapp.buddy.service.ChatMessageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO chat(@Payload ChatMessageDTO chatMessage) {
        return chatMessageService.saveChatMessageToDB(chatMessage);
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessageDTO addUser(@Payload ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getChatUser());
        return chatMessage;
    }
}
