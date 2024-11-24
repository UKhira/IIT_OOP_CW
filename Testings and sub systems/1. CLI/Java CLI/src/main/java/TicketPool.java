package main.java;

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

    public synchronized void addTickets(int amount) throws InterruptedException {
        if(currentAmount + amount <= maxCapacity) {
            notify();
            currentAmount += amount;
            vendList.add(amount);
//            System.out.println("Vendor added " + amount + " to the Ticket Pool\nTickets in Pool " + currentAmount);
        }
        else {
            wait(1000);
            vendList.add(404);
        }
//            System.out.println("You can only add another " + (maxCapacity - currentAmount) + " tickets or less than that");
    }

    public synchronized void removeTickets(int amount) throws InterruptedException {
        if(currentAmount - amount >= 0) {
            notify();
            currentAmount -= amount;
            custList.add(amount);
//            System.out.println("Customer bought " + amount + " from the Ticket Pool \nTickets in Pool: " + currentAmount);
        }
        else{
            wait(1000);
            custList.add(404);
        }
//            System.out.println("You can buy only " + currentAmount + " or less than that");
    }

    public static void showList(){
        System.out.println(custList);
        System.out.println(vendList);
    }
}
