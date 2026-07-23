package logicatienda.habitat;

import logicatienda.animales.Animal;
import logicatienda.animales.Terrestre;

/**
 * Hábitat tipo cama para animales aéreos (aves).
 * Representa un lugar cómodo donde las aves pueden descansar.
 */
public class Cama extends Habitat {

    /**
     * Constructor de una cama.
     * Inicializa el hábitat con limpieza al 100% y sin residente.
     */
    public Cama() { super(); }

    /**
     * Verifica si el animal es compatible con la cama.
     * Solo acepta animales que implementen la interfaz Terrestre.
     * @param animal El animal a verificar
     * @return true si el animal es terrestre, false en caso contrario
     */
    @Override
    public boolean esCompatible(Animal animal) {
        return animal instanceof Terrestre;
    }
}