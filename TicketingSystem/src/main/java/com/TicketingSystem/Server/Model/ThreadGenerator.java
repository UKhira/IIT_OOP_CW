package com.TicketingSystem.Server.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Scanner;

@Component
public class ThreadGenerator {

    public ThreadGenerator() {}

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

            TicketPool ticketPool = new TicketPool();

            // Reference - https://stackoverflow.com/questions/45039819/java-how-to-wait-until-thread-exits-or-user-presses-enter
            Thread inputThread = new Thread(() -> {
                System.out.println("Press Enter to stop...");
                userInput.nextLine(); // Wait for user to press Enter
                System.out.println("User has pressed Enter, Stopping the simulation");
                System.exit(0); // Terminate the program
            });
            inputThread.start();
//
//            // Generate Vendor and Customer threads continuously
            while (true) {
                new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease))).start();
                new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval))).start();


//  Reference : https://stackoverflow.com/questions/4906799/why-invoke-thread-currentthread-interrupt-in-a-catch-interruptexception-block
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.out.println("Thread interrupted: " + e.getMessage());
                    break;
                }
            }
        } else {
            System.out.println("Please setup all parameters before start the system");
        }
        return inputPassed;
    }

        private void setUpSimulation(){
            Scanner in = new Scanner(System.in);
            boolean flag = true;
            while (flag){
                System.out.println("To Start Simulation, Please enter set the following parameters. \nEnter Customer Count: ");
                if(in.hasNextInt() && in.nextInt() > 0){
                    int customer = in.nextInt();
                    System.out.println("Enter Vendor Count");
                    if(in.hasNextInt() && in.nextInt() > 0) {
                        int vendor = in.nextInt();
                        int[] count = {customer, vendor};
                        returnNumber(count);
                        flag = false;
                    }
                    else
                        System.out.println("Please enter a valid value");
                }
                else
                    System.out.println("Please enter a valid value");
            }
        }

        private int[] returnNumber(int[] num){
            return num;
        }
}
