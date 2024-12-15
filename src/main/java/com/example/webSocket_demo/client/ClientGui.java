package com.example.webSocket_demo.client;

import com.example.webSocket_demo.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClientGui extends JFrame implements MessageListener{
    private JPanel connectedUsersPanel, messagePanel;
    private MyStompClient myStompClient;
    private String username;

    public ClientGui(String username) throws ExecutionException, InterruptedException {
        super("User: " + username);
        this.username = username;
        myStompClient = new MyStompClient(this, username);

        setSize(1216,685);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(ClientGui.this,
                        "Do you really want to leave?",
                        "Exit", JOptionPane.YES_NO_OPTION);

                if (choose == JOptionPane.YES_OPTION) {
                    myStompClient.disconnectUser(username);
                    ClientGui.this.dispose();
                }
            }
        });

        getContentPane().setBackground(Utility.PRIMARY_COLOR);
        addGuiComponent();
    }

    public void addGuiComponent() {
        addConnectedUserComponent();
        addChartComponent();
    }

    public void addConnectedUserComponent() {
        connectedUsersPanel = new JPanel();
        connectedUsersPanel.setBorder(Utility.addPadding(10,10,10,10));
        connectedUsersPanel.setLayout(new BoxLayout(connectedUsersPanel, BoxLayout.Y_AXIS));
        connectedUsersPanel.setBackground(Utility.SECONDARY_COLOR);
        connectedUsersPanel.setPreferredSize(new Dimension(200, getHeight()));

        JLabel connectedUsersLabel = new JLabel("Connected Users:");
        connectedUsersLabel.setFont(new Font("Inter", Font.BOLD, 18));
        connectedUsersLabel.setForeground(Utility.TEXT_COLOR);
        connectedUsersPanel.add(connectedUsersLabel);

        add(connectedUsersPanel, BorderLayout.WEST);
    }

    public void addChartComponent() {
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.setBackground(Utility.TRANSPARENT_COLOR);

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Utility.TRANSPARENT_COLOR);
        chartPanel.add(messagePanel, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(Utility.addPadding(10,10,10,10));
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Utility.TRANSPARENT_COLOR);

        JTextField inputField = new JTextField();
        inputField.setBackground(Utility.SECONDARY_COLOR);
        inputField.setForeground(Utility.TEXT_COLOR);
        inputField.setFont(new Font("Inter", Font.PLAIN, 16));
        inputField.setPreferredSize(new Dimension(inputPanel.getWidth(), 50));
        inputField.setBorder(Utility.addPadding(10, 10, 10, 10));
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String input = inputField.getText();

                    // EDGE CASE: STOP TO UPLOAD EMPTY MESSAGE
                    if (input.isEmpty()) return;
                    inputField.setText("");

                    myStompClient.sendMessage(new Message(username, input));
                }
            }
        });
        inputPanel.add(inputField, BorderLayout.CENTER);

        chartPanel.add(inputPanel, BorderLayout.SOUTH);

        add(chartPanel, BorderLayout.CENTER);
    }

    private JPanel createChatMessageComponent(Message message) {
        JPanel chatMessage = new JPanel();
        chatMessage.setBackground(Utility.TRANSPARENT_COLOR);
        chatMessage.setLayout(new BoxLayout(chatMessage, BoxLayout.Y_AXIS));
        chatMessage.setBorder(Utility.addPadding(20, 20, 20 , 20));

        JLabel usernameLabel = new JLabel(message.getUser());
        usernameLabel.setFont(new Font("Inter", Font.BOLD, 18));
        usernameLabel.setForeground(Utility.TEXT_COLOR);
        chatMessage.add(usernameLabel);

        JLabel messageLabel = new JLabel(message.getMessage());
        messageLabel.setFont(new Font("Inter", Font.PLAIN, 18));
        messageLabel.setForeground(Utility.TEXT_COLOR);
        chatMessage.add(messageLabel);

        return chatMessage;
    }

    @Override
    public void onMessageReceive(Message message) {
        messagePanel.add(createChatMessageComponent(message));
        revalidate();
        repaint();
    }

    @Override
    public void onActiveUsersUpdated(ArrayList<String> users) {
        // REMOVE THE CURRENT USER LIST PANEL (WHICH SHOULD BE THE SECOND COMPONENT IN THE PANEL)
        // THE USER LIST PANEL DOESN'T GET ADDED UNTIL AFTER AND THIS IS MAINLY FOR WHEN THE USERS GET UPDATE
        if (connectedUsersPanel.getComponents().length >= 2) {
            connectedUsersPanel.remove(1);
        }

        JPanel userListPanel = new JPanel();
        userListPanel.setBackground(Utility.TRANSPARENT_COLOR);
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        users.stream().forEach(user -> {
            JLabel username = new JLabel(user);
            username.setFont(new Font("Inter", Font.BOLD, 16));
            username.setForeground(Utility.TEXT_COLOR);
            username.setBorder(Utility.addPadding(5, 5, 5, 5));
            userListPanel.add(username);
        });

        connectedUsersPanel.add(userListPanel);
        revalidate();
        repaint();
    }
}
