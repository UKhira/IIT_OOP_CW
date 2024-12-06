package com.TicketingSystem.Server.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utility {
    /**
     * Pre-Defined Color Codes for use across Multiple Classes
     * @see <a href="https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/"></a>
     * */

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /**
     * Pre-Defined Logger to use across multiple classes
     * */
    public static final Logger logger = LogManager.getLogger();


}

