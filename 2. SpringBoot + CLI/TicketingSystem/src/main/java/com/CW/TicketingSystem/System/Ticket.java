package com.CW.TicketingSystem.System;

import org.springframework.stereotype.Component;

@Component("TicketComponent")
public class Ticket {
    private int ticketCount;

    public int getTicketAmount(){
        return ticketCount;
    }
}
