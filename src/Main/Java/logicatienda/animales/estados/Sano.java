package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal sano.
 * La salud disminuye lentamente con el tiempo.
 * Permite jugar y limpiar, pero no curar (no está enfermo).
 */

public class Sano implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado sano.
     * Disminuye la salud en 3 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.SALUD, 3);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.SANO;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return false; }

    @Override
    public boolean puedeLimpiar() { return true; }
}