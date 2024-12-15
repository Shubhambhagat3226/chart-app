package com.example.webSocket_demo.client;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String username = JOptionPane.showInputDialog(null,
                        "Enter username(max: 16 char): ", "Chat Application", JOptionPane.QUESTION_MESSAGE);

                if (username == null || username.isEmpty() || username.length() > 16) {
                    JOptionPane.showMessageDialog(null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ClientGui clientGui = null;
                try {
                    clientGui = new ClientGui(username);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clientGui.setVisible(true);
            }
        });
    }
}
