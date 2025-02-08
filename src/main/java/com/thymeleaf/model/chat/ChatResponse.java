package com.thymeleaf.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponse {

    private String uid;
    private String type;
    private String from;
    private String to;
    private String text;
    private String time;

    public ChatResponse(String type, String from, String text, String time) {
        this.type = type;
        this.from = from;
        this.text = text;
        this.time = time;
    }

    public ChatResponse(String type, String from, String to, String text, String time) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.text = text;
        this.time = time;
    }
}
