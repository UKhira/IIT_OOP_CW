package com.TicketingSystem.Server.Model;

import com.TicketingSystem.Server.Controller.MyWebSocketHandler;
import com.TicketingSystem.Server.Repository.StatusTable;
import com.TicketingSystem.Server.Repository.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static com.TicketingSystem.Server.Config.Utility.*;

@Component
public class TicketPool {

    private static int currentAmount;
    private static int maxCapacity;

//    @Autowired
//    TableRepo tableRepo;

//    @Autowired
//    StatusTable statusTable;



    public static void setMaxCapacity(int amount){
        logger.info("Max Capacity added");
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
            // call the websocket and send the current amount
            MyWebSocketHandler.sendCurrentAmount(currentAmount);
            logger.info(ANSI_YELLOW + "added {} tickets to the Ticket Pool.\nTickets in Pool: {}", amount, getCurrentAmount());
//            saveCurrentAmount(amount);
            return true;

        } else {
            logger.info(ANSI_PURPLE + "couldn't add {} tickets. Pool is full.", amount);
            return false;
        }
    }

    public synchronized boolean removeTickets(int amount) {
        if (currentAmount - amount >= 0) {
            currentAmount -= amount;
            logger.info(ANSI_GREEN + "bought {} tickets from the Ticket Pool.\nTickets in Pool: {}", amount, getCurrentAmount());
//            saveCurrentAmount(amount);
            return true;
        } else {
            logger.info(ANSI_PURPLE + "couldn't buy {} tickets. Not enough tickets in the pool.", amount);
            return false;
        }
    }

//    @PostConstruct
//    public synchronized void saveCurrentAmount(int currentAmount){
//        statusTable.setCurrent_amount(currentAmount);
//        statusTable.setTime_stamp(LocalDateTime.now());
//        tableRepo.save(statusTable);
//    }
}
