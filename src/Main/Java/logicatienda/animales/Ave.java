package logicatienda.animales;

/**
 * Clase que representa un ave en la tienda.
 * Implementa la interfaz Aereo para indicar que es un animal del tipoa ereo.
 */

public class Ave extends Animal implements Aereo{

    /**
     * Constructor de ave.
     * @param nombre El nombre del ave
     */
    public Ave(String nombre){
        super(nombre);
    }
}