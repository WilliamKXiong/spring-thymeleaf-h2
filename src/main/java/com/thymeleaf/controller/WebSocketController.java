package com.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/socket")
public class WebSocketController {

    @GetMapping
    public String chatSocket() {
        return "/socket/index";
    }
}
