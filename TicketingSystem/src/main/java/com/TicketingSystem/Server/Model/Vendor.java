package com.TicketingSystem.Server.Model;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//@Entity(name = "Vendor_Info")
public class Vendor implements Runnable{
    private final int ticketCount;

    //    @Transient
    @Autowired
    private TicketPool ticket;

    private String name;


    //    @Transient
    private static final List<Integer> vendTicketList = Collections.synchronizedList(new ArrayList<>());
    private static final List<String> vendNameList = Collections.synchronizedList(new ArrayList<>());


    //    @Transient
    private static volatile boolean runflag = true;


    public Vendor(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    public static List<String> getVendNameList(){
        return vendNameList;
    }

    public static List<Integer> getVendTicketList(){
        return vendTicketList;
    }

    /**
     * Inherited method from runnable Interface. This method will do the thread running under runFlag() till user want to stop simulation
     * @since 1.3*/
    @Override
    public void run() {
        while (runflag) {
            boolean success = ticket.addTickets(this.ticketCount);
            if(success){
                vendNameList.add(Thread.currentThread().getName());
                vendTicketList.add(ticketCount);
            }
//            System.out.println(vendNameList);
//            System.out.println(vendList);

            try {
                Thread.sleep(1000); // Optional delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Vendor thread stopped.");

    }


    /**
     * will get change the value of flag to stop the loop of run() method so that it will stop the vendors adding ticket simulation*/
    public static void stopThread() {
        runflag = false;
    }
}
