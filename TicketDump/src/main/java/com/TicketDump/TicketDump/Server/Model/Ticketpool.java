package com.TicketDump.TicketDump.Server.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ticketpool {
//    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());
//    private static List<Integer> vendList = Collections.synchronizedList(new ArrayList<>());

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

    public synchronized boolean addTickets(int amount) {
        if (currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            System.out.println(Thread.currentThread().getName() + " added " + amount + " tickets to the Ticket Pool\nTickets in Pool: " + getCurrentAmount());
            return true; // Indicate success
        } else {
            System.out.println("Vendor cannot add " + amount + " tickets. Pool is full.");
            return false; // Indicate failure
        }
    }

    public synchronized boolean removeTickets(int amount) {
        if (currentAmount - amount >= 0) {
            currentAmount -= amount;
            System.out.println(Thread.currentThread().getName() + "bought " + amount + " tickets from the Ticket Pool\nTickets in Pool: " + getCurrentAmount());
            return true; // Indicate success
        } else {
            System.out.println("Customer cannot buy " + amount + " tickets. Not enough tickets in the pool.");
            return false; // Indicate failure
        }
    }

}
