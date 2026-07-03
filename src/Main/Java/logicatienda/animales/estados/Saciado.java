package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public class Saciado implements EstadoAnimal {
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