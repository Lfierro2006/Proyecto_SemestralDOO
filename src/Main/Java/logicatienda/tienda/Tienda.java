package logicatienda.tienda;

import java.util.ArrayList;
import java.util.List;
import logicatienda.animales.*;
import logicatienda.habitat.*;
import logicatienda.comprador.Comprador;
import logicatienda.usuario.*;

/**
 * Representa la tienda de mascotas.
 * Gestiona los hábitats disponibles, las compras de hábitats y animales,
 * y las ventas a clientes. Delega la gestión del dinero e inventario a usuario.
 */
public class Tienda {

    private List<Habitat> espaciosActivos;
    public static final int CAPACIDAD_MAXIMA = 22;
    private Usuario usuario;

    /**
     * Constructor de la tienda.
     * Inicializa la lista de espacios activos y crea un usuario con el dinero inicial.
     * @param DineroInicial Cantidad de dinero con la que empieza el jugador
     */
    public Tienda(int DineroInicial) {
        this.espaciosActivos = new ArrayList<>();
        this.usuario=new Usuario(DineroInicial);
    }

    /**
     * Obtiene el presupuesto actual del usuario (dinero disponible).
     * @return Dinero disponible del usuario
     */
    public int getPresupuesto() {
        return this.usuario.getDinero();
    }

    /**
     * Obtiene la lista de hábitats activos en la tienda.
     * @return Lista de hábitats
     */
    public List<Habitat> getEspaciosActivos() {
        return this.espaciosActivos;
    }

    /**
     * Compra un hábitat y lo añade a los espacios activos de la tienda.
     * Verifica que no se supere la capacidad máxima y que el usuario tenga suficiente dinero.
     * @param nuevoHabitat El hábitat a comprar
     * @param costo Costo del hábitat
     * @return true si la compra fue exitosa, false en caso contrario
     */
    public boolean comprarHabitat(Habitat nuevoHabitat, int costo) {

        if (this.espaciosActivos.size() >= CAPACIDAD_MAXIMA) {
            System.out.println("No hay más espacios libres en la tienda.");
            return false;
        }

        if (usuario.getDinero() < costo) {
            System.out.println("No se cuenta con suficiente dinero.");
            return false;
        }

        this.usuario.quitarDinero(costo);
        this.espaciosActivos.add(nuevoHabitat);

        System.out.println("Nuevo recinto comprado.");
        return true;
    }

    /**
     * Compra un animal y lo aloja en un hábitat específico.
     * Verifica que el hábitat pertenezca a la tienda, que esté vacío y que haya dinero suficiente.
     * @param nuevaMascota El animal a comprar
     * @param destino El hábitat donde se alojará el animal
     * @param costo Costo del animal
     * @return true si la compra fue exitosa, false en caso contrario
     */
    public boolean comprarAnimal(Animal nuevaMascota, Habitat destino, int costo) {

        if (!this.espaciosActivos.contains(destino)) {
            System.out.println("Error: Ese recinto no pertenece a tu tienda.");
            return false;
        }

        if (!destino.estaVacio()) {
            System.out.println("Operación fallida: El recinto seleccionado ya está ocupado.");
            return false;
        }

        if (this.usuario.getDinero() < costo) {
            System.out.println("Operación fallida: Presupuesto insuficiente para la mascota.");
            return false;
        }
        this.usuario.quitarDinero(costo);
        destino.alojarAnimal(nuevaMascota);
        System.out.println("El animal ha sido ccomprado corretamente.");
        return true;
    }

    /**
     * Compra una medicina.
     * Verifica que el usuario tenga suficiente dinero y añade una medicina al inventario.
     * @return true si la compra fue exitosa, false en caso contrario
     */
    public boolean comprarMedicina(){
        if(this.usuario.getDinero()<150){
            return false;
        }
        this.usuario.sumarItem(Item.MEDICINA.getIndex());
        this.usuario.quitarDinero(150);
        return true;
    }

    /**
     * Compra comida.
     * Verifica que el usuario tenga suficiente dinero y añade comida al inventario.
     * @return true si la compra fue exitosa, false en caso contrario
     */
    public boolean comprarComida(){
        if(this.usuario.getDinero()<200){
            return false;
        }
        this.usuario.quitarDinero(200);
        this.usuario.sumarItem(Item.COMIDA.getIndex());
        return true;
    }

    /**
     * Realiza un reembolso al usuario al desmantelar un hábitat.
     * @param costo Cantidad a reembolsar
     */
    public void reembolso(int costo){
        this.usuario.darDinero(costo);
    }

    /**
     * Vende una mascota a un cliente.
     * Verifica que el hábitat tenga un animal y calcula el precio final con el comprador.
     * @param habitatOcupado El hábitat que contiene el animal a vender
     * @param cliente El comprador interesado
     * @return true si la venta fue exitosa, false en caso contrario
     */
    public boolean venderMascotaACliente(Habitat habitatOcupado, Comprador cliente) {

        if (habitatOcupado.estaVacio()) {
            System.out.println("Error: No hay ningún animal en este recinto para vender.");
            return false;
        }

        Animal mascotaAVender = habitatOcupado.getResidente();

        int precioFinalAcordado = cliente.calcularPrecioFinal(mascotaAVender);

        if (precioFinalAcordado > 0){
            this.usuario.darDinero(precioFinalAcordado);
            habitatOcupado.liberarHabitat();
            System.out.println("Venta exitosa");
                return true;
        }

        System.out.println("Venta cancelada");
        return false;

    }
}