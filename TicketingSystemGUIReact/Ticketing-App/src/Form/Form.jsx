import InputField from '../InputField/InputField';
import './Form.css'

function Form(){
    return(
        <div class="container">
            <div class="brand-logo"></div>
            <div class="brand-title">TicketEZ</div>
            <div class="inputs">
                
                <label>Release Rate</label>
                <InputField prompt = "The Max Ticket Amount that a Vendor would add"/>
                <label>Retrival Rate</label>
                <InputField prompt = "The Max Ticket Amount that a Customer would buy"/>
                <label>Max Amount</label>
                <InputField prompt = "The Max Ticket Amount that Ticketpool can have"/>

                <button type="submit">Start</button>

            </div>
        </div>
    );
}

export default Form