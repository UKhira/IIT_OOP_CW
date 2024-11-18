package com.TicketDump.TicketDump.Server.Model;
import com.TicketDump.TicketDump.Server.Repository.ThreadActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class ThreadGenerator {

    @Autowired
    ThreadActivityDAO threadActivityDAO;

    Scanner addCount = new Scanner(System.in);

    public void startThreads(int ticketRelease, int customerRetrieval) {

        // Generate Random Ticket Amounts within parameters
        Random randomNumber = new Random();
        Ticketpool ticketPool = new Ticketpool();
        List<Thread> threadList = new ArrayList<>();

        int vendorCount = setVendorCount();
        int customerCount = setCustomerCount();

        for (int i = 1; i <= vendorCount; i++) {
            Thread vendor = new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease)));
            vendor.setName("Vendor " + i);
            System.out.println("Vendor " + i + "started");
            threadList.add(vendor);
            vendor.start();
        }

        for (int i = 1; i <= customerCount; i++) {
            Thread customer = new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval)));
            customer.setName("Vendor " + i);
            System.out.println("Customer " + i + "started");
            threadList.add(customer);
            customer.start();
        }
        System.out.println(threadList);

        System.out.println("Simulation running. Press Enter to stop.");
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

        System.out.println("Simulation stopped.");
        int i = 0;
        while(i < Vendor.getVendTicketList().size()){
            threadActivityDAO.saveThreadActivity(Vendor.getVendNameList().get(i),"Vendor", Vendor.getVendTicketList().get(i));
            i++;
        }
    }



    /*
     * Purpose - To prevent user input errors with scanner in more efficient way
     * Reference: https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint*/
    private int setVendorCount(){
        int count;
        System.out.println("To Start Simulation Please Enter Vendor Count you want to add \nIf the value is invalid Customer count will be set as 5 to run the the simulation");
        try{
            count = Integer.parseInt(addCount.nextLine());
        }
        catch (Exception e){
            System.out.println("Invalid Input. Setting Vendor Count to 5");
            count = 5;
        }
        return count;
    }

    private int setCustomerCount(){
        int count;
        System.out.println("To Start Simulation Please Enter Customer Count you want to add \nIf the value is invalid Customer count will be set as 5 to run the the simulation");
        try{
            count = Integer.parseInt(addCount.nextLine());
        }
        catch (Exception e){
            System.out.println("Invalid Input. Setting Customer Count to 5");
            count = 5;
        }
        return count;
    }
}
