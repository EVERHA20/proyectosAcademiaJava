
public class Kitchen {
    private Chef chef = new Chef();

    public void preparePastry(Builder<Pastry> builder, PastryType type) {
        chef.preparePastry(builder, type);
    }
}