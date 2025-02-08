package com.thymeleaf.util;

import com.thymeleaf.model.chat.ChatResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUtils {

    public static ChatResponse createMessageResponse(String from,
                                                     String text) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("message",
                from,
                text,
                time);
    }

    public static ChatResponse createDisconnectResponse(String from) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("disconnect",
                "Server",
                from,
                String.format("%s disconnected.", from),
                time);
    }

    public static ChatResponse createConnectErrorResponse(String from) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("connect.error",
                "Server",
                from,
                "Username already taken.",
                time);
    }

    public static ChatResponse createConnectedResponse(String from) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("connect",
                "Server",
                from,
                String.format("%s connected.", from),
                time);
    }

    public static ChatResponse createRenameErrorResponse(String from) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("rename.error",
                "Server",
                from,
                "Username already taken.",
                time);
    }

    public static ChatResponse createRenameResponse(String from, String to) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatResponse("rename",
                "Server",
                from,
                String.format("%s renamed themself to %s", from, to),
                time);
    }
}
