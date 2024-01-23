/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author adoni
 */
public class Customer {
    private String name;
    private int age;
    private String sex;
    private CustomerType customerType;
    private CustomerSprite customerSprite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerSprite getCustomerSprite() {
        return customerSprite;
    }

    public void setCustomerSprite(CustomerSprite customerSprite) {
        this.customerSprite = customerSprite;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", age=" + age + ", sex=" + sex + ", customerType=" + customerType + ", customerSprite=" + customerSprite + '}';
    }

}
