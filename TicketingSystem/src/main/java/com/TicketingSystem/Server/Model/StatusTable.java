//package com.TicketingSystem.Server.Model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//import java.time.LocalDateTime;
//
//@Entity
//public class StatusTable {
//
//    @Id
//    private String userName;
//    private String userTye; // Vendor or Customer
//    private int tickets; // Number of tickets added/removed
//    private LocalDateTime lastUpdated; // Timestamp of the last update
//
//    public StatusTable(){}
//
//    public StatusTable(String userName){
//        this.userName = userName;
//    }
//
//    public StatusTable(String userName, String userTye, int tickets) {
//        this.userName = userName;
//        this.userTye = userTye;
//        this.tickets = tickets;
//        this.lastUpdated = LocalDateTime.now();
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserTye() {
//        return userTye;
//    }
//
//    public void setUserTye(String userTye) {
//        this.userTye = userTye;
//    }
//
//    public int getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(int tickets) {
//        this.tickets = tickets;
//    }
//
//    public LocalDateTime getLastUpdated() {
//        return lastUpdated;
//    }
//
//    public void setLastUpdated(LocalDateTime lastUpdated) {
//        this.lastUpdated = lastUpdated;
//    }
//
//}
