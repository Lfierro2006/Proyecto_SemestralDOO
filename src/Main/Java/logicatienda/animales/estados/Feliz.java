package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal feliz.
 * La felicidad disminuye lentamente con el tiempo.
 * Permite jugar, curar y limpiar.
 */

public class Feliz implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado feliz.
     * Disminuye la felicidad en 2 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.FELICIDAD, 2);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.FELIZ;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}