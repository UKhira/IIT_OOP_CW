package com.TicketingSystem.Server.Controller;

import com.TicketingSystem.Server.Config.ThreadGenerator;
import com.TicketingSystem.Server.Model.TicketPool;

import org.springframework.web.bind.annotation.*;

import static com.TicketingSystem.Server.Config.Utility.*;

@RestController
@RequestMapping("/ticketEz")
@CrossOrigin
public class TicketController {

    @PostMapping("/add")
    public String add(@RequestBody Client client){
        logger.info(ANSI_GREEN + "Client Request Successfully fetched");
        logger.info(ANSI_CYAN + "Max Ticket Count: " + client.getMaxAmount());
        logger.info(ANSI_CYAN + "Release Rate " + client.getReleaseRate());
        logger.info(ANSI_CYAN + "Retrieval Rate: " + client.getRetrievalRate());
        logger.info(ANSI_CYAN + "Vendor Count: " + client.getVendorCount());
        logger.info(ANSI_CYAN +"Customer Count: " + client.getCustomerCount());

        TicketPool.setMaxCapacity(client.getMaxAmount());

        ThreadGenerator thread = new ThreadGenerator();
        thread.setVendorCount(client.getVendorCount());
        thread.setCustomerCount(client.getCustomerCount());
        thread.startThreads(client.getReleaseRate(),client.getRetrievalRate());
        return "Came to BE";
    }
}
