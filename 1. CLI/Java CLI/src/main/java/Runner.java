public class Runner implements Runnable{
    private int ticketCount = 0;

    public void addTicket(int amount){
        this.ticketCount += amount;
    }

    @Override
    public void run() {

    }
}
