package com.TicketingSystem.Server.Controller;

import com.TicketingSystem.Server.Model.Client;
import com.TicketingSystem.Server.Model.ThreadGenerator;
import com.TicketingSystem.Server.Model.TicketPool;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketEz")
@CrossOrigin
public class TicketController {

    @PostMapping("/add")
    public String add(@RequestBody Client client){
        System.out.println("BE fetched");
        System.out.println(client.getMaxAmount());
        System.out.println(client.getReleaseRate());
        System.out.println(client.getRetrievalRate());
        System.out.println(client.getVendors());
        System.out.println(client.getCustomers());

        TicketPool.setMaxCapacity(client.getMaxAmount());

        ThreadGenerator thread = new ThreadGenerator();
        thread.setVendorCount(client.getVendors());
        thread.setCustomerCount(client.getCustomers());
        thread.startThreads(client.getReleaseRate(),client.getRetrievalRate());
        return "Came to BE";
    }
}
