package com.thymeleaf.controller;

import com.thymeleaf.model.chat.ChatRequest;
import com.thymeleaf.model.chat.ChatResponse;
import com.thymeleaf.service.ChatService;
import com.thymeleaf.socket.SocketConstant;
import com.thymeleaf.util.ChatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketRestController {

    private final ChatService chatService;

    public WebSocketRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping(SocketConstant.ENDPOINT.CHAT)
    @SendTo(SocketConstant.TOPIC.MESSAGES)
    public ResponseEntity<?> handleRequests(@RequestBody ChatRequest chatRequest) {
        if (chatRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        String type = chatRequest.getType();
        if (StringUtils.isBlank(type)) {
            return ResponseEntity.badRequest().body("Chat message type is blank!");
        }

        String from = chatRequest.getFrom();
        if (StringUtils.isBlank(from)) {
            return ResponseEntity.badRequest().body("Chat message from is blank!");
        }

        String uid = chatRequest.getUid();
        String to = chatRequest.getTo();

        ChatResponse chatResponse;
        switch (type) {
            case "connect":
                if (chatService.userExists("socket", from)) {
                    chatResponse = ChatUtils.createConnectErrorResponse(from);
                    chatResponse.setUid(uid);
                    return ResponseEntity.ok(chatResponse);
                }

                chatService.addUser("socket", from);
                chatResponse = ChatUtils.createConnectedResponse(from);
                chatResponse.setUid(uid);

                return ResponseEntity.ok(chatResponse);

            case "disconnect":
                chatService.removeUser("socket", from);
                chatResponse = ChatUtils.createDisconnectResponse(from);
                chatResponse.setUid(uid);

                return ResponseEntity.ok(ChatUtils.createDisconnectResponse(from));

            case "message":
                chatResponse = ChatUtils.createMessageResponse(
                        chatRequest.getFrom(),
                        chatRequest.getText());
                chatResponse.setUid(uid);

                return ResponseEntity.ok(chatResponse);

            case "rename":
                if (chatService.userExists("socket", from)) {
                    chatResponse = ChatUtils.createRenameErrorResponse(from);
                    chatResponse.setUid(uid);
                    return ResponseEntity.ok(chatResponse);
                }

                chatService.addUser("socket", from);
                chatResponse = ChatUtils.createRenameResponse(from, to);
                chatResponse.setUid(uid);

                return ResponseEntity.ok(chatResponse);

            default:
                return ResponseEntity.badRequest().body("Unrecognized chat message type!");
        }
    }
}
