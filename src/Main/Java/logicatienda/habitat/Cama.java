package logicatienda.habitat;

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
     * Limpia la cama.
     * Restaura el nivel de limpieza al 100%.
     */
    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha limpiado la cama.");
    }
}