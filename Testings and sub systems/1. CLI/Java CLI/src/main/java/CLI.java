package main.java;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class CLI{

    private static Scanner userInput = new Scanner(System.in);
    private static boolean inputPassed = false;
    private static String[] optionArray = {
            "*  Welcome, Please select an option       *",
            "*  1. View Total Tickets                  *",
            "*  2. Setup Ticket Release Rate           *",
            "*  3. Setup Customer Retrieval Rate       *",
            "*  4. Setup Max Ticket Capacity           *",
            "*  5. Exit and Start The System           *"};
    private static int customerRetrieval,ticketRelease;


    // ** Initiate User Terminal Options ** //
    public static void main(String[] args) throws IOException {
        while (!inputPassed) {
            try {
                showOptions();
                int primaryOption = userInput.nextInt();

                switch (primaryOption) {
                    case 1:
                        System.out.println("There are " + TicketPool.getCurrentAmount() + " tickets currently available in system");
                        updateOptionMenu(1);
                        break;
                    case 2: setTicketReleaseRate();
                        break;
                    case 3: setCustomerRetrievalRate();
                        break;
                    case 4:
                        setMaxTickets();
                        break;
                    case 5:
                        startThreads();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } catch (Exception exception) {
                System.out.println("Please enter the Option Number");
                userInput.nextLine();           //If an invalid value contain in scanner it needed to be clear
            }
           /* finally {
                config.writeFile();
            }*/
        }
    }

    private static void showOptions(){
        for (int i = 0; i <= 10; i++){
            System.out.print("*\t");
        }
        System.out.println();
        for (String option : optionArray) {
            System.out.println(option);
        }
        for (int i = 0; i <= 10; i++){
            System.out.print("*\t");
        }
        System.out.println();
    }

    private static void updateOptionMenu(int index){
        if(!optionArray[index].contains("[Done]"))
            optionArray[index] = optionArray[index] + " [Done]";
    }

    private static void setMaxTickets(){
        System.out.println("Enter the Max Ticket amount");
        if(userInput.hasNextInt()){
            TicketPool.setMaxCapacity(userInput.nextInt());
            updateOptionMenu(4);
        }
        else{
            System.out.println("Please enter a Integer value");
        }
    }

    private static void setTicketReleaseRate(){
        System.out.println("Enter the Highest Limit Ticket Amount which a Vendor can add");
        if(userInput.hasNextInt()) {
            updateOptionMenu(2);
            ticketRelease = userInput.nextInt();
        }
        else
            System.out.println("Invalid input");
    }

    private static void setCustomerRetrievalRate(){
        System.out.println("Enter the Highest Limit Ticket Amount which a Customer can buy");
        if(userInput.hasNextInt()) {
            updateOptionMenu(3);
             customerRetrieval = userInput.nextInt();
        }
        else
            System.out.println("Invalid input");
    }

    private static void startThreads() throws SQLException {

        // Check whether all the tasks is done in option menu
        int checker = 0;
        for (String option : optionArray) {
            if(option.contains("Done"))
                checker++;
        }
        if(checker == 4){
            // Stop the Option Menu
            inputPassed = true;

            // Connect to Database
            Database.connect();

            // Generate Random Ticket Amounts
            Random randomNumber = new Random();
            TicketPool ticketPool = new TicketPool();

            // Customer and Vendor Threads
            System.out.println("System is running. Enter something to stop running");
//            Thread vendorThread = new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease)));
//            Thread customerThread = new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval)));

            do {
                for (int i = 0; i < 5; i++) {
                    new Thread(new Vendor(ticketPool, randomNumber.nextInt(1, ticketRelease))).start();
                    new Thread(new Customer(ticketPool, randomNumber.nextInt(1, customerRetrieval))).start();
                }
            }
            while (!userInput.hasNext());
            TicketPool.showList();

//            for(int i = TicketPool.getCurrentAmount(); i < TicketPool.getMaxCapacity(); i++){
//                new Thread(new Vendor(ticketPool, randomNumber.nextInt(1,ticketRelease))).start();
//                new Thread(new Customer(ticketPool, randomNumber.nextInt(1,customerRetrieval))).start();
//            }
        }
        else
            System.out.println("Please setup all parameters before start the system");
    }
}

