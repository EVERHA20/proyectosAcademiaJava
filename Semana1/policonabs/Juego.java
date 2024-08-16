
public class Juego {

	public static void main(String[] args) {
        // Se crean los Personajes
        Personaje[] personajes = new Personaje[4];
        Personaje jugador = new Jugador("Barbaro");
        personajes[0] = jugador;
        personajes[1] = new Goblin();
        personajes[2] = new Orco();
        personajes[3] = new Basilisco();


        // Cada uno de los enemigos ataca y el jugador responde a cada uno
        for (int i = 1; i < personajes.length; i++) {
            Personaje enemigo = personajes[i];
            enemigo.accion();
            enemigo.atacar();
            jugador.accion();
            jugador.atacar();
        }
	}

}
