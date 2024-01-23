/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import java.util.Queue;
import java.util.Random;
import main.KeyHandler;
import main.MainPanel;
import model.Customer;
import model.CustomerSprite;
import model.CustomerType;
import model.Transaction;
import queue.QueueManager;
import util.CustomerDataStorage;

/**
 *
 * @author adoni
 */
public class CustomerGenerator {
    private MainPanel mainPanel;
    private KeyHandler keyHandler;

    private final Random random = new Random();
    
    public CustomerGenerator (MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
    public void generate() {
        Customer customer = createCustomer();
        addCustomerToQueue(customer);
        
        for (int i = 0; i < mainPanel.getCustomerQueues().length; i++) {
            Queue<Customer> queue = mainPanel.getCustomerQueues()[i];
            System.out.println("Window " + (i + 1) + ": " + queue.size());
        }
        System.out.println("\n");
    }
    
    public Customer createCustomer() {
        Customer customer = new Customer();
        
        // Get the customer type
        CustomerType type = getRandomCustomerType();
        
        // Set the type
        customer.setCustomerType(type);
        
        // Set sex
        String[] sexes = {"Male", "Female"};
        String sex = sexes[random.nextInt(2)];
        if (type == CustomerType.PREGNANT) {
            customer.setSex("Female");
        } else {
            customer.setSex(sex);   
        }
        
        // Set the name
        int nameIndex = random.nextInt(CustomerDataStorage.getNumberOfNames());
        if (type == CustomerType.PREGNANT) {
            customer.setName(CustomerDataStorage.getName(1, nameIndex));
        } else if (sex.equals("Male")) {
            customer.setName(CustomerDataStorage.getName(0, nameIndex));
        } else if (sex.equals("Female")) {
            customer.setName(CustomerDataStorage.getName(1, nameIndex));
        }

        // Set age
        int minAge;
        int maxAge;

        if (type == CustomerType.SENIOR_CITIZEN) {
            minAge = 60;
            maxAge = 81;
        } else if (type == CustomerType.PREGNANT) {
            minAge = 18; 
            maxAge = 46;
        } else {
            minAge = 18;
            maxAge = 60;
        }
        customer.setAge(random.nextInt(minAge, maxAge));

        // Set Customer Sprite
        CustomerSprite customerSprite = new CustomerSprite(mainPanel, keyHandler);
        
        int imageIndex = random.nextInt(2);
        if (null != type) switch (type) {
            case SENIOR_CITIZEN -> {
                if (sex.equals("Male")) {
                    customerSprite.setCustomerImage(CustomerDataStorage.getSeniorImageNames()[imageIndex]);
                } else if (sex.equals("Female")) {
                    customerSprite.setCustomerImage(CustomerDataStorage.getSeniorImageNames()[2]);
                }
            }
            case PREGNANT -> customerSprite.setCustomerImage(CustomerDataStorage.getPregnantImageNames()[imageIndex]);
            case PWD -> {
                if (sex.equals("Male")) {
                    customerSprite.setCustomerImage(CustomerDataStorage.getPWDImageNames()[0]);
                } else if (sex.equals("Female")) {
                    customerSprite.setCustomerImage(CustomerDataStorage.getPWDImageNames()[1]);
                }
            }
            case REGULAR -> {
                if (sex.equals("Male")) {
                    customerSprite.setCustomerImage(CustomerDataStorage.getRegularImageNames()[imageIndex]);
                } else if (sex.equals("Female")) {  
                    customerSprite.setCustomerImage(CustomerDataStorage.getRegularImageNames()[random.nextInt(2, 4)]);
                }
            }
        }
        customer.setCustomerSprite(customerSprite);
        
        return customer;
    }
    
    public void addCustomerToQueue(Customer customer) {
        QueueManager queueManager = new QueueManager(mainPanel);
        int[] windowsXCoordinate = {4, 9, 14};

        // Check the window with the fewest customers
        CustomerType type = customer.getCustomerType();
        int minCountWindowIdx = type == CustomerType.SENIOR_CITIZEN || 
                                type == CustomerType.PREGNANT ||
                                type == CustomerType.PWD
                                
                                ? getWindowWithFewestCustomers() : getWindowWithFewestPlayersExcludingPriority();
        
        // Set serving time
        int servingTime = setRandomServingTime();
        
        // Set customer sprite default values
        int yCoordinate = 11; // default yCoordinate
        CustomerSprite customerSprite = customer.getCustomerSprite();
        customerSprite.setDefaultValues(windowsXCoordinate[minCountWindowIdx] * mainPanel.getTileSize() + mainPanel.getTileSize() / 2, 
                                                           yCoordinate * mainPanel.getTileSize() + mainPanel.getTileSize() / 8, 
                                                           1, 
                                                           servingTime);
        
        // Enqueue the customer to the chosen window or lane
        queueManager.addToQueue(customer, minCountWindowIdx);
        
        // Add the customer to the list
        mainPanel.getCustomers().add(customer);

        // Set transaction
        setTransaction(customer, minCountWindowIdx + 1, servingTime);
    }
    
    public void setTransaction(Customer customer, int windowQueueNumber, int servingTime) {
        mainPanel.getTransactions().add(new Transaction(customer, windowQueueNumber, servingTime));
    }
    
    public CustomerType getRandomCustomerType() {
        int counter = random.nextInt(100) + 1; // 1 to 100
        if (counter <= 9) {
            return CustomerType.SENIOR_CITIZEN;
        } 
        if (counter > 9 && counter <= 18) { // 10 to 18
            return CustomerType.PREGNANT;
        } 
        if (counter > 18 && counter <= 27) {
            return CustomerType.PWD;
        }
        return CustomerType.REGULAR;
    }
   
    public int setRandomServingTime() {
        return (int) (Math.random() * 200) + 501; // set random serving time between 501 (inclusive) and 701 (exclusive) 
    }
    
    public int getWindowWithFewestCustomers() {
        Queue<Customer>[] customerQueues = mainPanel.getCustomerQueues();
        int minWindowIdx = 0;
        int minCount = customerQueues[minWindowIdx].size();
        
        for (int windowIdx = 0; windowIdx < customerQueues.length; windowIdx++) {
            if (customerQueues[windowIdx].size() < minCount) {
                minWindowIdx = windowIdx;
                minCount = customerQueues[windowIdx].size();
            }
        }
        return minWindowIdx;
    }
    
    private int getWindowWithFewestPlayersExcludingPriority() {
        Queue<Customer>[] customerQueues = mainPanel.getCustomerQueues();
        int minWindowIdx = 1;
        int minCount = customerQueues[minWindowIdx].size();
        
        for (int windowIdx = 1; windowIdx < customerQueues.length; windowIdx++) {
            if (customerQueues[windowIdx].size() < minCount) {
                minWindowIdx = windowIdx;
                minCount = customerQueues[windowIdx].size();
            }
        }
        return minWindowIdx;
    }
}
