package com.example.webSocket_demo.client;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGui clientGui = new ClientGui("Bye-Bye");
                clientGui.setVisible(true);
            }
        });
    }
}
