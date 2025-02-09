package com.thymeleaf.socket;

public class SocketConstant {

    public static final String SIMPLE_BROKER = "/topic";

    // This is the endpoint the client sends to ie "/app" + ENDPOINT
    public static final String APPLICATION_DESTINATION_PREFIXES = "/app";

    public static final class TOPIC {
        public static final String MESSAGES = SIMPLE_BROKER + "/messages";
    }

    public static final class ENDPOINT {
        public static final String CHAT = "/chat";
        public static final String KABOOM = "/kaboomSocket";
        public static final String DISCONNECT = "/disconnect";
        public static final String ERROR = "/error";
    }
}
