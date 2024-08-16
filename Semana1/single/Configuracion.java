
public class Configuracion {
	//la idea es que todas las funciones del juego tengan la misma instancia de configuracion.
    private static Configuracion instancia;
    
    private String resolucion;
    private boolean sonidoActivado;
    private static int contador = 0;

    private Configuracion() {
    	this.resolucion = "1920x1080";
        this.sonidoActivado = true;
        
        contador++;
    }

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }
    
    public static int getContador() {
        return contador;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public boolean isSonidoActivado() {
        return sonidoActivado;
    }

    public void setSonidoActivado(boolean sonidoActivado) {
        this.sonidoActivado = sonidoActivado;
    }
}
