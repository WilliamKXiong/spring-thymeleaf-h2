package com.thymeleaf.model.chat;

import lombok.Data;

@Data
public class ChatRequest {

    private String uid;
    private String type;
    private String from;
    private String to;
    private String text;
}
