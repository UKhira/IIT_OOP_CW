package com.TicketingSystem.Server.Model;

public class Client {
    private int releaseRate;
    private int retrievalRate;
    private int maxAmount;
    private int vendors;
    private int customers;

    public int getVendors() {
        return vendors;
    }

    public void setVendors(int vendors) {
        this.vendors = vendors;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public Client(){}

    public int getReleaseRate() {
        return releaseRate;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
