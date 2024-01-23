/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.KeyHandler;
import main.MainPanel;

/**
 *
 * @author adoni
 */
public class CustomerSprite {
    private MainPanel mainPanel;    
    private KeyHandler keyHandler;
    
    private String direction;
    private int x, y;
    private int defaultX, defaultY;
    private int speed;
    private BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, right1, right2, right3, right4;
    
    private int spriteCounter = 0;
    private int spriteNum = 1;
    
    private Rectangle collisionArea;
    private int collisionAreaDefaultX, collisionAreaDefaultY;
    private boolean collisionOn;
    
    private int servingTime;
    private int timeCounter = 0;
    private boolean isBeingServed = false;
    private int chosenWindow;
        
    public CustomerSprite(MainPanel mainPanel, KeyHandler keyHandler) {
        this.mainPanel = mainPanel;
        this.keyHandler = keyHandler;
        direction = "up";
        
        collisionArea = new Rectangle(8, 16, 28, 52);
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;
    }
    
    public void setDefaultValues(int x, int y, int speed, int servingTime) {
        this.x = x;
        this.y = y;
        defaultX = x;
        defaultY = y;
        this.speed = speed;
        this.servingTime = servingTime;
    }
    
    public void getPlayerImage(String u1, String u2, String u3, String u4, 
                               String d1, String d2, String d3, String d4,
                               String r1, String r2, String r3, String r4) {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/customers/" + u1 + ".png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/customers/" + u2 + ".png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/customers/" + u3 + ".png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/customers/" + u4 + ".png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/customers/" + d1 + ".png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/customers/" + d2 + ".png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/customers/" + d3 + ".png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/customers/" + d4 + ".png"));
            
            right1 = ImageIO.read(getClass().getResourceAsStream("/customers/" + r1 + ".png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/customers/" + r2 + ".png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/customers/" + r3 + ".png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/customers/" + r4 + ".png"));

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setCustomerImage(String imageName) {
        String upImage = imageName + "_up_";
        String downImage = imageName + "_down_";
        String rightImage = imageName + "_right_";
        getPlayerImage(
            upImage + "1", upImage + "2", upImage + "3", upImage + "4",
            downImage + "1", downImage + "2", downImage + "3", downImage + "4",
            rightImage + "1", rightImage + "2", rightImage + "3", rightImage + "4"
        );
    }
    
    public void setAction() {
        if (direction.equals("up")) {
            if (y <= 5 * mainPanel.getTileSize()) {
                if (!isBeingServed) {
                    isBeingServed = true;
                } else if (timeCounter < servingTime) {
                    timeCounter++;
                } else {
                    isBeingServed = false;
                    direction = "right";
                }
            }
        } else if (direction.equals("right")) {
            if (x >= defaultX + (3 * mainPanel.getTileSize()) / 2) {
                direction = "down";
            }
        }  
    }
    
    public void update() {
        setAction();
        collisionOn = false;
        
        // CHECk COLLISION
        mainPanel.getCollisionChecker().checkFront(this, mainPanel.getCustomers());
        
        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn) {
            if (!isBeingServed) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "right" -> x += speed;
                }

                spriteCounter++;
                if (spriteCounter > 15) {
                    switch (spriteNum) {
                        case 1 -> spriteNum = 2;
                        case 2 -> spriteNum = 3;
                        case 3 -> spriteNum = 4;
                        case 4 -> spriteNum = 1;
                        default -> {
                        }
                    }
                    spriteCounter = 0;
                }
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (isBeingServed || collisionOn) {
            image = up3; // Display up3 when isDelaying is true
        } else {
            switch (direction) {
                case "up" -> {
                switch (spriteNum) {
                    case 1 -> image = up1;
                    case 2 -> image = up2;
                    case 3 -> image = up3;
                    case 4 -> image = up4;
                    default -> {
                    }
                }
                }
                case "down" -> {
                    switch (spriteNum) {
                        case 1 -> image = down1;
                        case 2 -> image = down2;
                        case 3 -> image = down3;
                        case 4 -> image = down4;
                        default -> {
                        }
                    }
                }
                
                case "right" -> {
                    switch (spriteNum) {
                        case 1 -> image = right1;
                        case 2 -> image = right2;
                        case 3 -> image = right3;
                        case 4 -> image = right4;
                        default -> {
                        }
                    }
                }

            }
        }

        g2.drawImage(image, x, y, 48, 72, null);
    }
    
    // Getters and Setters
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }
    
    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getUp3() {
        return up3;
    }

    public void setUp3(BufferedImage up3) {
        this.up3 = up3;
    }

    public BufferedImage getUp4() {
        return up4;
    }

    public void setUp4(BufferedImage up4) {
        this.up4 = up4;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getDown3() {
        return down3;
    }

    public void setDown3(BufferedImage down3) {
        this.down3 = down3;
    }

    public BufferedImage getDown4() {
        return down4;
    }

    public void setDown4(BufferedImage down4) {
        this.down4 = down4;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getRight3() {
        return right3;
    }

    public void setRight3(BufferedImage right3) {
        this.right3 = right3;
    }

    public BufferedImage getRight4() {
        return right4;
    }

    public void setRight4(BufferedImage right4) {
        this.right4 = right4;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

    public void setCollisionArea(Rectangle collisionArea) {
        this.collisionArea = collisionArea;
    }

    public int getCollisionAreaDefaultX() {
        return collisionAreaDefaultX;
    }

    public void setCollisionAreaDefaultX(int collisionAreaDefaultX) {
        this.collisionAreaDefaultX = collisionAreaDefaultX;
    }

    public int getCollisionAreaDefaultY() {
        return collisionAreaDefaultY;
    }

    public void setCollisionAreaDefaultY(int collisionAreaDefaultY) {
        this.collisionAreaDefaultY = collisionAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getServingTime() {
        return servingTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public boolean isIsBeingServed() {
        return isBeingServed;
    }

    public void setIsBeingServed(boolean isBeingServed) {
        this.isBeingServed = isBeingServed;
    }

    public int getChosenWindow() {
        return chosenWindow;
    }

    public void setChosenWindow(int chosenWindow) {
        this.chosenWindow = chosenWindow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomerSprite{");
        sb.append("direction=").append(direction);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", speed=").append(speed);
        sb.append(", spriteCounter=").append(spriteCounter);
        sb.append(", spriteNum=").append(spriteNum);
        sb.append(", servingTime=").append(servingTime);
        sb.append(", timeCounter=").append(timeCounter);
        sb.append(", isBeingServed=").append(isBeingServed);
        sb.append(", chosenWindow=").append(chosenWindow);
        sb.append('}');
        return sb.toString();
    }
    
    
}
