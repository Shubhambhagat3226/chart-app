package com.example.webSocket_demo.client;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGui clientGui = null;
                try {
                    clientGui = new ClientGui("Bye-Bye");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clientGui.setVisible(true);
            }
        });
    }
}
