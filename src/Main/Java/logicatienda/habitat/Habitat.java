package logicatienda.habitat;

public abstract class Habitat {

    protected int nivelLimpieza;

    public Habitat() {
        this.nivelLimpieza = 100;
    }

    public int getNivelLimpieza() {
        return nivelLimpieza;
    }

    public void ensuciar(int cantidad) {
        this.nivelLimpieza -= cantidad;
        if (this.nivelLimpieza < 0) {
            this.nivelLimpieza = 0;
        }
    }

    public boolean necesitaLimpieza() {
        return this.nivelLimpieza < 55;
    }

    public abstract void limpiarHabitat();
}