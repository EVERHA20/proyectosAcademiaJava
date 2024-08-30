import java.util.ArrayList;
import java.util.List;

public class Supervisor implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private String flightInfo;

    public void setFlightInfo(String flightInfo) {
        this.flightInfo = flightInfo;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(flightInfo);
        }
    }
}
