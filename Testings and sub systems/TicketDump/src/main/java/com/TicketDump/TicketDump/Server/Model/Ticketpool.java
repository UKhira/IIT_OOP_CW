package com.TicketDump.TicketDump.Server.Model;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
//@Entity
public class Ticketpool {

//    @Id
//    @Column
    private static int currentAmount;

//    @Transient
    private static int maxCapacity;

    protected static final Logger logger = LogManager.getLogger();

    public static void setMaxCapacity(int amount){
        maxCapacity = amount;
    }

    public static int getMaxCapacity(){
        return maxCapacity;
    }

    public static synchronized int getCurrentAmount(){
        return currentAmount;
    }

    public static AtomicInteger convertAndReturnMAx(){
        AtomicInteger count = new AtomicInteger(currentAmount);
        return count;
    }

    public synchronized boolean addTickets(int amount) {
        if (currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            logger.info("{} added {} tickets to the Ticket Pool\nTickets in Pool: {}", Thread.currentThread().getName(), amount, getCurrentAmount());
            return true; // Indicate success
        } else {
            logger.info("{} couldn't add {} tickets. Pool is full.",Thread.currentThread().getName(), amount);
            return false; // Indicate failure
        }
    }

    public synchronized boolean removeTickets(int amount) {
        if (currentAmount - amount >= 0) {
            currentAmount -= amount;
            logger.info("{} bought {} tickets from the Ticket Pool\nTickets in Pool: {}", Thread.currentThread().getName(), amount, getCurrentAmount());
            return true; // Indicate success
        } else {
            logger.info("{} couldn't buy {} tickets. Not enough tickets in the pool.", Thread.currentThread().getName(),amount);
            return false; // Indicate failure
        }
    }
}
