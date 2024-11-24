package com.TicketDump.TicketDump.CLI;

import com.TicketDump.TicketDump.Server.Model.*;
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

    /**
     * This is the Pilot Method of CLI. This method will control the user interaction with CLI Menu. While getting and providing necessary values from/to other classes
     * @see <a href="https://www.baeldung.com/javadoc">JavaDoc Reference</a>
     * @since 1.0
     **/
    public void openCLI() {
        while (!inputPassed) {
            try {
                showOptions();
                int primaryOption = userInput.nextInt();

                switch (primaryOption) {
                    case 1:
                        System.out.println("There are " + Ticketpool.getCurrentAmount() + " tickets currently available in system");
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
                        checkUpdate();
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

    /**
     * This is the Method to show Option Menu
     * @since 1.0
     */
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

    /**
     * This is the method to check each parameter and mark as 'Done' once user set values accurately. So it can keep track whether the all configurations have been set to start the simulation
     * @param index This will get the index of each option in option menu
     * @since 1.1
     */
    private static void updateOptionMenu(int index){
        if(!optionArray[index].contains("[Done]"))
            optionArray[index] = optionArray[index] + " [Done]";
    }

    /**
     * This method will set the Max Tickets that can hold in Ticket pool according to user input. and set it in TicketPool Class
     * @since 1.0
     */
    private static void setMaxTickets(){
        System.out.println("Enter the Max Ticket amount");
        if(userInput.hasNextInt()){
            Ticketpool.setMaxCapacity(userInput.nextInt());
            updateOptionMenu(4);
        }
        else{
            System.out.println("Please enter a Integer value");
        }
    }

    /**
     * This method will set the ticket release rate (Assumption - This means a max ticket amount which vendor will be added through simulation)
     * @since 1.0
     */
    private static void setTicketReleaseRate(){
        System.out.println("Enter the Highest Limit Ticket Amount which a Vendor can add");
        if(userInput.hasNextInt()) {
            updateOptionMenu(2);
            ticketRelease = userInput.nextInt();
        }
        else
            System.out.println("Invalid input");
    }

    /**
     * This method will set the customer retrieval rate (Assumption - This means a max ticket amount which customer would be bought through simulation)
     * @since 1.0
     */
    private static void setCustomerRetrievalRate(){
        System.out.println("Enter the Highest Limit Ticket Amount which a Customer can buy");
        if(userInput.hasNextInt()) {
            updateOptionMenu(3);
            customerRetrieval = userInput.nextInt();
        }
        else
            System.out.println("Invalid input");
    }

    public void checkUpdate() {

        // Check whether all the tasks is done in option menu
        int checker = 0;
        for (String option : optionArray) {
            if (option.contains("Done"))
                checker++;
        }
        if (checker == 4) {

            // Stop the showOptionMenu() Loop
            inputPassed = true;
            ThreadGenerator threadGenerator = new ThreadGenerator();
            threadGenerator.startThreads(ticketRelease,customerRetrieval);
        }
        else {
            System.out.println("Please setup all parameters before start the system");
        }

    }
}

