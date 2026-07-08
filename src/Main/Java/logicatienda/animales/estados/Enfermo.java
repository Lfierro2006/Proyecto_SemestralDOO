package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;

/**
 * Estado que representa a un animal enfermo.
 * La salud disminuye con el tiempo (-5 por ciclo).
 * No permite jugar, pero sí curar y limpiar.
 */

public class Enfermo implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado enfermo.
     * Disminuye la salud en 5 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Estadistica.SALUD, 5);
    }

    @Override
    public Tipo getTipo() {
        return Tipo.ENFERMO;
    }

    @Override
    public boolean puedeJugar() { return false; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}