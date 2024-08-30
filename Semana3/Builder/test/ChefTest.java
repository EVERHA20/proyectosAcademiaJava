import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChefTest {

    private Chef chef;
    private PastryBuilder pastryBuilder;

    @BeforeEach
    public void setUp() {
        chef = new Chef();
        pastryBuilder = new PastryBuilder();
    }

    @Test
    public void testPrepareCinnamonRoll() {
        chef.preparePastry(pastryBuilder, PastryType.CINNAMON_ROLL);

        Pastry pastry = pastryBuilder.getResult();
        assertEquals("Cinnamon roll", pastry.getType());
        assertEquals("Large", pastry.getSize());
        assertEquals("Butter", pastry.getFilling());
        assertEquals(Arrays.asList("Powdered cinnamon"), pastry.getToppings());
    }

    @Test
    public void testPrepareConcha() {
        chef.preparePastry(pastryBuilder, PastryType.CONCHA);

        Pastry pastry = pastryBuilder.getResult();
        assertEquals("Concha", pastry.getType());
        assertEquals("Medium", pastry.getSize());
        assertEquals("None", pastry.getFilling());
        assertEquals(Arrays.asList("Chocolate Icing"), pastry.getToppings());
    }

    @Test
    public void testPrepareMuffin() {
        chef.preparePastry(pastryBuilder, PastryType.MUFFIN);

        Pastry pastry = pastryBuilder.getResult();
        assertEquals("Muffin", pastry.getType());
        assertEquals("Small", pastry.getSize());
        assertEquals("Blueberry", pastry.getFilling());
        assertEquals(Arrays.asList("Sprinkle Sparks"), pastry.getToppings());
    }

    @Test
    public void testPrepareBrownie() {
        chef.preparePastry(pastryBuilder, PastryType.BROWNIE);

        Pastry pastry = pastryBuilder.getResult();
        assertEquals("Brownie", pastry.getType());
        assertEquals("Small", pastry.getSize());
        assertEquals("Chocolate chips", pastry.getFilling());
        assertEquals(Arrays.asList("Almond Topping"), pastry.getToppings());
    }
}