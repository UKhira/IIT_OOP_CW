import React, { useState } from 'react';
import InputField from '../InputField/InputField.jsx';
import UserCount from '../Ven_Cus/UserCount.jsx';
import './Form.css'

function Form(){

    const [releaseRate, setReleaseRate] = useState('');
    const [retrievalRate, setRetrievalRate] = useState('');
    const [maxAmount, setMaxAmount] = useState('');
    const [vendorCount, setVendorCount] = useState('');
    const [customerCount, setCustomerCount] = useState('');


    function submitHandler(event){
        event.preventDefault();
        const ticket = {releaseRate, retrievalRate, maxAmount, vendorCount, customerCount};
        fetch("http://localhost:8080/ticketEz/add", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ticket),
        }).then(() => {
            console.log('New ticket added');
        });
    }

    return(
        <form className="container">
            <div className="brand-logo"></div>
            <div className="brand-title">🎟️ TicketEZ 🎫</div>
            <div className="inputs">
                
                <label>Release Rate</label>
                <InputField prompt = "The Max Ticket Amount that a Vendor would add" onChange={(e) => setReleaseRate(e.target.value)}/>
                <label>Retrival Rate</label>
                <InputField prompt = "The Max Ticket Amount that a Customer would buy" onChange={(e) => setRetrievalRate(e.target.value)}/>
                <label>Max Amount</label>
                <InputField prompt = "The Max Ticket Amount that Ticketpool can have" onChange={(e) => setMaxAmount(e.target.value)}/>

                <button type="submit" onClick={submitHandler}>Start ▶️</button>
                
                <label>Vendors</label>
                <InputField prompt = "Vendor Count" onChange={(e) => setVendorCount(e.target.value)}/>

                <label>Customers</label>
                <InputField prompt = "Customer Count" onChange={(e) => setCustomerCount(e.target.value)}/>

            </div>
        </form>
    );
}

export default Form