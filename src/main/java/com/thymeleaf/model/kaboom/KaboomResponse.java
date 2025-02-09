package com.thymeleaf.model.kaboom;

import lombok.Data;

import java.util.List;

@Data
public class KaboomResponse {

    private String uid;
    private String type;
    private List<Player> players;
    private String message;
}
