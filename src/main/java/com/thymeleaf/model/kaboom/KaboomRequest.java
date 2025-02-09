package com.thymeleaf.model.kaboom;

import lombok.Data;

@Data
public class KaboomRequest {

    private String uid;
    private String type;
    private Player player;
}
