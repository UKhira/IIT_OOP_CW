package com.TicketingSystem.Server.Model;

import com.TicketingSystem.Server.Repository.StatusTable;
import com.TicketingSystem.Server.Repository.TableRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TicketPool {

    private static int currentAmount;
    private static int maxCapacity;

    @Autowired
    TableRepo tableRepo;

    StatusTable statusTable = new StatusTable();

    protected static final Logger logger = LogManager.getLogger();

    public static void setMaxCapacity(int amount){
        System.out.println("Max Capacity added");
        maxCapacity = amount;
    }

    public static int getMaxCapacity(){
        return maxCapacity;
    }

    public static synchronized void setCurrentAmount(int totalTickets){
        currentAmount = totalTickets;
    }

    public static synchronized int getCurrentAmount(){
        return currentAmount;
    }

    public synchronized boolean addTickets(int amount) {
        if (currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            // saveCurrentAmount(amount);
            logger.info("added {} tickets to the Ticket Pool\nTickets in Pool: {}", amount, getCurrentAmount());
            return true; // Indicate success
        } else {
            logger.info("couldn't add {} tickets. Pool is full.", amount);
            return false; // Indicate failure
        }
    }

    public synchronized boolean removeTickets(int amount) {
        if (currentAmount - amount >= 0) {
            currentAmount -= amount;
            // saveCurrentAmount(amount);
            logger.info("bought {} tickets from the Ticket Pool\nTickets in Pool: {}", amount, getCurrentAmount());
            return true; // Indicate success
        } else {
            logger.info("couldn't buy {} tickets. Not enough tickets in the pool.", amount);
            return false; // Indicate failure
        }
    }

    public StatusTable saveCurrentAmount(int currentAmount){
        statusTable.setCurrent_amount(currentAmount);
        statusTable.setTime_stamp(LocalDateTime.now());
        return tableRepo.save(statusTable);
    }
}
