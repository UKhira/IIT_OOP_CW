function InputField(props){
    return(
        <input type="number" id = "field" placeholder={props.prompt} title="Please enter a numeric value higher than 0" required/>
    );
}


export default InputField