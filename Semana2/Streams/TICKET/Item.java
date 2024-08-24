public class Item {
    private String name;
    private double price;
    private int quantity;
    private double totalPrice;
    private String disc;

    public Item(String name, double price, int quantity) {
        this(name, price, quantity, price * quantity, null);
    }

    public Item(String name, double price, int quantity, double totalPrice, String disc) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.disc = disc;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public String getDisc() {
        return disc;
    }

    @Override
    public String toString() {
        return String.format("%-20s $%-8.2f %-8d $%.2f %s", name, price, quantity, totalPrice, disc != null ? disc : "");    }
}