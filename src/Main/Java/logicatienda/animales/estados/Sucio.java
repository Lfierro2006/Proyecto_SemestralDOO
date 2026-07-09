package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;

/**
 * Estado que representa a un animal sucio.
 * La higiene disminuye rapidamente con el tiempo.
 * Permite jugar, curar y limpiar.
 */

public class Sucio implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado sucio.
     * Disminuye la higiene en 3 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Estadistica.HIGIENE, 3);
    }

    @Override
    public Tipo getTipo() {
        return Tipo.SUCIO;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}