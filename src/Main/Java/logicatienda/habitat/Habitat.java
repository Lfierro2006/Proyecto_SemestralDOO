package logicatienda.habitat;

import logicatienda.animales.Animal;

public abstract class Habitat {

    protected int nivelLimpieza;
    protected Animal residente;

    public Habitat() {
        this.nivelLimpieza = 100;
        this.residente = null;
    }

    public int getNivelLimpieza() {
        return nivelLimpieza;
    }

    public Animal getResidente() {
        return this.residente;
    }

    public boolean estaVacio() {
        return this.residente == null;
    }

    public boolean alojarAnimal(Animal nuevaMascota) {
        if (this.estaVacio()) {
            this.residente = nuevaMascota;
            return true;
        }
        return false;
    }

    public void liberarHabitat() {
        this.residente = null;
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