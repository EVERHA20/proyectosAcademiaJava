
public class Pocion implements Objeto {
	
	private Efectividad efectividad;
	
	public Pocion(Efectividad efectividad) {
		this.efectividad = efectividad;
	}

    @Override
    public void activar() {
        switch (efectividad) {
            case BAJA:
                System.out.println("La poción cura un POCO de vida.");
                break;
            case MEDIA:
                System.out.println("La poción cura una cantidad MEDIA de vida.");
                break;
            case ALTA:
                System.out.println("La poción cura una GRAN cantidad de vida.");
                break;
        }
    }

}
