package logicatienda.habitat;

import logicatienda.animales.Acuatico;
import logicatienda.animales.Animal;

/**
 * Hábitat tipo pecera para animales acuáticos (peces).
 * Contiene agua y proporciona un entorno adecuado para peces.
 */
public class Pecera extends Habitat {

    /**
     * Constructor de una pecera.
     * Inicializa el hábitat con limpieza al 100% y sin residente.
     */
    public Pecera() { super(); }

    /**
     * Verifica si el animal es compatible con la pecera.
     * Solo acepta animales que implementen la interfaz Acuatico.
     *
     * @param animal El animal a verificar
     * @return true si el animal es acuático, false en caso contrario
     */
    @Override
    public boolean esCompatible(Animal animal) {
        return animal instanceof Acuatico;
    }
}