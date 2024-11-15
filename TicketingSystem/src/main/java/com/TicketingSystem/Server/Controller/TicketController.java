package com.TicketingSystem.Server.Controller;

import com.TicketingSystem.Server.Model.TicketPool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @RequestMapping("/home")
    public static synchronized int getCurrentAmount(){
        return TicketPool.getCurrentAmount();
    }
}
