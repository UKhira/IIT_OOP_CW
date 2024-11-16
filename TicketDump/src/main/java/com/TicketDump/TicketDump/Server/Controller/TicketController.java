package com.TicketDump.TicketDump.Server.Controller;

import com.TicketDump.TicketDump.Server.Model.Ticketpool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @RequestMapping("/home")
    public static synchronized int getCurrentAmount(){
        return Ticketpool.getCurrentAmount();
    }
}

