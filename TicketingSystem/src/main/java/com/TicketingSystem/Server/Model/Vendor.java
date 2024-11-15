package com.TicketingSystem.Server.Model;

public class Vendor implements Runnable{
    private int vendorId;
    private int ticketCount;
    private TicketPool ticket;
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
        System.out.println(Thread.currentThread().getName());
        ticket.addTickets(this.ticketCount);
    }
}
