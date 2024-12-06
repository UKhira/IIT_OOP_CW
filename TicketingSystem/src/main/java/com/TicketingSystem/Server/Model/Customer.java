package com.TicketingSystem.Server.Model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.TicketingSystem.Server.Config.Utility.ANSI_WHITE;
import static com.TicketingSystem.Server.Config.Utility.logger;

public class Customer implements Runnable{

    private final int ticketCount;

    @Autowired
    TicketPool ticket;

    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());
    private static List<String> custNameList = Collections.synchronizedList(new ArrayList<>());

    private static volatile boolean runFlag = true;


    public Customer(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }


    /**
     * This method will run the Customer simulation, where multiple customers attempt to buy tickets from ticket pool. ny RunFlag it will make sure that the simulation will tun until user forces threads to stop (By pressing Enter).
     success variable will be checked whether the amount customer thread is going to buy is available in ticket pool. Only if so it will let the thread buy tickets
     */
    @Override
    public void run() {
        while (runFlag) {
            boolean success = ticket.removeTickets(this.ticketCount);
            if(success){
                custNameList.add(Thread.currentThread().getName());
                custList.add(ticketCount);
            }
            try {
                Thread.sleep(1000); // delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        logger.info(ANSI_WHITE + "Customer thread stopped.");
    }

    public static void stopThread() {
        runFlag = false;
    }
}
