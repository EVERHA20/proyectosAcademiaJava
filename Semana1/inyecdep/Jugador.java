
public class Jugador {
	
	private String nombre;
	
	private Objeto objeto;
	
	public Jugador(String nombre,Objeto objeto) {
		this.nombre = nombre;
		this.objeto = objeto;
	}
	
	void UsarObjeto( ) {
		System.out.println(nombre + " usa su objeto");
		objeto.activar();
	}
	
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

}
