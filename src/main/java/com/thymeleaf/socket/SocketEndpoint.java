package com.thymeleaf.socket;

public enum SocketEndpoint {
    CHAT(SocketConstant.ENDPOINT.CHAT, true),
    DISCONNECT(SocketConstant.ENDPOINT.DISCONNECT, false),
    ERROR(SocketConstant.ENDPOINT.ERROR, false);

    private final String path;
    private final boolean enabled;

    SocketEndpoint(String path,
                   boolean enabled) {
        this.path = path;
        this.enabled = enabled;
    }

    public String getPath() {
        return this.path;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
