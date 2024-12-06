package com.TicketingSystem.Server.Model;

public class Client {
    private int releaseRate;
    private int retrievalRate;
    private int maxAmount;
    private int vendorCount;
    private int customerCount;


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

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendors) {
        this.vendorCount = vendors;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customers) {
        this.customerCount = customers;
    }


}
