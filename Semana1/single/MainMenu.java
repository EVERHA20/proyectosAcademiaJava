
import java.util.Scanner;

public class MainMenu {
	
    private Scanner scanner;
    private Configuracion config;

    public MainMenu() {
        scanner = new Scanner(System.in);
        config = Configuracion.getInstance();
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("Menú de Configuración:");
            System.out.println("1. Cambiar Resolución");
            System.out.println("2. Activar/Desactivar Sonido");
            System.out.println("3. Mostrar Configuración Actual");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    cambiarResolucion();
                    break;
                case 2:
                    cambiarSonido();
                    break;
                case 3:
                    mostrarConfiguracion();
                    break;
                case 4:
                    System.out.println("Saliendo del menú.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void cambiarResolucion() {
        System.out.print("Ingrese la nueva resolución (por ejemplo, 1280x720): ");
        String nuevaResolucion = scanner.nextLine();
        config.setResolucion(nuevaResolucion);
        System.out.println("Resolución cambiada a " + nuevaResolucion);
    }

    private void cambiarSonido() {
        System.out.print("¿Activar sonido? (true/false): ");
        boolean nuevoEstadoSonido = scanner.nextBoolean();
        scanner.nextLine();
        config.setSonidoActivado(nuevoEstadoSonido);
        System.out.println("Sonido " + (nuevoEstadoSonido ? "activado" : "desactivado"));
    }

    private void mostrarConfiguracion() {
        System.out.println("Configuración Actual:");
        System.out.println("Resolución: " + config.getResolucion());
        System.out.println("Sonido Activado: " + config.isSonidoActivado());
        System.out.println("Contador de Instancias: " + Configuracion.getContador());
    }
}
