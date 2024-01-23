/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import model.Customer;
import model.CustomerSprite;

/**
 *
 * @author adoni
 */
public class UI {
    private final MainPanel mainPanel;
    private Graphics2D g2;
    private Font maruMonica;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    public String currentText;
    
    public UI (MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setupFonts();
    }
    
    private void setupFonts() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(inputStream));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showMessage(String text) {
        message = text;
        messageOn = true;
        
    }
    
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        
        g2.setFont(maruMonica);
//        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        
        for (int i = 0; i < mainPanel.getCustomerQueues().length; i++) {
            drawQueueScreen(i);
        }
//        drawQueueScreen(0);
//        drawQueueScreen(1);
//        drawQueueScreen(2);
       
    }  
    
    


    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255, 200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = mainPanel.getScreenWidth() / 2 - length / 2;
        return x;
    }
        
    public void drawQueueScreen(int i) {
        // WINDOW
        int x = mainPanel.getTileSize() * (3 + 5 * i);
        int y = mainPanel.getTileSize() * 2;
        int width = mainPanel.getTileSize() * 4;
        int height = mainPanel.getTileSize() + mainPanel.getTileSize() / 2;
        drawSubWindow(x, y, width, height);

        // QUEUE SIZE TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F)); // Smaller font size
        String queueSizeText = "Window " + (i + 1);
        int textWidth = g2.getFontMetrics().stringWidth(queueSizeText);
        int textX = x + (width - textWidth) / 2;
        int textY = y + 3 * mainPanel.getTileSize() / 5;

        // Set color based on queue size
        if (mainPanel.getCustomerQueues()[i].size() >= 5) {
            g2.setColor(Color.RED);  // Set color to red if the size is greater than 5
        } else {
            g2.setColor(Color.WHITE);  // Set color to white otherwise
        }

        g2.drawString(queueSizeText, textX, textY);

        // QUEUE SIZE VALUE
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F)); // Larger font size for the value

        String queueSizeValue = String.valueOf(mainPanel.getCustomerQueues()[i].size());
        textWidth = g2.getFontMetrics().stringWidth(queueSizeValue);
        textX = x + (width - textWidth) / 2;
        textY = textY + mainPanel.getTileSize() / 2;

        g2.drawString(queueSizeValue, textX, textY);

        // Reset color to default (optional)
        g2.setColor(Color.BLACK);  // You may want to reset the color to default after drawing
    }

    public void drawCustomerProfile(Customer customer) {
        CustomerSprite customerSprite = customer.getCustomerSprite();

        // Calculate the window position based on customer's current position
        int x = customerSprite.getX() + 50;
        int y = customerSprite.getY() + 27;

        // Set font and text position within the window
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 11F));

        // Set the text color to black
        Color textColor = Color.DARK_GRAY;

        // Prepare customer information
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getName()).append("\n");
        sb.append(customer.getAge()).append("\n");
        sb.append(customer.getSex()).append("\n");
        sb.append(customer.getCustomerType().getDisplayName()).append("\n");

        // Draw each line of customer information with text outlines
        for (String line : sb.toString().split("\n")) {
            int width = g2.getFontMetrics().stringWidth(line);

            // Draw the text with the specified text color
            g2.setColor(textColor);
            g2.drawString(line, x, y);

            y += g2.getFontMetrics().getHeight(); // Move to the next line
        }
    }

    public void drawServingTime(Customer customer) {
        CustomerSprite customerSprite = customer.getCustomerSprite();

        // Calculate the window position based on customer's current position
        int x = customerSprite.getX() - 4;
        int y = customerSprite.getY() + 25;

        // Set font and text position within the window
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 11F));

        // Determine the text color based on the time counter
        Color textColor;
        if (customerSprite.getTimeCounter() == 0) {
            textColor = new Color(220, 0, 0); // Dark red for "Waiting"
        } else if (customerSprite.getTimeCounter() == customerSprite.getServingTime()) {
            textColor = new Color(0, 150, 0); // Dark green for "Served"
        } else {
            textColor = new Color(50, 50, 50); // Dark gray for serving time on light gray background
        }

        // Prepare customer information
        StringBuilder sb = new StringBuilder();
        sb.append("No: ").append(mainPanel.getCustomers().indexOf(customer) + 1).append("\n");
        if (customerSprite.getTimeCounter() == 0) {
            sb.append("PENDING");
        } else if (customerSprite.getTimeCounter() == customerSprite.getServingTime()) {
            sb.append("SERVED");
        } else {
            sb.append("Serving").append("\n");
            sb.append("Status:").append("\n");
            sb.append(customerSprite.getTimeCounter());
            sb.append("/").append(customerSprite.getServingTime());
        }

        // Calculate the width of the longest line to determine the right alignment
        int maxWidth = 0;
        for (String line : sb.toString().split("\n")) {
            int width = g2.getFontMetrics().stringWidth(line);
            maxWidth = Math.max(maxWidth, width);
        }

        // Adjust the starting position (x) for right alignment
        x -= maxWidth;

        // Draw each line of customer information with right alignment and text outlines
        for (String line : sb.toString().split("\n")) {
            int width = g2.getFontMetrics().stringWidth(line);

            // Draw the text with the specified text color
            g2.setColor(textColor);
            g2.drawString(line, x + maxWidth - width, y);

            y += g2.getFontMetrics().getHeight(); // Move to the next line
        }
    }
}
