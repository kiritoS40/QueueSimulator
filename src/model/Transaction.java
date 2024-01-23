/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author adoni
 */
public class Transaction {
    private Customer customer;
    private int windowQueueNumber;
    private int servingTime;

    public Transaction(Customer customer, int windowQueueNumber, int servingTime) {
        this.customer = customer;
        this.windowQueueNumber = windowQueueNumber;
        this.servingTime = servingTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getWindowQueueNumber() {
        return windowQueueNumber;
    }

    public void setWindowQueue(int windowQueueNumber) {
        this.windowQueueNumber = windowQueueNumber;
    }

    public int getServingTime() {
        return servingTime;
    }

    public void setServingTime(int servingTime) {
        this.servingTime = servingTime;
    }
    
    
}
