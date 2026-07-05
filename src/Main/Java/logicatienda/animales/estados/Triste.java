package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal triste.
 * La felicidad disminuye rápidamente con el tiempo.
 * Permite jugar, curar y limpiar.
 */

public class Triste implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado triste.
     * Disminuye la felicidad en 4 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.FELICIDAD, 4);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.TRISTE;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}