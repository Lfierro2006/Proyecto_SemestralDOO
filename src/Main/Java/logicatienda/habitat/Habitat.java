package logicatienda.habitat;

import logicatienda.animales.Animal;

/**
 * Clase abstracta que representa un hábitat para alojar animales en la tienda.
 * Cada hábitat tiene un nivel de limpieza y puede albergar un único animal.
 * Los hábitats concretos deben implementar cómo se limpian.
 */
public abstract class Habitat {

    protected int nivelLimpieza;
    protected Animal residente;

    /**
     * Constructor de un hábitat.
     * Inicializa el nivel de limpieza al 100% y no tiene residente.
     */
    public Habitat() {
        this.nivelLimpieza = 100;
        this.residente = null;
    }

    /**
     * Obtiene el nivel de limpieza del hábitat.
     *
     * @return Nivel de limpieza (0-100)
     */
    public int getNivelLimpieza() {
        return nivelLimpieza;
    }

    /**
     * Obtiene el animal residente del hábitat.
     *
     * @return El animal residente, o null si está vacío
     */
    public Animal getResidente() {
        return this.residente;
    }

    /**
     * Verifica si el hábitat está vacío.
     *
     * @return true si no tiene animal residente, false en caso contrario
     */
    public boolean estaVacio() {
        return this.residente == null;
    }

    /**
     * Aloja un animal en el hábitat.
     * Solo se permite si el hábitat está vacío.
     *
     * @param nuevaMascota El animal a alojar
     * @return true si se alojó correctamente, false si ya estaba ocupado
     */
    public boolean alojarAnimal(Animal nuevaMascota) {
        if (this.estaVacio()) {
            this.residente = nuevaMascota;
            return true;
        }
        return false;
    }

    /**
     * Libera el hábitat, eliminando al animal residente.
     * El hábitat queda vacío.
     */
    public void liberarHabitat() {
        this.residente = null;
    }

    /**
     * Disminuye el nivel de limpieza del hábitat.
     * El nivel no puede bajar de 0.
     *
     * @param cantidad Cantidad a ensuciar
     */
    public void ensuciar(int cantidad) {
        this.nivelLimpieza -= cantidad;
        if (this.nivelLimpieza < 0) {
            this.nivelLimpieza = 0;
        }
    }

    /**
     * Verifica si el hábitat necesita limpieza.
     *
     * @return true si el nivel de limpieza es menor a 55, false en caso contrario
     */
    public boolean necesitaLimpieza() {
        return this.nivelLimpieza < 55;
    }

    /**
     * Limpia el hábitat.
     * Cada tipo de hábitat implementa su propia forma de limpieza.
     */
    public abstract void limpiarHabitat();
}