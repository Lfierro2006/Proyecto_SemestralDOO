package logicatienda.habitat;

public class Cama extends Habitat {

    public Cama() { super(); }

    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha limpiado la cama.");
    }
}