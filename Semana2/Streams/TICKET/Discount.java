public class Discount {
	public static Item applyDiscount(Item item) {
    double subtotal = item.getTotalPrice();
    String disc = null;
    if (item.getQuantity() >= 5) {
        subtotal *= 0.90;
        disc = "10% discount";
    	}
    return new Item(item.getName(), item.getPrice(), item.getQuantity(), subtotal, disc);	}
}