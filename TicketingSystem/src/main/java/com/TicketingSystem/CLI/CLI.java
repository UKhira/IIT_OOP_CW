package com.TicketingSystem.CLI;

import com.TicketingSystem.Server.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class CLI{

    private static final Scanner userInput = new Scanner(System.in);
    private static boolean inputPassed = false;
    private static final String[] optionArray = {
            "*  Welcome, Please select an option       *",
            "*  1. View Total Tickets                  *",
            "*  2. Setup Ticket Release Rate           *",
            "*  3. Setup Customer Retrieval Rate       *",
            "*  4. Setup Max Ticket Capacity           *",
            "*  5. Exit and Start The System           *"};
    private static int customerRetrieval,ticketRelease;

    // ** Initiate User Terminal Options ** //
    public void openCLI() {
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
                        ThreadGenerator threadGenerator = new ThreadGenerator();
                        inputPassed = threadGenerator.startThreads(optionArray, ticketRelease, customerRetrieval);
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            } catch (Exception exception) {
                System.out.println("Please enter the Option Number");
                userInput.nextLine();           //If an invalid value contain in scanner it needed to be clear
            }
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
}

