package main.java;

public class Customer implements Runnable{
    private int ticketCount;
    private TicketPool ticket;
    static Customer customer = new Customer();

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
        ticket.removeTickets(this.ticketCount);
    }
}
