package logicatienda.animales.estados;

import logicatienda.animales.Animal;

/**
 * Estado que representa a un animal saciado.
 * La saciedad disminuye moderadamente con el tiempo.
 * Permite jugar, curar y limpiar.
 */

public class Saciado implements EstadoAnimal {

    /**
     * Ejecuta el comportamiento del estado saciado.
     * Disminuye la saciedad en 2 puntos por ciclo.
     * @param animal El animal al que se aplica el estado
     */
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.SACIEDAD, 2);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.SACIADO;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}