package com.TicketingSystem.Server.Model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer implements Runnable{

    private long id;

    private int ticketCount;

    @Autowired
    private TicketPool ticket;

    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());
    private static List<String> custNameList = Collections.synchronizedList(new ArrayList<>());

    private static volatile boolean runFlag = true;


    public Customer(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }


    @Override
    public void run() {
        while (runFlag) {
            boolean success = ticket.removeTickets(this.ticketCount);
            if(success){
                custNameList.add(Thread.currentThread().getName());
                custList.add(ticketCount);
            }
            try {
                Thread.sleep(1000); // Optional delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Customer thread stopped.");
    }

    public static void stopThread() {
        runFlag = false;
    }
}
