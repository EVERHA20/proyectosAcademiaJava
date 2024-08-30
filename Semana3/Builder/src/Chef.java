
public class Chef {
    public void preparePastry(Builder<Pastry> builder, PastryType type) {
        switch (type) {
            case CINNAMON_ROLL:
                builder.reset()
                    .setType("Cinnamon roll")
                    .setSize("Large")
                    .setFilling("Butter")
                    .setToppings("Powdered cinnamon");
                break;
            case CONCHA:
                builder.reset()
                    .setType("Concha")
                    .setSize("Medium")
                    .setFilling("None")
                    .setToppings("Chocolate Icing");
                break;
            case MUFFIN:
                builder.reset()
                    .setType("Muffin")
                    .setSize("Small")
                    .setFilling("Blueberry")
                    .setToppings("Sprinkle Sparks");
                break;
            case BROWNIE:
                builder.reset()
                    .setType("Brownie")
                    .setSize("Small")
                    .setFilling("Chocolate chips")
                    .setToppings("Almond Topping");
                break;
        }
    }
}
