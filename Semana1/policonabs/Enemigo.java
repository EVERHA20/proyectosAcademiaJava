
abstract class Enemigo implements Personaje{
    // Todos los enemigos son personajes y tienen la misma accion de spawnear.
	@Override
	public void accion() {
		System.out.println("Un enemigo aparece de la nada");
	}
}
