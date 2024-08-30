import java.util.Objects;

public class Airport implements Observer{
    private String airport;

    public Airport(String name) {
        this.airport = name;
    }

    @Override
    public void update(String flightInfo) {
        System.out.println("ControlTower " + airport + " received flight info: " + flightInfo);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		return Objects.equals(airport, other.airport);
	}
}

