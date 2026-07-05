package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Interfaz que define los estados de un animal.
 * Implementa el patrón State para manejar comportamientos según el estado actual.
 */

public interface EstadoAnimal {
    /**
     * Enum que define los tipos de estado posibles de un animal.
     */
    enum Tipo {
        FELIZ,
        TRISTE,
        SACIADO,
        HAMBRIENTO,
        SANO,
        ENFERMO,
        LIMPIO,
        SUCIO
    }
    /**
     * Ejecuta el comportamiento asociado al estado.
     * Se llama periódicamente para aplicar efectos continuos.
     * @param animal El animal al que se aplica el estado
     */
    public void ejecutar(Animal animal);

    /**
     * Obtiene el tipo de estado.
     * @return El tipo de estado (FELIZ, TRISTE, etc.)
     */
    Tipo getTipo();

    /**
     * Indica si el animal puede jugar en este estado.
     * @return true si puede jugar, false en caso contrario
     */
    boolean puedeJugar();

    /**
     * Indica si el animal puede ser curado en este estado.
     * @return true si puede ser curado, false en caso contrario
     */
    boolean puedeCurar();

    /**
     * Indica si el animal puede ser limpiado en este estado.
     * @return true si puede ser limpiado, false en caso contrario
     */
    boolean puedeLimpiar();
}
