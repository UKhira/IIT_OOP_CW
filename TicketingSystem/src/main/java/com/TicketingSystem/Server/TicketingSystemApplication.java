package com.TicketingSystem.Server;

import com.TicketingSystem.Client.CLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories
public class TicketingSystemApplication {

	/**
	 * This is the main method of Application. By this method user can run the server and as his/her preference open the CLI.
	 * @since 1.5
	 */
	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
		System.out.println("Server has started");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Do You want to open CLI (Y/N) ?");

		String input = scanner.next();
		if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) {
			runCLI();
		}
		else
			System.out.println("Continuing without opening CLI...");
		scanner.close();
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
