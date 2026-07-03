package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public class Hambriento implements EstadoAnimal {
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