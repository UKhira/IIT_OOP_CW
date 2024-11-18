package com.TicketDump.TicketDump.Server;

import com.TicketDump.TicketDump.CLI.CLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class TicketDump {

    public static void main(String[] args) {
        SpringApplication.run(TicketDump.class, args);
        System.out.println("Server has started");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do You want to open CLI (Y/N) ?");

        String input = scanner.next();
        if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes"))
            runCLI();
        else
            System.out.println("Continuing without opening CLI...");
    }

    /**
     * This method will call to runCLI() method and open the CLI depends on the user input on main method
     * @since 1.5
     */
    private static void runCLI() {
        System.out.println("Opening CLI...");
        CLI terminal = new CLI();
        terminal.openCLI();
    }
}
