public class Vendor extends User{

    private Ticket ticketLis;
    public Vendor(){}

    public Vendor(String userId, String password){
        super(userId,password);
    }

    public void createTicket(){
        System.out.println();
    }

    public void viewCreatedTickets(){

    }
}
