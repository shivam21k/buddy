package com.chatapp.buddy.util;
import com.chatapp.buddy.dto.ChatMessageDTO;
import com.chatapp.buddy.models.ChatMessageModel;
import com.chatapp.buddy.models.MessageActionEnum;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ChatMessageBinder {

    public ChatMessageModel bind(ChatMessageDTO chatMessageDTO) {
        Date messageTime = Calendar.getInstance().getTime();
        setMessageTime(chatMessageDTO, messageTime);

        return ChatMessageModel.builder()
                .chatUser(chatMessageDTO.getChatUser())
                .message(chatMessageDTO.getMessage())
                .messageAction(Enum.valueOf(MessageActionEnum.class, chatMessageDTO.getMessageAction()))
                .messageTime(messageTime)
                .build();
    }

    private void setMessageTime(ChatMessageDTO chatMessageDTO, Date messageTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String formattedMessageTime = dateFormat.format(messageTime);
        chatMessageDTO.setMessageTime(formattedMessageTime);
    }
}