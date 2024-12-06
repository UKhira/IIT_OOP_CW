package com.TicketingSystem.Server.Service;

import com.TicketingSystem.Server.Config.ThreadGenerator;
import com.TicketingSystem.Server.Model.TicketPool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class TicketingService {

    private final ThreadGenerator threadGenerator;

    public TicketingService(ThreadGenerator threadGenerator) {
        this.threadGenerator = threadGenerator;
    }

    @RequestMapping("/home")
    public static synchronized int getCurrentAmount(){
        return TicketPool.getCurrentAmount();
    }

    @PostMapping("/setTicket/{value}")
    public ResponseEntity<TicketPool> setMaxTicket(@PathVariable("value") int value){
        TicketPool.setMaxCapacity(value);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/setUser/{release}/{retrieval}")
    public ResponseEntity<ThreadGenerator> setUsers(@PathVariable("release") int release, @PathVariable("retrieval") int retrieval){
        threadGenerator.startThreads(release,retrieval);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
