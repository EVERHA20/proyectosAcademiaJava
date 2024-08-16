
public class Jugador implements Personaje{
    private String clase;

    // Constructor
    public Jugador(String clase) {
        this.clase = clase;
    }

    public void atacar() {
        System.out.println(clase + " ataca con su espada.");
    }

    public void accion() {
        System.out.println(clase + " esquiva el ataque.");
    }
}
