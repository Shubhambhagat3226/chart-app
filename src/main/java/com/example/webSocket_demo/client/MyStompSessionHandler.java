package com.example.webSocket_demo.client;

import com.example.webSocket_demo.Message;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
    private String username;
    private MessageListener messageListener;

    public MyStompSessionHandler(MessageListener messageListener, String username) {
        this.messageListener = messageListener;
        this.username = username;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Client connected");

        session.send("/app/connect", username);
        session.subscribe("/topic/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {
                    if (payload instanceof Message) {
                        Message message = (Message) payload;
                        messageListener.onMessageReceive(message);
                        System.out.println("Received message: " + message.getUser() + ": " + message.getMessage());
                    } else {
                        System.out.println("Received unexpected payload type: " + payload.getClass());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Client subscribe to /topic/message");
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        exception.printStackTrace();
    }
}
