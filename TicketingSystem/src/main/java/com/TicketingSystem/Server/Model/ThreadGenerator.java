package com.TicketingSystem.Server.Model;

import com.TicketingSystem.CLI.CLI;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class ThreadGenerator {

    private int vendorCount;
    private int customerCount;

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    Scanner addCount = new Scanner(System.in);

    public void startThreads(int ticketRelease, int customerRetrieval) {

        // Generate Random Ticket Amounts within parameters
        Random randomNumber = new Random();
        TicketPool ticketPool = new TicketPool();
        List<Thread> threadList = new ArrayList<>();

        if (vendorCount == 0 && customerCount == 0) {
            setVendorCount();
            setCustomerCount();
            startThreads(ticketRelease,customerRetrieval);
        } else {
        System.out.println("Once the simulation started. Press Enter to stop.\n");


        for (int i = 1; i <= vendorCount; i++) {
            Thread vendor = new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease + 1)));
            vendor.setName("Vendor " + i);
            System.out.println("Vendor " + i + "started");
            threadList.add(vendor);
            vendor.start();
        }

        for (int i = 1; i <= customerCount; i++) {
            Thread customer = new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval + 1)));
            customer.setName("Customer " + i);
            System.out.println("Customer " + i + "started");
            threadList.add(customer);
            customer.start();
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for Enter key

        // Stop threads
        Vendor.stopThread();
        Customer.stopThread();

        try {
            for (Thread threads : threadList) {
                threads.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error stopping threads.");
        }

        CLI.saveConfiguration(TicketPool.getCurrentAmount());
        System.out.println("Simulation stopped.");
    }
    }



    /*
     * Purpose - To prevent user input errors with scanner in more efficient way
     * Reference: https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint*/
    private void  setVendorCount(){
        int count = 0;
        System.out.println("To Start Simulation Please Enter Vendor Count you want to add \nIf the value is invalid Vendor count will be set as 5 to run the the simulation");
        try{
            count = Integer.parseInt(addCount.nextLine());

        }
        catch (Exception e){
            System.out.println("Invalid Input. Setting Vendor Count to 5");
            count = 5;
        }
        finally {
            vendorCount = count;
        }
    }

    private void setCustomerCount(){
        int count = 0;
        System.out.println("To Start Simulation Please Enter Customer Count you want to add \nIf the value is invalid Customer count will be set as 5 to run the the simulation");
        try{
            count = Integer.parseInt(addCount.nextLine());
        }
        catch (Exception e){
            System.out.println("Invalid Input. Setting Customer Count to 5");
            count = 5;
        }
        finally {
            customerCount = count;
        }
    }

}
