import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class KitchenTest {

    private Builder<Pastry> builder;
    private Kitchen kitchen;

    @BeforeEach
    public void setUp() {
        builder = new PastryBuilder();
        kitchen = new Kitchen();
    }

    @Test
    public void testPreparePastry_Muffin() {
        kitchen.preparePastry(builder, PastryType.MUFFIN);
        Pastry pastry = builder.getResult();

        assertEquals("Muffin", pastry.getType());
        assertEquals("Small", pastry.getSize());
        assertEquals("Blueberry", pastry.getFilling());
        assertEquals(Arrays.asList("Sprinkle Sparks"), pastry.getToppings());
    }

    @Test
    public void testPreparePastry_CinnamonRoll() {
        kitchen.preparePastry(builder, PastryType.CINNAMON_ROLL);
        Pastry pastry = builder.getResult();

        assertEquals("Cinnamon roll", pastry.getType());
        assertEquals("Large", pastry.getSize());
        assertEquals("Butter", pastry.getFilling());
        assertEquals(Arrays.asList("Powdered cinnamon"), pastry.getToppings());
    }

    @Test
    public void testPreparePastry_Concha() {
        kitchen.preparePastry(builder, PastryType.CONCHA);
        Pastry pastry = builder.getResult();

        assertEquals("Concha", pastry.getType());
        assertEquals("Medium", pastry.getSize());
        assertEquals("None", pastry.getFilling());
        assertEquals(Arrays.asList("Chocolate Icing"), pastry.getToppings());
    }

    @Test
    public void testPreparePastry_Brownie() {
        kitchen.preparePastry(builder, PastryType.BROWNIE);
        Pastry pastry = builder.getResult();

        assertEquals("Brownie", pastry.getType());
        assertEquals("Small", pastry.getSize());
        assertEquals("Chocolate chips", pastry.getFilling());
        assertEquals(Arrays.asList("Almond Topping"), pastry.getToppings());
    }
}
