package com.example.webSocket_demo.client;

import com.example.webSocket_demo.Message;

import java.util.concurrent.ExecutionException;

public class ClientGui {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyStompClient myStompClient = new MyStompClient("Bye-Bye");
        myStompClient.sendMessage(new Message("Bye-Bye", "Hello World!"));
        myStompClient.disconnectUser("Bye-Bye");
    }
}
