package com.TicketDump.TicketDump.Server.Model;

import com.TicketDump.TicketDump.Server.Repository.ThreadActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.TicketDump.TicketDump.Server.Model.Ticketpool;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vendor implements Runnable{
    private int ticketCount;

    //    @Transient
    @Autowired
    private Ticketpool ticket;

    private String name;


    //    @Transient
    private static List<Integer> vendList = Collections.synchronizedList(new ArrayList<>());
    private static List<String> vendNameList = Collections.synchronizedList(new ArrayList<>());


    //    @Transient
    private static volatile boolean runflag = true;


    public Vendor(Ticketpool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    public static List<String> getVendNameList(){
        return vendNameList;
    }

    public static List<Integer> getVendTicketList(){
        return vendList;
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
                vendList.add(ticketCount);
            }
//            System.out.println(vendNameList);
//            System.out.println(vendList);

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
}
