/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import java.util.List;
import model.Customer;
import model.CustomerSprite;

/**
 *
 * @author adoni
 */
public class CollisionChecker {
    private MainPanel mainPanel;
    
    public CollisionChecker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
    // Check if there is customer in front
    public void checkFront(CustomerSprite customerSprite, List<Customer> targets) {
        int index = 999;

        for (Customer t : targets) {
            CustomerSprite target = t.getCustomerSprite();
            if (target != customerSprite && target != null) {
                // Get entity's solid area position
                customerSprite.getCollisionArea().x = customerSprite.getX() + customerSprite.getCollisionArea().x;
                customerSprite.getCollisionArea().y = customerSprite.getY() + customerSprite.getCollisionArea().y;

                // Get the object's solid area position
                target.getCollisionArea().x = target.getX() + target.getCollisionArea().x;
                target.getCollisionArea().y = target.getY() + target.getCollisionArea().y;

                switch (customerSprite.getDirection()) {
                    case "up" -> {
                        customerSprite.getCollisionArea().y -= customerSprite.getSpeed();
                        if (customerSprite.getCollisionArea().intersects(target.getCollisionArea())) {
                            customerSprite.setCollisionOn(true);
                        }
                    }
                    case "down" -> {
                        customerSprite.getCollisionArea().y -= customerSprite.getSpeed();
                        if (customerSprite.getCollisionArea().intersects(target.getCollisionArea())) {
                            customerSprite.setCollisionOn(true);
                        }
                    }
                    case "left" -> {
                        customerSprite.getCollisionArea().y -= customerSprite.getSpeed();
                        if (customerSprite.getCollisionArea().intersects(target.getCollisionArea())) {
                            customerSprite.setCollisionOn(true);
                        }
                    }
                    case "right" -> {
                        customerSprite.getCollisionArea().y -= customerSprite.getSpeed();
                        if (customerSprite.getCollisionArea().intersects(target.getCollisionArea())) {
                            customerSprite.setCollisionOn(true);
                        }
                    }
                }
                customerSprite.getCollisionArea().x = customerSprite.getCollisionAreaDefaultX();
                customerSprite.getCollisionArea().y = customerSprite.getCollisionAreaDefaultY();
                target.getCollisionArea().x = target.getCollisionAreaDefaultX();
                target.getCollisionArea().y = target.getCollisionAreaDefaultY();
            }
        }
    }
}
