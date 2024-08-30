import java.util.List;

public class Pastry {
    private final String type;
    private final String size;
    private final String filling;
    private final List<String> toppings;

    protected Pastry(String type, String size, String filling, List<String> toppings) {
        this.type = type;
        this.size = size;
        this.filling = filling;
        this.toppings = toppings;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getFilling() {
        return filling;
    }

    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Pastry Specifications:\n" +
               "Type: " + type + "\n" +
               "Size: " + size + "\n" +
               "Filling: " + filling + "\n" +
               "Toppings: " + String.join(", ", toppings);
    }
}