package com.thymeleaf.service;

import com.thymeleaf.entity.Chatroom;
import com.thymeleaf.repository.ChatroomRepo;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatService {

    private final ChatroomRepo chatroomRepo;

    public ChatService(ChatroomRepo chatroomRepo) {
        this.chatroomRepo = chatroomRepo;
    }

    public Chatroom findByRoom(String room) {
        return chatroomRepo.findChatUserByKey(room);
    }

    public boolean userExists(String room, String user) {
        List<String> users = getUsers(room);
        return users.contains(user);
    }

    @Transactional
    public Chatroom addUser(String room, String user) {
        Chatroom chatroom = findByRoom(room);

        List<String> users = getUsers(chatroom);
        users.add(user);


        String userString = String.join(",", users);
        chatroom.setUsers(userString);

        return chatroomRepo.save(chatroom);
    }

    @Transactional
    public Chatroom removeUser(String room, String user) {
        Chatroom chatroom = findByRoom(room);

        List<String> users = getUsers(chatroom);
        if (users.isEmpty()) {
            return null;
        }

        users.removeIf(user1 ->
                StringUtils.isNotBlank(user1) && user1.equals(user));

        String userString = String.join(",", users);
        chatroom.setUsers(userString);

        return chatroomRepo.save(chatroom);
    }

    public List<String> getUsers(String room) {
        Chatroom chatroom = findByRoom(room);
        return getUsers(chatroom);
    }

    public List<String> getUsers(Chatroom chatroom) {
        String usersString = chatroom.getUsers();

        if (StringUtils.isBlank(usersString)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(usersString.split(",")));
    }

    public void clearUsers() {
        List<Chatroom> chatroomList = chatroomRepo.findAll();
        chatroomList.forEach(chatroom -> {
            chatroom.setUsers("");
            chatroomRepo.save(chatroom);
        });
    }
}

