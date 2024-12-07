// WebSocketComponent.js
import React, { useEffect, useState } from 'react';

const WebSocketComponent = () => {
  const [currentAmount, setCurrentAmount] = useState(0);

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:8080/websocket');

    socket.onopen = () => {
      console.log('WebSocket connection established');
    };

    socket.onmessage = (event) => {
      const message = event.data;
      console.log('Message from server:', message);
      if (message.startsWith('Current Amount: ')) {
        const amount = parseInt(message.replace('Current Amount: ', ''), 10);
        setCurrentAmount(amount);
      }
    };

    socket.onclose = () => {
      console.log('WebSocket connection closed');
    };

    socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    return () => {
      socket.close();
    };
  }, []);

  return (
    <div>
      <h1>Current Amount: {currentAmount}</h1>
    </div>
  );
};

export default WebSocketComponent;