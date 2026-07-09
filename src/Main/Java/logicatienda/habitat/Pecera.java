package logicatienda.habitat;

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
     * Limpia la pecera.
     * Restaura el nivel de limpieza al 100%.
     */
    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha cambiado el agua de la pecera.");
    }
}