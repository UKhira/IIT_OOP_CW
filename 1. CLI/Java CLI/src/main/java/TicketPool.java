public class TicketPool {
    private int id;
    private double price;
    private static int currentAmount;
    public static int maxCapacity;

    public static void setMaxCapacity(int amount){
        maxCapacity = amount;
    }

    public static synchronized int getCurrentAmount(){
        return currentAmount;
    }

    public synchronized void addTickets(int amount){
        if(currentAmount + amount <= maxCapacity) {
            currentAmount += amount;
            System.out.println("Tickets in Pool " + currentAmount);
        }
        else
            System.out.println("You can only add another " + (maxCapacity - currentAmount) + " tickets or less than that");
    }

    public synchronized void buyTickets(int amount){
        if(currentAmount - amount >= 0) {
            currentAmount -= amount;
            System.out.println("Tickets in Pool: " + currentAmount);
        }
        else
            System.out.println("You can buy only " + currentAmount + " or less than that");
    }
}
