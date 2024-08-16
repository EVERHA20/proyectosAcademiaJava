
public class Amuleto implements Objeto {
	
	private Efectividad efectividad;
	
	public Amuleto(Efectividad efectividad) {
		this.efectividad = efectividad;
	}

    @Override
    public void activar() {
        switch (efectividad) {
            case BAJA:
                System.out.println("El amuleto da un PEQUEÑO aumento de daño.");
                break;
            case MEDIA:
                System.out.println("El amuleto da un MEDIANO aumento de daño.");
                break;
            case ALTA:
                System.out.println("El amuleto da un GRAN aumento de daño.");
                break;
        }
    }

}
