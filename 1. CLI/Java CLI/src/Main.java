import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // LogIn
        System.out.println("Select an Option");
        System.out.println("Press 1 to Log in as a Vendor");
        System.out.println("Press 2 to Log in as a Customer");
        int selection1 = scanner.nextInt();

        switch (selection1){
            case 1:
                Vendor vendor = new Vendor();
                System.out.println("Select what you need to do");
                System.out.println("1. Create Tickets");
                System.out.println("View created tickets");
                int selection2 = scanner.nextInt();
                switch (selection1){
                    case 1:
                        vendor.createTicket();
                        break;
                    case 2:
                        vendor.viewCreatedTickets();
                        break;
                }
            case 2:
                //User
            default:
                System.out.println("Invalid login");
                break;
        }
    }
}