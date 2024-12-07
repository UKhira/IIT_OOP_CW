// MyWebSocketHandler.java
package com.TicketingSystem.Server.Controller;

import com.TicketingSystem.Server.Model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private TicketPool ticketPool;

    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages from the client
        String messagePayload = message.getPayload();
        // ... process the message as needed

        // Get the current amount from TicketPool
        int currentAmount = ticketPool.getCurrentAmount();

        // Send the current amount to the client
        session.sendMessage(new TextMessage("Current Amount: " + currentAmount));
    }

    public static void sendCurrentAmount(int currentAmount) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                try {
                    session.sendMessage(new TextMessage("Current Amount: " + currentAmount));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}