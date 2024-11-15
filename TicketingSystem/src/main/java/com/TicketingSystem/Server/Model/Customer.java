package com.TicketingSystem.Server.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Customer implements Runnable{
    private static final Logger log = LogManager.getLogger(Customer.class);
    private int ticketCount;
    private TicketPool ticket;
    static Customer customer = new Customer();
    private boolean threadFlag = true;

    // Singleton Class - https://www.youtube.com/watch?v=KUTqnWswPV4&list=PLsyeobzWxl7rqhgfVySFnwhtS4QUT4805

    private Customer(){}

    public static Customer getInstance(){
        return customer;
    }

    public Customer(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    @Override
    public void run() {
//        Scanner sc = new Scanner(System.in);
//        while (threadFlag) {
            ticket.removeTickets(this.ticketCount);
//            try {
//                Thread.sleep(500);
//            }
//            catch(InterruptedException ie) {
//                log.error("e: ", ie);
//            }
//        }
    }

    public void stopThread() {
        this.threadFlag = false;
    }
}
