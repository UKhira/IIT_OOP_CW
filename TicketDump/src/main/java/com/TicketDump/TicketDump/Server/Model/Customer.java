package com.TicketDump.TicketDump.Server.Model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer implements Runnable{
    private long id;

    private int ticketCount;

    private String name;

    @Autowired
    private Ticketpool ticket;

    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());
    private static List<String> custNameList = Collections.synchronizedList(new ArrayList<>());


    private static volatile boolean runFlag = true;



    public Customer(Ticketpool ticket, int amount, String name){
        this.ticket = ticket;
        this.ticketCount = amount;
        this.name = name;
    }

//    @Override
//    public void run() {
//        ticket.removeTickets(this.ticketCount);
//    }

    @Override
    public void run() {
        while (runFlag) {
            boolean success = ticket.removeTickets(this.ticketCount);
            if(success){
                custNameList.add(Thread.currentThread().getName());
                custList.add(ticketCount);
            }
            System.out.println(custNameList);
            System.out.println(custList);
            try {
                Thread.sleep(500); // Optional delay
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

    public static List<Integer> getCustList() {
        return custList;
    }

    public static void setCustList(int ticketCount) {
        custList.add(ticketCount);
    }
}
