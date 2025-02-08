package com.thymeleaf.service;

import com.thymeleaf.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShutdownListener {

    private static final Logger LOG = LoggerFactory.getLogger(ShutdownListener.class);

    private final ChatService chatService;

    public ShutdownListener(ChatService chatService) {
        this.chatService = chatService;
    }

    @EventListener
    public void onApplicationShutdown(ContextClosedEvent event) {
        LOG.info("ContextClosedEvent triggered: Application is shutting down.");
        LOG.info("Clearing users from chatrooms.");
        chatService.clearUsers();
    }
}
