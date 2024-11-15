package com.TicketingSystem.Server;

import com.TicketingSystem.CLI.CLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TicketingSystemApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select an Option: \n1. Run CLI \n2. Run Server");

		if(scanner.hasNextInt()){
			int input = scanner.nextInt();

			switch (input) {
				case 1 :
					runCLI();
					break;
				case 2:
					runServer(args);
					break;
				default:
					System.out.println("Invalid choice. Please select 1 or 2.");
					break;
			}
		}
		else
			System.out.println("Invalid Input. Please enter a number.");
	}

	private static void runCLI() {
		System.out.println("Opening CLI...");
		CLI terminal = new CLI();
		terminal.openCLI();
	}

	private static void runServer(String[] args) {
		System.out.println("Starting Server...");
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

}
