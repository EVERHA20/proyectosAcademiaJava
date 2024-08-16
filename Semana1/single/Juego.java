
public class Juego {

	public static void main(String[] args) {
		
		Configuracion config1 = Configuracion.getInstance();
        System.out.println("Configuración 1 inicial:");
        System.out.println("Resolución: " + config1.getResolucion());
        System.out.println("Sonido Activado: " + config1.isSonidoActivado());
        System.out.println("Contador de Instancias: " + Configuracion.getContador());
        System.out.println();

        MainMenu menu = new MainMenu();
        menu.mostrarMenu();
    }
}

