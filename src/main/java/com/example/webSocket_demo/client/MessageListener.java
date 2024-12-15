package com.example.webSocket_demo.client;

import com.example.webSocket_demo.Message;
import java.util.ArrayList;

public interface MessageListener {
    void onMessageReceive(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}
