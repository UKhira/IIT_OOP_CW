package com.TicketingSystem.Server.Model;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class ThreadGenerator {

    public static boolean startThreads(String[] optionArray, int ticketRelease, int customerRetrieval) throws SQLException {
        boolean inputPassed = false;
        Scanner userInput = new Scanner(System.in);

        // Check whether all the tasks is done in option menu
        int checker = 0;
        for (String option : optionArray) {
            if(option.contains("Done"))
                checker++;
        }
        if(checker == 4) {
            // Stop the Option Menu
            inputPassed = true;

            // Connect to Database
//            Database.connect();

            // Generate Random Ticket Amounts
            Random randomNumber = new Random();
            TicketPool ticketPool = new TicketPool();

            // Customer and Vendor Threads
            System.out.println("System is running. Enter something to stop running");

            // Reference - https://stackoverflow.com/questions/45039819/java-how-to-wait-until-thread-exits-or-user-presses-enter
            Thread inputThread = new Thread(() -> {
                System.out.println("Press Enter to stop...");
                userInput.nextLine(); // Wait for user to press Enter
                System.out.println("User has pressed Enter, Stopping the simulation");
                System.exit(0); // Terminate the program
            });
            inputThread.start();

            // Generate Vendor and Customer threads continuously
            while (true) {
                new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease))).start();
                new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval))).start();

                try {
                    Thread.sleep(500); // Small delay to prevent overwhelming thread creation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.out.println("Thread interrupted: " + e.getMessage());
                    break;
                }
            }
        }
        else{
                System.out.println("Please setup all parameters before start the system");
            }
        return inputPassed;
    }
}
