package logicatienda.habitat;

import logicatienda.animales.Aereo;
import logicatienda.animales.Animal;

/**
 * Hábitat tipo jaula para animales terrestres (perros y gatos).
 * Proporciona un espacio cerrado y seguro para mascotas terrestres.
 */
public class Jaula extends Habitat {
    /**
     * Constructor de una jaula.
     * Inicializa el hábitat con limpieza al 100% y sin residente.
     */
    public Jaula() { super(); }

    /**
     * Verifica si el animal es compatible con la jaula.
     * Solo acepta animales que implementen la interfaz Aereo.
     * @param animal El animal a verificar
     * @return true si el animal es aéreo, false en caso contrario
     */
    @Override
    public boolean esCompatible(Animal animal) {
        return animal instanceof Aereo;
    }
}