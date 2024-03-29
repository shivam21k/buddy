package com.chatapp.buddy.repository;

import com.chatapp.buddy.models.ChatMessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessageModel, Long> {
}