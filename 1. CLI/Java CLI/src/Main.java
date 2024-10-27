import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // LogIn
        System.out.println("LogIn");
        System.out.println("Enter your User ID");
        String userId = scanner.nextLine();
        System.out.println("Enter Your Password");
        String password = scanner.nextLine();

        User user = new User(userId,password);
        user.setFlags();
    }
}