package logicatienda.animales.estados;

import logicatienda.animales.Animal;

public interface EstadoAnimal {
    enum Tipo {
        FELIZ,
        TRISTE,
        SACIADO,
        HAMBRIENTO,
        SANO,
        ENFERMO,
        LIMPIO,
        SUCIO
    }
    public void ejecutar(Animal animal);
    Tipo getTipo();
    boolean puedeJugar();
    boolean puedeCurar();
    boolean puedeLimpiar();
}
