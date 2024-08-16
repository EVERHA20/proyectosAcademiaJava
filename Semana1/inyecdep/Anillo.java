
public class Anillo implements Objeto {
	
	private Efectividad efectividad;
	
	public Anillo(Efectividad efectividad) {
		this.efectividad = efectividad;
	}

    @Override
    public void activar() {
        switch (efectividad) {
            case BAJA:
                System.out.println("El anillo otorga un PEQUEÑO escudo magico.");
                break;
            case MEDIA:
                System.out.println("El anillo otorga un escudo magico MODERADO.");
                break;
            case ALTA:
                System.out.println("El anillo otorga un GRAN escudo magico.");
                break;
        }
    }

}
