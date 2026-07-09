package logicatienda.habitat;

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
     * Limpia la jaula.
     * Restaura el nivel de limpieza al 100%.
     */
    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha limpiado la jaula.");
    }
}