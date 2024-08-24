import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
            new Item("Cereal", 83.90, 1),
            new Item("Mayonnaise", 80.90, 2),
            new Item("Rice 1kg", 36.90, 3),
            new Item("Nutrioli Oil 1lt", 35.90, 5),
            new Item("Coffee", 133.00, 1),
            new Item("Milk 1lt", 28.50, 1),
            new Item("24 Egg pack", 88.90, 1),
            new Item("Juice 200ml", 7.50, 24)
        );

        System.out.printf("%-20s %-10s %-10s %-10s%n", "Item", "Price", "Quantity", "Subtotal");
        System.out.printf("%-20s %-10s %-10s %-10s%n", "", "", "", "10% Discount if more than 5 Items");
        printTicket(items);
    }

    private static void printTicket(List<Item> items) {
        List<Item> updatedItems = items.stream()
            .map(Discount::applyDiscount)
            .sorted((item1, item2) -> item1.getName().compareTo(item2.getName()))
            .peek(item -> System.out.println(item))
            .collect(Collectors.toList());

        double total = updatedItems.stream()
            .mapToDouble(Item::getTotalPrice)
            .sum();

        System.out.printf("%n%-20s $%.2f%n", "Total", total);
    }
}