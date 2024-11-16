package com.TicketingSystem.Server.Model;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//@Entity(name = "Vendor_Info")
public class Vendor implements Runnable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Vendor_Id")
//    private int vendorId;

//    @Column(name = "Added_Ticket_Count")
    private int ticketCount;

//    @Transient
    @Autowired
    private TicketPool ticket;


//    @Transient
    private static List<Integer> vendList = Collections.synchronizedList(new ArrayList<>());

//    @Transient
    private static volatile boolean runflag = true;


//    static Vendor vendor = new Vendor();

//    public Vendor(){}

//    private static Vendor getInstance(){
//        return vendor;
//    }

    public Vendor(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

//    @Override
//    public void run() {
//        ticket.addTickets(this.ticketCount);
//    }

    /**
     * Inherited method from runnable Interface. This method will do the thread running under runFlag() till user want to stop simulation
     * @since 1.3*/
    @Override
    public void run() {
        while (runflag) {
            ticket.addTickets(this.ticketCount); // Vendor adds tickets
            try {
                Thread.sleep(500); // Optional delay
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

    public static List<Integer> getVendList() {
        return vendList;
    }

    public static void setVendList(int ticketCount) {
        vendList.add(ticketCount);
    }
}
