
import java.util.Random;

public class Equipar {
	
    private static final Random RANDOM = new Random();

    private static Efectividad getEfectividadAleatoria() {
        Efectividad[] efectividades = Efectividad.values();
        return efectividades[RANDOM.nextInt(efectividades.length)];
    }
    // al momento de equipar el objeto se le da una efectividad aleatoria
    public static Jugador equiparObjeto(String nombre, String tipoObjeto) {
        Objeto objeto;
        switch (tipoObjeto.toUpperCase()) {
            case "ANILLO":
                objeto = new Anillo(getEfectividadAleatoria());
                break;
            case "POCION":
                objeto = new Pocion(getEfectividadAleatoria());
                break;
            case "AMULETO":
                objeto = new Amuleto(getEfectividadAleatoria());
                break;
            default:
                throw new UnsupportedOperationException("Tipo objeto no existe");
        }
        return new Jugador(nombre, objeto);
    }
}
