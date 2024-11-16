package com.TicketDump.TicketDump.Server.Model;

import com.TicketDump.TicketDump.Server.Repository.ThreadActivityDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class ThreadGenerator {
    Scanner addCount = new Scanner(System.in);

    public boolean startThreads(String[] optionArray, int ticketRelease, int customerRetrieval){
        boolean inputPassed = false;
        Scanner userInput = new Scanner(System.in);

        // Check whether all the tasks is done in option menu
        int checker = 0;
        for (String option : optionArray) {
            if (option.contains("Done"))
                checker++;
        }
        if (checker == 4) {

            // Stop the showOptionMenu() Loop
            inputPassed = true;


            // Generate Random Ticket Amounts within parameters
            Random randomNumber = new Random();
            Ticketpool ticketPool = new Ticketpool();
            List<Thread> threadList = new ArrayList<>();

            int vendorCount = setVendorCount();
            int customerCount = setCustomerCount();

            for(int i = 0; i < vendorCount; i++){
                Thread vendor = new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease),("Vendor " + i)));
                System.out.println("Vendor " + i + "started");
                vendor.setName("Vendor - " + i);
                threadList.add(vendor);
                vendor.start();
            }

            for(int i = 0; i < customerCount; i++){
                Thread customer = new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval),("Customer " + i)));
                customer.setName("Customer - " + i);
                System.out.println("Customer " + i + "started");
                threadList.add(customer);
                customer.start();
            }

            System.out.println("Simulation running. Press Enter to stop.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine(); // Wait for Enter key

            // Stop threads
            Vendor.stopThread();
            Customer.stopThread();

            try {
                for(Thread threads: threadList){
                    threads.join();
                }
            } catch (InterruptedException e) {
                System.out.println("Error stopping threads.");
            }
            System.out.println("Simulation stopped.");int i = 0;
            while(!Vendor.getVendNameList().isEmpty() && i < Vendor.getVendNameList().size()) {
                ThreadActivityDAO.saveThreadActivity(Vendor.getVendNameList().get(i), "Vendor", Vendor.getVendTicketList().get(i));
                i++;
            }
        } else {
            System.out.println("Please setup all parameters before start the system");
        }
        return inputPassed;
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
