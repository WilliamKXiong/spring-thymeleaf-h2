package com.thymeleaf.controller;

import com.thymeleaf.model.kaboom.KaboomRequest;
import com.thymeleaf.model.kaboom.KaboomResponse;
import com.thymeleaf.model.kaboom.Player;
import com.thymeleaf.service.KaboomService;
import com.thymeleaf.socket.SocketConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class KaboomRestController {

    private final KaboomService kaboomService;
    private List<Player> playerList = new ArrayList<>();
    private int playerCount = 0;

    public KaboomRestController(KaboomService kaboomService) {
        this.kaboomService = kaboomService;
    }

    @GetMapping("/api/kaboomSocket/nextPlayer")
    public String getNextPlayer() {
        return playerCount++ + "";
    }

    @MessageMapping(SocketConstant.ENDPOINT.KABOOM)
    @SendTo(SocketConstant.TOPIC.MESSAGES)
    public ResponseEntity<?> handleRequest(@RequestBody KaboomRequest kaboomRequest) {
        if (kaboomRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        String type = kaboomRequest.getType();
        if (StringUtils.isBlank(type)) {
            return ResponseEntity.badRequest().body("Request type is blank!");
        }

        Player player = kaboomRequest.getPlayer();
        KaboomResponse kaboomResponse = new KaboomResponse();
        switch (type) {
            case "connect":
                if (!playerList.stream()
                        .map(Player::getName)
                        .collect(Collectors.toList())
                        .contains(player.getName())) {

                    playerList.add(player);
                    playerCount = playerCount + 1;
                }
                kaboomResponse.setUid(kaboomRequest.getUid());
                kaboomResponse.setType("connected");
                kaboomResponse.setPlayers(playerList);
                kaboomResponse.setMessage(playerCount + "");
                return ResponseEntity.ok(kaboomResponse);
            case "disconnect":
                for (Player p : playerList) {
                    if (p.getName().equals(player.getName())) {
                        playerList.remove(p);
                        if (playerCount > 0) {
                            playerCount = playerCount - 1;
                        }
                    }
                }
                kaboomResponse.setUid(kaboomRequest.getUid());
                kaboomResponse.setType("disconnected");
                kaboomResponse.setPlayers(playerList);
                return ResponseEntity.ok(kaboomResponse);
            case "player":
                for (Player p : playerList) {
                    if (p.getName().equals(player.getName())) {
                        p.setPosX(player.getPosX());
                        p.setPosY(player.getPosY());
                        p.setScore(player.getScore());
                    }
                }
                kaboomResponse.setUid(kaboomRequest.getUid());
                kaboomResponse.setType("Server");
                kaboomResponse.setPlayers(playerList);
                return ResponseEntity.ok(kaboomResponse);
            default:
                return ResponseEntity.badRequest().body("Unrecognized request type!");
        }
    }
}
