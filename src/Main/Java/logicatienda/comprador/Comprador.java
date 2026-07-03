package logicatienda.comprador;

import logicatienda.animales.Animal;
import logicatienda.animales.estados.EstadoAnimal;

public class Comprador {

    private final String tipoMascotaDeseada;
    private final double presupuestoMaximo;

    public Comprador(String tipoMascotaDeseada, double presupuestoMaximo) {
        this.tipoMascotaDeseada = tipoMascotaDeseada;
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public boolean quiereComprar(Animal mascota, double precioVenta) {
        if (this.presupuestoMaximo < precioVenta) {
            System.out.println("El cliente no tiene suficiente dinero.");
            return false;
        }
        String especieMascota = mascota.getClass().getSimpleName();
        if (!especieMascota.equalsIgnoreCase(this.tipoMascotaDeseada)) {
            System.out.println("El cliente busca otro tipo de animal.");
            return false;
        }

        if (mascota.tieneEstado(EstadoAnimal.Tipo.ENFERMO)) {
            System.out.println("El animal está enfermo y no fue comprado.");
            return false;
        }
        return true;
    }
}
//por ahora la venta no se completa si el animal esta en estado ENFERMO
//pero se puede cambiar en caso de que la logica del juego lo necesite
//como reducir el precio en caso de haber 1,2,3 o 4 estados adversos