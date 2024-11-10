package main.java;

public class Vendor implements Runnable{
    private int vendorId;
    private int ticketCount;
    private TicketPool ticket;

    public Vendor(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    @Override
    public void run() {
        ticket.addTickets(this.ticketCount);
    }
}
