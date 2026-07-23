package logicatienda.usuario;

import logicatienda.inventario.Inventario;

/**
 * Representa al jugador de la tienda de mascotas.
 * Gestiona el dinero disponible y el inventario de ítems del jugador.
 */
public class Usuario {
    private int dinero;
    private Inventario inventario;

    /**
     * Constructor del usuario.
     * Inicializa el dinero y crea un inventario con 6 tipos de ítems.
     * @param dineroInicial Cantidad de dinero inicial del jugador
     */
    public Usuario(int dineroInicial) {
        this.dinero = dineroInicial;
        this.inventario = new Inventario(6);
    }

    /**
     * Obtiene el dinero disponible del usuario.
     * @return Dinero actual
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * Añade un ítem al inventario del usuario.
     * @param indice Índice del ítem a añadir
     */
    public void sumarItem(int indice){
        this.inventario.sumarUno(indice);
    }

    /**
     * Elimina un ítem del inventario del usuario.
     *
     * @param indice Índice del ítem a eliminar
     */
    public void restarItem(int indice){
        this.inventario.quitarUno(indice);
    }

    /**
     * Obtiene la cantidad de un ítem específico en el inventario.
     *
     * @param indice Índice del ítem a consultar
     * @return Cantidad del ítem
     */
    public int getCantItem(int indice){
        return this.inventario.getCantidad(indice);
    }

    /**
     * Añade dinero al usuario.
     * @param cantidad Cantidad de dinero a añadir
     */
    public void darDinero(int cantidad){
        this.dinero+=cantidad;
    }

    /**
     * Quita dinero del usuario.
     * @param cantidad Cantidad de dinero a quitar
     */
    public void quitarDinero(int cantidad){
        this.dinero-=cantidad;
    }
}