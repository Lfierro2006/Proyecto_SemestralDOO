package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public class Enfermo implements EstadoAnimal {
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.SALUD, 5);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.ENFERMO;
    }

    @Override
    public boolean puedeJugar() { return false; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}