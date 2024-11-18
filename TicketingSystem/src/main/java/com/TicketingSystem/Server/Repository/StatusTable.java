package com.TicketingSystem.Server.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class StatusTable {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public void saveThreadActivity(String threadName, String role, int tickets) {
        String sql = "INSERT INTO ticket_pool_activity (thread_name, act, tickets) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, threadName, role, tickets);
    }

    public void updateThreadActivity(String threadName, int tickets) {
        String sql = "UPDATE thread_ticket_activity SET tickets = tickets + ? WHERE thread_name = ?";
        jdbcTemplate.update(sql, tickets, threadName);
    }

    public boolean threadExists(String threadName) {
        String sql = "SELECT COUNT(*) FROM thread_ticket_activity WHERE thread_name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, threadName);
        return count != null && count > 0;
    }

    public static void updateTicketCount(int amount, String value){
        System.out.println("Came to query");
        String sql = "INSERT INTO ticketcount (current_amount,ticketname) VALUE (?,?)";
        jdbcTemplate.update(sql,amount, value);
        System.out.println("Exit the query");
    }
}
