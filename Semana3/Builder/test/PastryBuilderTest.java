import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PastryBuilderTest {

    @Test
    public void testBuildPastry() {
        Builder<Pastry> builder = new PastryBuilder();
        Pastry pastry = builder
            .reset()
            .setType("Danish")
            .setSize("Large")
            .setFilling("Fruit")
            .setToppings("Icing Sugar", "Almond Slices")
            .getResult();

        assertEquals("Danish", pastry.getType());
        assertEquals("Large", pastry.getSize());
        assertEquals("Fruit", pastry.getFilling());
        assertEquals(Arrays.asList("Icing Sugar", "Almond Slices"), pastry.getToppings());
    }

    @Test
    public void testReset() {
        Builder<Pastry> builder = new PastryBuilder();
        builder.setType("Cupcake").setSize("Small").setFilling("Chocolate").setToppings("Sprinkles");
        Pastry pastry = builder.getResult();
        assertEquals("Cupcake", pastry.getType());

        builder.reset();
        builder.setType("Muffin").setSize("Medium").setFilling("Blueberry").setToppings("Sugar Crystals");
        pastry = builder.getResult();
        assertEquals("Muffin", pastry.getType());
        assertEquals("Medium", pastry.getSize());
        assertEquals("Blueberry", pastry.getFilling());
        assertEquals(Arrays.asList("Sugar Crystals"), pastry.getToppings());
    }

}
