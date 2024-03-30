package com.chatapp.buddy.models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "sampleChatMessageData")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageModel {
    @Id
    private String id;

    private String chatUser;

    private String message;

    private Date messageTime;

    private MessageActionEnum messageAction;
}
