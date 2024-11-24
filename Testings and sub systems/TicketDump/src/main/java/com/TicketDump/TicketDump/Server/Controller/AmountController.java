package com.TicketDump.TicketDump.Server.Controller;


import com.TicketDump.TicketDump.Server.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class AmountController {

    @Autowired
    TicketService service;

    public void addTicketAmount(int amount){
        System.out.println(amount);
        service.addTicketAmount(amount);
    }
}
