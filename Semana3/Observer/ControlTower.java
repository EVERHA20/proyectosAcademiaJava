
public class ControlTower {

	public static void main(String[] args) {
		Supervisor supervisor = new Supervisor();

		Airport ap1 = new Airport("CDMX");
		Airport ap2 = new Airport("Monterrey");
		Airport ap3 = new Airport("Cancún");
		supervisor.attach(ap1);
		supervisor.attach(ap2);
		supervisor.setFlightInfo("Flight 33 has arrived.");
		supervisor.setFlightInfo("Flight 454 is departing.");
		System.out.println("********");
		supervisor.detach(ap2);
		supervisor.attach(ap3);
		supervisor.setFlightInfo("Flight 619 is departing.");
	}

}
