package com.TicketDump.TicketDump.Server.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "status_table")
public class Table {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_type")
    private String type;

    @Column(name = "ticket")
    private int ticketCount;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public Table(String userName, String type, int ticketCount) {
        this.userName = userName;
        this.type = type;
        this.ticketCount = ticketCount;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }
}
