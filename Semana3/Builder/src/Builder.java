
public interface Builder<T> {
    Builder<T> reset();
    Builder<T> setType(String type);
    Builder<T> setSize(String size);
    Builder<T> setFilling(String filling);
    Builder<T> setToppings(String... toppings);
    T getResult();
}