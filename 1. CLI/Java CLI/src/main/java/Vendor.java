package main.java;

public class Vendor implements Runnable{
    private int vendorId;
    private int ticketCount;
    private TicketPool ticket;
    static Vendor vendor = new Vendor();

    private Vendor(){}

    private static Vendor getInstance(){
        return vendor;
    }

    public Vendor(TicketPool ticket, int amount){
        this.ticket = ticket;
        this.ticketCount = amount;
    }

    @Override
    public void run() {
        ticket.addTickets(this.ticketCount);
    }
}
