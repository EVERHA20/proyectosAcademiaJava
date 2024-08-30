import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PastryTest {

    @Test
    public void testPastryToString() {
        Pastry pastry = new Pastry("Croissant", "Medium", "Almond", Arrays.asList("Powdered Sugar", "Almond Slices"));
        String expected = "Pastry Specifications:\n" +
                          "Type: Croissant\n" +
                          "Size: Medium\n" +
                          "Filling: Almond\n" +
                          "Toppings: Powdered Sugar, Almond Slices";
        assertEquals(expected, pastry.toString());
    }

    @Test
    public void testPastryGetters() {
        Pastry pastry = new Pastry("Croissant", "Medium", "Almond", Arrays.asList("Powdered Sugar", "Almond Slices"));
        assertEquals("Croissant", pastry.getType());
        assertEquals("Medium", pastry.getSize());
        assertEquals("Almond", pastry.getFilling());
        assertEquals(Arrays.asList("Powdered Sugar", "Almond Slices"), pastry.getToppings());
    }

}
