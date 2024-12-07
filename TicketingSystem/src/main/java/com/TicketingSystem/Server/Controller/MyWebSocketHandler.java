package com.TicketingSystem.Server.Controller;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            // Handle incoming messages from the client
            String messagePayload = message.getPayload();
            // ... process the message as needed

            // Broadcast a message to all connected clients
            session.sendMessage(new TextMessage("Hello from the server!"));
        }
}

