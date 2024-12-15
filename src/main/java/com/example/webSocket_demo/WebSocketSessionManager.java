package com.example.webSocket_demo;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WebSocketSessionManager {
    private final ArrayList<String> activeUsers = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketSessionManager(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void addUsername(String username) {
        activeUsers.add(username);
    }

    public void removeUsername(String username) {
        activeUsers.remove(username);
    }

    public void broadcastActiveUsernames() {
        messagingTemplate.convertAndSend("/topic/user", activeUsers);
        System.out.println("Broadcast Active users to /topic/user: " + activeUsers);
    }
}
