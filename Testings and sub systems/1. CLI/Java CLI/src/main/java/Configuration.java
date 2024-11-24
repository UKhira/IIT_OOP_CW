package main.java;

import java.io.*;

public class Configuration implements Serializable {
    private int maxTicketCapacity;
    private int customerRetrievalRate;
    private int ticketReleaseRate;
    private int totalTickets;

    public Configuration(){}

    public int getMaxTicketCapacity() {
        return this.maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getCustomerRetrievalRate() {
        return this.customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getTicketReleaseRate() {
        return this.ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTotalTickets() {
        return this.totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "maxTicketCapacity=" + maxTicketCapacity +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", totalTickets=" + totalTickets +
                '}';
    }

    public void writeFile() throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("Config.txt"));
        obj.writeObject(this.toString());
        System.out.println("Configurations have been serialized to Config.txt");
        obj.close();
    }
}
