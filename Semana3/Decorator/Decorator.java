
public abstract class Decorator extends Component {
    public Component component;
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public String getDescription() {
        return component.getDescription() + (component.getDescription().isEmpty() ? "" : ", ") + accessoryName;
    }
    @Override
    public double getTotalCost() {
        return component.getTotalCost() + accessoryPrice;
    }
}
