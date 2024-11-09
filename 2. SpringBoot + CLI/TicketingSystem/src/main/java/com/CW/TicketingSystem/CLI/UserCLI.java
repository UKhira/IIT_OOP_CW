package com.CW.TicketingSystem.CLI;

import com.CW.TicketingSystem.System.Ticket;

import java.util.Scanner;
public class UserCLI {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        String[] optionArray = {"Welcome, Please select an option", "1. View Total Tickets", "2. Setup Ticket Release Rate", "3. Setup Customer Retrieval Rate", "4. Setup Max Ticket Capacity"};

        for (String option : optionArray) {
            System.out.println(option);
        }

        try {
            int primaryOption = userInput.nextInt();

            switch (primaryOption) {
                case 1:
                    System.out.println("There are  tickets currently available in system");
                    break;
                case 2: // Do something
                    break;
                case 3: // Do something
                    break;
                case 4:
                    setMaxTickets();
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Please enter the Option Number");
        }
    }

    public static int viewTotalTickets(){
        Ticket ticket = new Ticket();
        return ticket.getTicketAmount();
    }
    public static void setMaxTickets(){
        System.out.println("Enter the Max Ticket amount");
        int totalTicket = userInput.nextInt();
        Ticket ticket = new Ticket();
    }
}
