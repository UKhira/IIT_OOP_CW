//package com.TicketDump.TicketDump.Server.Model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//
//import java.time.LocalDateTime;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Entity(name = "status_table")
//public class Table {
//
//    @Id
//    @Column(name = "ticket_count")
//    private int amount;
//
//    @Column(name = "last_updated")
//    private LocalDateTime lastUpdated;
//
//    public Table(int amount) {
//        this.amount = amount;
//        this.lastUpdated = LocalDateTime.now();
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//}
