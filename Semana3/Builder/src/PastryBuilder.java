import java.util.Arrays;

public class PastryBuilder implements Builder<Pastry> {
    private String type;
    private String size;
    private String filling;
    private String[] toppings = new String[0];

    @Override
    public PastryBuilder reset() {
        this.type = null;
        this.size = null;
        this.filling = null;
        this.toppings = new String[0];
        return this;
    }

    @Override
    public PastryBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public PastryBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    @Override
    public PastryBuilder setFilling(String filling) {
        this.filling = filling;
        return this;
    }

    @Override
    public PastryBuilder setToppings(String... toppings) {
        this.toppings = toppings;
        return this;
    }

    @Override
    public Pastry getResult() {
        return new Pastry(type, size, filling, Arrays.asList(toppings));
    }
}