package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal hambriento.
 * La saciedad disminuye rápidamente con el tiempo.
 * No permite jugar, pero sí curar y limpiar.
 */

public class Hambriento implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado hambriento.
     * Disminuye la saciedad en 5 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.SACIEDAD, 5);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.HAMBRIENTO;
    }

    @Override
    public boolean puedeJugar() { return false; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}