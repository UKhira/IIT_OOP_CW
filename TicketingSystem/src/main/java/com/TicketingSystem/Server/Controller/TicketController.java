package com.TicketingSystem.Server.Controller;

import com.TicketingSystem.Server.Model.Client;
import com.TicketingSystem.Server.Model.ThreadGenerator;
import com.TicketingSystem.Server.Model.TicketPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketEz")
@CrossOrigin
public class TicketController {
    private static final Logger logger = LogManager.getLogger();

    @PostMapping("/add")
    public String add(@RequestBody Client client){
        logger.info("Client Request Successfully fetched");
        logger.info("Max Ticket Count: " + client.getMaxAmount());
        logger.info("Release Rate " + client.getReleaseRate());
        logger.info("Retrieval Rate: " + client.getRetrievalRate());
        logger.info("Vendor Count: " + client.getVendorCount());
        logger.info("Customer Count: " + client.getCustomerCount());

        TicketPool.setMaxCapacity(client.getMaxAmount());

        ThreadGenerator thread = new ThreadGenerator();
        thread.setVendorCount(client.getVendorCount());
        thread.setCustomerCount(client.getCustomerCount());
        thread.startThreads(client.getReleaseRate(),client.getRetrievalRate());
        return "Came to BE";
    }
}
