
public class Juego {

	public static void main(String[] args) {
        // Se equipa uno de los objetos y se obtiene una efectividad random
        Jugador jugador = Equipar.equiparObjeto("Arquero", "pocion");
        jugador.UsarObjeto();

        Jugador jugador2 = Equipar.equiparObjeto("Caballero", "anillo");
        jugador2.UsarObjeto();
        
        Jugador jugador3 = Equipar.equiparObjeto("Asesino", "amuleto");
        jugador3.UsarObjeto();
	}

}
