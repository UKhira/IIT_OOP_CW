import React, { useState, useEffect } from 'react';

function Label() {
  const [labelValue, setLabelValue] = useState('');

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:8080/websocket'); // Replace with your server's URL

    socket.onopen = () => {
      console.log('Connected to WebSocket server');
    };

    socket.onmessage = (event) => {
      setLabelValue(event.data);
    };

    socket.onerror = (error) => {
      console.error('WebSocket Error:', error);
    };

    return () => {
      socket.close();
    };
  }, []);

  return (
    <h1>{labelValue}</h1>
  );
}

export default Label;