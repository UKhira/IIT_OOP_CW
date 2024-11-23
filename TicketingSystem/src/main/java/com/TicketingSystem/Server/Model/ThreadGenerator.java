package com.TicketingSystem.Server.Model;

import com.TicketingSystem.CLI.CLI;
import com.TicketingSystem.Server.Repository.StatusTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class ThreadGenerator {


    Scanner addCount = new Scanner(System.in);

    public void startThreads(int ticketRelease, int customerRetrieval) {

        // Generate Random Ticket Amounts within parameters
        Random randomNumber = new Random();
        TicketPool ticketPool = new TicketPool();
        List<Thread> threadList = new ArrayList<>();

        System.out.println("Once the simulation started. Press Enter to stop.\n");

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
        //        int i = 0;
//        while(i < Vendor.getVendTicketList().size()){
//            table.saveThreadActivity(Vendor.getVendNameList().get(i),"Vendor", Vendor.getVendTicketList().get(i));
//            i++;
//        }
    }



    /*
     * Purpose - To prevent user input errors with scanner in more efficient way
     * Reference: https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint*/
    private int setVendorCount(){
        int count;
        System.out.println("To Start Simulation Please Enter Vendor Count you want to add \nIf the value is invalid Vendor count will be set as 5 to run the the simulation");
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
