package com.chatapp.buddy.service;

import com.chatapp.buddy.dto.ChatMessageDTO;
import com.chatapp.buddy.models.ChatMessageModel;
import com.chatapp.buddy.repository.ChatMessageRepository;
import com.chatapp.buddy.util.ChatMessageBinder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessageServiceTest {

    @Mock
    private ChatMessageBinder chatMessageBinder;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatMessageService chatMessageService;

    @Test
    public void shouldSaveChatMessageToDBAndReturn() {
        ChatMessageModel chatMessage = ChatMessageModel.builder().build();
        ChatMessageDTO chatMessageDTO = ChatMessageDTO.builder().build();

        when(chatMessageBinder.bind(chatMessageDTO)).thenReturn(chatMessage);

        chatMessageService.saveChatMessageToDB(chatMessageDTO);

        verify(chatMessageBinder).bind(chatMessageDTO);
        verify(chatMessageRepository).save(chatMessage);
    }
}
