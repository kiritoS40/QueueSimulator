/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import generator.CustomerGenerator;
import java.util.Queue;
import model.Customer;

/**
 *
 * @author adoni
 */
public class CustomerGeneratorThread extends Thread {
    private MainPanel mainPanel;
    private CustomerGenerator customerGenerator;
    private boolean running;

    public CustomerGeneratorThread(MainPanel mainPanel, CustomerGenerator customerGenerator) {
        this.mainPanel = mainPanel;
        this.customerGenerator = customerGenerator;
        this.running = true;
    }

    public void stopGenerating() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            boolean shouldGenerate = true;

            // Check the size of each queue
            for (Queue<Customer> queue : mainPanel.getCustomerQueues()) {
                if (queue.size() >= 5) {
                    shouldGenerate = false;
                    break;
                }
            }

            // Generate customers if allowed
            if (shouldGenerate) {
                customerGenerator.generate();
            }

            // Sleep for a specific amount of time to control the generation rate
            try {
                Thread.sleep(3300); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
