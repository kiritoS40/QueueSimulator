/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author adoni
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
        window.setResizable(false);
        window.setTitle("Queue Simulator");

        MainPanel mainPanel = new MainPanel();
        window.add(mainPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        mainPanel.setupApp();
        mainPanel.startAppThread();

        // Add a WindowListener to prompt the user before closing
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(window,
                        "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // If the user chooses yes, close the application
                    System.exit(0);
                }
            }
        });
    }
}