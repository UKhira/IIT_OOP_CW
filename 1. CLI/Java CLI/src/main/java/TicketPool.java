package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private int id;
    private double price;
    private static int currentAmount;
    public static int maxCapacity;
    final static List<Integer> list=new ArrayList<Integer>();
    public static List<Integer> synList=Collections.synchronizedList(list);

    public static void setMaxCapacity(int amount){
        maxCapacity = amount;
    }

    public static synchronized int getCurrentAmount(){
        return currentAmount;
    }

    public synchronized void addTickets(int amount){
        if(currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            synList.add(amount);
            System.out.println("Vendor added " + amount + " to the Ticket Pool\nTickets in Pool " + currentAmount);
        }
        else
            System.out.println("You can only add another " + (maxCapacity - currentAmount) + " tickets or less than that");
    }

    public synchronized void removeTickets(int amount){
        if(currentAmount - amount >= 0) {
            currentAmount -= amount;
            synList.add(amount);
            System.out.println("Customer bought " + amount + " from the Ticket Pool \nTickets in Pool: " + currentAmount);
        }
        else
            System.out.println("You can buy only " + currentAmount + " or less than that");
    }
}
