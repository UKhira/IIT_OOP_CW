import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;

public class CLI{

    private static Scanner userInput = new Scanner(System.in);
    public static int maxTicketAmount;
    public static int totalAvailableTickets = 0;
    private static boolean inputPassed = false;
    private static String[] optionArray = {"Welcome, Please select an option", "1. View Total Tickets", "2. Setup Ticket Release Rate", "3. Setup Customer Retrieval Rate", "4. Setup Max Ticket Capacity", "5. Exit and Start The System"};


    // ** Initiate User Terminal Options ** //
    public static void main(String[] args) throws IOException {
        TicketPool ticketPool = new TicketPool();
        while (!inputPassed) {
            try {
                showOptions();
                int primaryOption = userInput.nextInt();

                switch (primaryOption) {
                    case 1:
                        System.out.println("There are " + TicketPool.getCurrentAmount() + " tickets currently available in system");
                        updateOptionMenu(1);
                        break;
                    case 2: // Do something
                        break;
                    case 3: // Do something
                        break;
                    case 4:
                        setMaxTickets();
                        break;
                    case 5:
                        startThreads();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        inputPassed = false;
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
        for (String option : optionArray) {
            System.out.println(option);
        }
    }

    private static void updateOptionMenu(int index){
        if(!optionArray[index].contains("[Done]"))
            optionArray[index] = optionArray[index] + " [Done]";
    }

    private static void setMaxTickets(){
        System.out.println("Enter the Max Ticket amount");
        if(userInput.hasNextInt()){
            TicketPool.setMaxCapacity(userInput.nextInt());
            updateOptionMenu(2);
        }
        else{
            System.out.println("Please enter a Integer value");
        }
    }

    private static void startThreads(){
//        int checker = 0;
//        for (String option : optionArray) {
//            if(option.contains("Done"))
//                checker++;
//        }
//        if(checker == 4){
            TicketPool ticketPool = new TicketPool();

            for(int i = TicketPool.getCurrentAmount(); i < TicketPool.maxCapacity; i++){
                new Thread(new Vendor(ticketPool, 40)).start();
                new Thread(new Customer(ticketPool, 30)).start();
            }
//        }
//        else
//            System.out.println("Please setup all parameters before start the system");
    }
}
