package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public class Sano implements EstadoAnimal {
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