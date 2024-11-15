package com.TicketingSystem.Server.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

// Reference - https://www.baeldung.com/jpa-mapping-single-entity-to-multiple-tables
//@Entity(name = "TicketPool")
public class TicketPool {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    Long id;

//    @Column(name = "Current")
    private static int currentAmount;

    private static int maxCapacity;

    public static void setMaxCapacity(int amount){
        maxCapacity = amount;
    }

    public static int getMaxCapacity(){
        return maxCapacity;
    }

    public static synchronized int getCurrentAmount(){
        return currentAmount;
    }

    public synchronized void addTickets(int amount) {
        if (currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            Vendor.setVendList(amount);
            System.out.println("Vendor added " + amount + " to the Ticket Pool\nTickets in Pool " + getCurrentAmount());
            System.out.println(Vendor.getVendList());
        }
        else {
            System.out.println("You can only add another " + (maxCapacity - currentAmount) + " tickets or less than that");
        }
    }

    public synchronized void removeTickets(int amount){
        if(currentAmount - amount >= 0) {
            currentAmount -= amount;
            Customer.setCustList(amount);
            System.out.println("Customer bought " + amount + " from the Ticket Pool \nTickets in Pool: " + getCurrentAmount());
            System.out.println(Customer.getCustList());
        }
        else {
            System.out.println("You can buy only " + currentAmount + " or less than that");
        }
    }
}
