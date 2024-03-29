package com.chatapp.buddy.models;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
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
