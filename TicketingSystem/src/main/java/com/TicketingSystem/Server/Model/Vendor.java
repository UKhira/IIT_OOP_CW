package com.TicketingSystem.Server.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vendor implements Runnable{
    private static final Logger log = LogManager.getLogger(Vendor.class);
    private int vendorId;
    private int ticketCount;

    @Autowired
    private TicketPool ticket;

    private static List<Integer> vendList = Collections.synchronizedList(new ArrayList<>());

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


    public static List<Integer> getVendList() {
        return vendList;
    }

    public static void setVendList(int ticketCount) {
        vendList.add(ticketCount);
    }
}
