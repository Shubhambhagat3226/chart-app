package com.example.webSocket_demo.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGui extends JFrame {
    private JPanel connectedUsersPanel;

    public ClientGui(String username) {
        super("User: " + username);

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
                    ClientGui.this.dispose();
                }
            }
        });

        getContentPane().setBackground(Utility.PRIMARY_COLOR);
        addGuiComponent();
    }

    public void addGuiComponent() {
        addConnectedUserComponent();
    }

    public void addConnectedUserComponent() {
        connectedUsersPanel = new JPanel();
        connectedUsersPanel.setLayout(new BoxLayout(connectedUsersPanel, BoxLayout.Y_AXIS));
        connectedUsersPanel.setBackground(Utility.SECONDARY_COLOR);
        connectedUsersPanel.setPreferredSize(new Dimension(200, getHeight()));

        JLabel connectedUsersLabel = new JLabel("Connected Users:");
        connectedUsersLabel.setFont(new Font("Inter", Font.BOLD, 18));
        connectedUsersLabel.setForeground(Utility.TEXT_COLOR);
        connectedUsersPanel.add(connectedUsersLabel);

        add(connectedUsersPanel, BorderLayout.WEST);
    }
}
