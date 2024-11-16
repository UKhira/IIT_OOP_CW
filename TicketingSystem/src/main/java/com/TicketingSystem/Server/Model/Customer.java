package com.TicketingSystem.Server.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Entity(name = "CustomerInfo")

public class Customer implements Runnable{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "CustomerId")
    private long id;

//    @Column(name = "AddedTicketCount")
    private int ticketCount;

    /**
    * Purpose - Ignore unrelated variables adding into table columns
    * @see <a href="https://www.baeldung.com/jpa-transient-ignore-field">@Transient annotation</a>
     */
//    @Transient
//    @Autowired
    private TicketPool ticket;

//    @Transient
//    static Customer customer = new Customer();

//    @Transient
    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());

    /*
    * Purpose: Make the runFlag changes is immediately detected to run() method
    * Reference: https://www.datacamp.com/doc/java/volatile
    * */
//    @Transient
    private static volatile boolean runFlag = true;


    // Singleton Class - https://www.youtube.com/watch?v=KUTqnWswPV4&list=PLsyeobzWxl7rqhgfVySFnwhtS4QUT4805

//    private Customer(){}

//    public static Customer getInstance(){
//        return customer;
//    }

    public Customer(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

//    @Override
//    public void run() {
//        ticket.removeTickets(this.ticketCount);
//    }

    @Override
    public void run() {
        while (runFlag) {
            ticket.removeTickets(this.ticketCount); // Customer removes tickets
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
