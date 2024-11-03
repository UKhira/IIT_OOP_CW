package CredentialTest;

import java.util.HashMap;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        HashMap<Integer, String> hashmap = new HashMap<>();
        hashmap.put(1234,"user");
        hashmap.put(5678,"vendor");
        hashmap.put(9101, "admin");

        Scanner credentials = new Scanner(System.in);

        System.out.println("Enter userName ");
        int username = credentials.nextInt();

        System.out.println("Enter Password ");
        String password = credentials.next();

        if(!hashmap.containsKey(username)){
            System.out.println("Invalid Username");
        }
        else{
            if (hashmap.get(username).equals(password)){
                System.out.println("Login success");
            }
            else {
                System.out.println("Invalid password");
            }
        }
    }
}
