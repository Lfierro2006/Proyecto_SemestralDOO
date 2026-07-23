package logicatienda.animales;

/**
 * Clase que representa un perro en la tienda.
 * Implementa la interfaz Terrestre para indicar que es del tipo terrestre.
 */

public class Perro extends Animal implements Terrestre{

    /**
     * Constructor de un perro.
     * @param nombre El nombre del perro
     */
    public Perro(String nombre) {
        super(nombre);
    }
}