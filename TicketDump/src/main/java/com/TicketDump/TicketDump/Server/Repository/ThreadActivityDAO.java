package com.TicketDump.TicketDump.Server.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThreadActivityDAO {

    private static JdbcTemplate jdbcTemplate;

    // Insert a new thread activity
    public static void saveThreadActivity(String threadName, String role, int tickets) {
        String sql = "INSERT INTO thread_ticket_activity (thread_name, role, tickets) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, threadName, role, tickets);
    }

    // Update the ticket count for an existing thread
    public void updateThreadActivity(String threadName, int tickets) {
        String sql = "UPDATE thread_ticket_activity SET tickets = tickets + ? WHERE thread_name = ?";
        jdbcTemplate.update(sql, tickets, threadName);
    }

    // Check if a thread already exists
    public boolean threadExists(String threadName) {
        String sql = "SELECT COUNT(*) FROM thread_ticket_activity WHERE thread_name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, threadName);
        return count != null && count > 0;
    }
}
