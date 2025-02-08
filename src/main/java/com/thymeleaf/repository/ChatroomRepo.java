package com.thymeleaf.repository;

import com.thymeleaf.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepo extends JpaRepository<Chatroom, Integer> {

    Chatroom findChatUserByKey(String room);
}
