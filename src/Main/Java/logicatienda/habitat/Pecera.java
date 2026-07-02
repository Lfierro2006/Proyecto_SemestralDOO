package logicatienda.habitat;

public class Pecera extends Habitat {

    public Pecera() {
        super();
    }

    @Override
    public void limpiarHabitat() {
        this.nivelLimpieza = 100;
        System.out.println("Se ha limpiado la pecera.");
    }
}