package main.java;

public class Customer implements Runnable{
    private int ticketCount;
    private TicketPool ticket;

    public Customer(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    @Override
    public void run() {
        ticket.removeTickets(this.ticketCount);
    }
}
