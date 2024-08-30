
public abstract class Component {
    public String accessoryName = "";
    public double accessoryPrice = 0.0;
    public Component() {}
    public Component(Component component) {
        if (component != null) {
            this.accessoryName = component.accessoryName;
            this.accessoryPrice = component.accessoryPrice;
        }
    }

    public String getDescription() {
        return accessoryName;
    }

    public double getTotalCost() {
        return accessoryPrice;
    }
}
