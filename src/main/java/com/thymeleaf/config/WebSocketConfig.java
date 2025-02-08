package com.thymeleaf.config;

import com.thymeleaf.socket.SocketConstant;
import com.thymeleaf.socket.SocketEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(SocketConstant.SIMPLE_BROKER);
        registry.setApplicationDestinationPrefixes(SocketConstant.APPLICATION_DESTINATION_PREFIXES);
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        Arrays.stream(SocketEndpoint.values())
                .filter(SocketEndpoint::isEnabled)
                .forEach(socketEndpoint -> {
                    registry.addEndpoint(socketEndpoint.getPath()).withSockJS();
                });
    }
}
