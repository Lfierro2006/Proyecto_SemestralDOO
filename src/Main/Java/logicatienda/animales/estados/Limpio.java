package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal limpio.
 * La higiene disminuye lentamente con el tiempo.
 * Permite jugar y curar, pero no limpiar (ya está limpio).
 */

public class Limpio implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado limpio.
     * Disminuye la higiene en 2 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.HIGIENE, 2);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.LIMPIO;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return false; }
}