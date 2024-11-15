package com.TicketingSystem.Server.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "CustomerInfo")

public class Customer implements Runnable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private long id;

    @Column(name = "AddedTicketCount")
    private int ticketCount;

    /*
    Purpose - Ignore unrelated variables adding into table columns
    Reference - https://www.baeldung.com/jpa-transient-ignore-field
     */
    @Transient
    @Autowired
    private TicketPool ticket;

    @Transient
    static Customer customer = new Customer();

    @Transient
    private static List<Integer> custList = Collections.synchronizedList(new ArrayList<>());

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

    public static List<Integer> getCustList() {
        return custList;
    }

    public static void setCustList(int ticketCount) {
        custList.add(ticketCount);
    }
}
