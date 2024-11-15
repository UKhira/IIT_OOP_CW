package com.TicketingSystem.Server.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Vendor implements Runnable{
    private static final Logger log = LogManager.getLogger(Vendor.class);
    private int vendorId;
    private int ticketCount;
    private TicketPool ticket;
    private boolean threadFlag = true;
//    static Vendor vendor = new Vendor();

    public Vendor(){}

//    private static Vendor getInstance(){
//        return vendor;
//    }

    public Vendor(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    @Override
    public void run() {
//        Scanner sc = new Scanner(System.in);
//        while (threadFlag) {
            ticket.addTickets(this.ticketCount);
//            try {
//                Thread.sleep(500);
//            }
//            catch(InterruptedException ie) {
//                log.error("e: ", ie);
//            }
//        }
    }


//    public void stopThread() {
//        threadFlag = false;
//    }
}
