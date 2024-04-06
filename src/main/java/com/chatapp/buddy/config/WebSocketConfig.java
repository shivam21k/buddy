package com.chatapp.buddy.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
    @EnableWebSocketMessageBroker
    public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            //to register STOMP endpoint
            registry.addEndpoint("/ws-chatapp").setAllowedOrigins("http://localhost:8080/").withSockJS();
            //withSockJS enables that if the websocket connection is not established for some reason,
            //the connection will be downgraded to HTTP to continue the communication b/w client and server.
        }

        @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // for Message Broker
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic");

    }
}
