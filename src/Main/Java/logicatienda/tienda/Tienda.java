package logicatienda.tienda;

import java.util.ArrayList;
import java.util.List;
import logicatienda.animales.*;
import logicatienda.habitat.*;
import logicatienda.comprador.Comprador;

public class Tienda {

    private int presupuesto;
    private List<Habitat> espaciosActivos;
    public static final int CAPACIDAD_MAXIMA = 12;

    public Tienda(int presupuestoInicial) {
        this.presupuesto = presupuestoInicial;
        this.espaciosActivos = new ArrayList<>();
    }

    public int getPresupuesto() {
        return this.presupuesto;
    }

    public List<Habitat> getEspaciosActivos() {
        return this.espaciosActivos;
    }

    public boolean comprarHabitat(Habitat nuevoHabitat, int costo) {

        if (this.espaciosActivos.size() >= CAPACIDAD_MAXIMA) {
            System.out.println("No hay más espacios libres en la tienda.");
            return false;
        }

        if (this.presupuesto < costo) {
            System.out.println("No se cuenta con suficiente dinero.");
            return false;
        }

        this.presupuesto -= costo;
        this.espaciosActivos.add(nuevoHabitat);

        System.out.println("Nuevo recinto comprado.");
        return true;
    }
    public void reembolso(int costo){
        this.presupuesto+= costo;
    }
    public boolean comprarAnimal(Animal nuevaMascota, Habitat destino, int costo) {

        if (!this.espaciosActivos.contains(destino)) {
            System.out.println("Error: Ese recinto no pertenece a tu tienda.");
            return false;
        }

        if (!destino.estaVacio()) {
            System.out.println("Operación fallida: El recinto seleccionado ya está ocupado.");
            return false;
        }

        if (this.presupuesto < costo) {
            System.out.println("Operación fallida: Presupuesto insuficiente para la mascota.");
            return false;
        }
        this.presupuesto -= costo;
        destino.alojarAnimal(nuevaMascota);

        System.out.println("El animal ha sido ccomprado corretamente.");
        return true;
    }

    public boolean venderMascotaACliente(Habitat habitatOcupado, Comprador cliente) {

        if (habitatOcupado.estaVacio()) {
            System.out.println("Error: No hay ningún animal en este recinto para vender.");
            return false;
        }

        Animal mascotaAVender = habitatOcupado.getResidente();

        int precioFinalAcordado = cliente.calcularPrecioFinal(mascotaAVender);

        if (precioFinalAcordado > 0){
            this.presupuesto += precioFinalAcordado;
            habitatOcupado.liberarHabitat();
            System.out.println("Venta exitosa");

                return true;
            }

            System.out.println("Venta cancelada");
            return false;

        }
}