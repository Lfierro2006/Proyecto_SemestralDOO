package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public class Sucio implements EstadoAnimal {
    @Override
    public void ejecutar(Animal animal) {
        animal.disminuirNivel(Animal.Estadistica.HIGIENE, 3);
    }

    @Override
    public EstadoAnimal.Tipo getTipo() {
        return EstadoAnimal.Tipo.SUCIO;
    }

    @Override
    public boolean puedeJugar() { return true; }

    @Override
    public boolean puedeCurar() { return true; }

    @Override
    public boolean puedeLimpiar() { return true; }
}