package com.TicketingSystem.Client;

import com.TicketingSystem.Server.Config.SysConfig;
import com.TicketingSystem.Server.Config.ThreadGenerator;
import com.TicketingSystem.Server.Model.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.TicketingSystem.Server.Config.Utility.*;

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
                        loadDataFromJson();
                        System.out.println(ANSI_YELLOW + "There are " + TicketPool.getCurrentAmount() + " tickets currently available in system");
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
                        System.out.println(ANSI_RED + "Invalid Input");
                        break;
                }
            } catch (Exception exception) {
                System.out.println(ANSI_RED + "Please enter the Option Number");
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
            System.out.print(ANSI_BLUE + "*\t");
        }
        System.out.println();
        for (String option : optionArray) {
            System.out.println(ANSI_BLUE + option);
        }
        for (int i = 0; i <= 10; i++){
            System.out.print(ANSI_BLUE + "*\t");
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
        System.out.println(ANSI_RESET + "Enter the Max Ticket amount");
        if(userInput.hasNextInt()){
            TicketPool.setMaxCapacity(userInput.nextInt());
            updateOptionMenu(4);
        }
        else{
            System.out.println(ANSI_RED + "Please enter a Integer value");
        }
    }

    /**
     * This method will set the ticket release rate (Assumption - This means a max ticket amount which vendor will be added through simulation)
     * @since 1.0
     */
    private static void setTicketReleaseRate(){
        System.out.println(ANSI_RESET + "Enter the Highest Limit Ticket Amount which a Vendor can add");
        if(userInput.hasNextInt()) {
            updateOptionMenu(2);
            ticketRelease = userInput.nextInt();
        }
        else
            System.out.println(ANSI_RED + "Invalid input");
    }

    /**
     * This method will set the customer retrieval rate (Assumption - This means a max ticket amount which customer would be bought through simulation)
     * @since 1.0
     */
    private static void setCustomerRetrievalRate(){
        System.out.println(ANSI_RESET + "Enter the Highest Limit Ticket Amount which a Customer can buy");
        if(userInput.hasNextInt()) {
            updateOptionMenu(3);
            customerRetrieval = userInput.nextInt();
        }
        else
            System.out.println(ANSI_RED + "Invalid input");
    }

    /**
     * This method will check whether user has been set all parameter values before starting simulation. If any value is missing then loop back the option Menu. If all are done then will be directed to simulation
     * @since 1.4*/
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
            System.out.println(ANSI_RED + "Please setup all parameters before start the system");
        }
    }


    /**
     * Write setup configuration to a JSON file
     * @see <a href="https://mkyong.com/java/read-and-write-json-to-file-using-gson/">Gson</a>
     * @since 1.8
     */
    public static void saveConfiguration(int amount) {
        SysConfig config = new SysConfig();
        config.setCurrentAmount(amount);
        config.setCustomerRetrievalRate(customerRetrieval);
        config.setTicketReleaseRate(ticketRelease);
        config.setMaxTicketCapacity(TicketPool.getMaxCapacity());
        Gson json = new Gson();

        try {
            Writer writer = new FileWriter("Configuration.json");
            json.toJson(config, writer);
            writer.close();
            logger.info(ANSI_YELLOW + "Configurations saved successfully");
        }
        catch (Exception e){
            logger.warn(ANSI_RED + "Failed to save configuration");
        }
    }

    public void loadDataFromJson(){
        try{
            String jsonFile = new String(Files.readAllBytes(Paths.get("Configuration.json")));
            JSONObject jsonObject = new JSONObject(jsonFile);

            TicketPool.setCurrentAmount(jsonObject.getInt("currentAmount"));
        }
        catch (Exception e) {
            System.out.println(ANSI_RED + "No Data");
            TicketPool.setCurrentAmount(0);
        }
    }
}

