package logicatienda.animales;

/**
 * Clase que representa un gato en la tienda.
 * Implementa la interfaz Terrestre para indicar que es del tipo terrestre.
 */

public class Gato extends Animal implements Terrestre {

    /**
     * Constructor de un gato.
     * @param nombre El nombre del gato
     */
    public Gato(String nombre){
        super(nombre);
    }
}