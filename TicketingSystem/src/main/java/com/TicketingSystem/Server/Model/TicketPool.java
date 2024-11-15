package com.TicketingSystem.Server.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private int id;
    private double price;
    private static int currentAmount;
    private static int maxCapacity;
    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());
    private static List<Integer> vendList = Collections.synchronizedList(new ArrayList<>());

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
            vendList.add(amount);
            System.out.println("Vendor added " + amount + " to the Ticket Pool\nTickets in Pool " + getCurrentAmount());
            System.out.println(vendList);
        }
        else {
            System.out.println("You can only add another " + (maxCapacity - currentAmount) + " tickets or less than that");
            System.out.println(vendList);
        }
    }

    public synchronized void removeTickets(int amount){
        if(currentAmount - amount >= 0) {
            currentAmount -= amount;
            custList.add(amount);
            System.out.println("Customer bought " + amount + " from the Ticket Pool \nTickets in Pool: " + getCurrentAmount());
            System.out.println(custList);
        }
        else {
            System.out.println("You can buy only " + currentAmount + " or less than that");
            System.out.println(custList);
        }
    }
}
