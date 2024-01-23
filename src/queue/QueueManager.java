/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queue;

import java.util.Iterator;
import java.util.Queue;
import main.MainPanel;
import model.Customer;
import model.CustomerSprite;

/**
 *
 * @author adoni
 */
public class QueueManager {
    private MainPanel mainPanel;

    public QueueManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public boolean addToQueue(Customer newCustomer, int minCountWindowIdx) {
        mainPanel.getCustomerQueues()[minCountWindowIdx].add(newCustomer);
        return true;
    }
    
    public void pollFromQueue() {
        for (Queue<Customer> queue : mainPanel.getCustomerQueues()) {
            Iterator<Customer> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Customer customer = iterator.next();
                if (customer.getCustomerSprite().getX() > customer.getCustomerSprite().getDefaultX()) {
                    iterator.remove(); // Use iterator to safely remove the current element
                }
            }
        }
    }
}
