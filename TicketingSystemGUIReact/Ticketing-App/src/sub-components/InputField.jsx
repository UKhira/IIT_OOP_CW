import '../styles/InputField.css';

function InputField(props){
    return(
        <input type="number" id = "field" placeholder={props.prompt} title="Please enter a numeric value higher than 0"  onChange={props.onChange} required/>
    );
}


export default InputField