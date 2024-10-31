public class Ticket {

    private int ticketNo;
    private String title;
    private int count;
    private double price;
    private String details;

    public Ticket(int no, String title, int count, double price, String info){
        this.ticketNo = no;
        this.title = title;
        this.count = count;
        this.price = price;
        this.details = info;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }
}
