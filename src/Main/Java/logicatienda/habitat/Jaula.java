package logicatienda.habitat;

public class Jaula extends Habitat {

    public Jaula() {
        super();
    }

    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha limpiado la jaula.");
    }
}