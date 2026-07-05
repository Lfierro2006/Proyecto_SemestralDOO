package logicatienda.animales;

/**
 * Clase que representa un pez en la tienda.
 * Implementa la interfaz Acuatico para indicar que vive en agua.
 */

public class Pez extends Animal implements Acuatico {

    /**
     * Constructor de un pez.
     * @param nombre El nombre del pez
     */
    public Pez(String nombre){
        super(nombre);
    }
}
