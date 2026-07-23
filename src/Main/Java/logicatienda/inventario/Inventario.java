package logicatienda.inventario;

import java.util.ArrayList;

/**
 * Representa el inventario del jugador.
 * Almacena el número de items, donde cada índice es un item distinto.
 */
public class Inventario {

    private ArrayList<Integer> cantidades;

    /**
     * Constructor del inventario.
     * Inicializa todas las cantidades a 0.
     *
     * @param cantidadDeTiposDiferentes Número de tipos de ítems que tendrá el inventario
     */
    public Inventario(int cantidadDeTiposDiferentes) {
        this.cantidades = new ArrayList<>();

        for (int i = 0; i < cantidadDeTiposDiferentes; i++) {
            this.cantidades.add(0);
        }
    }

    /**
     * Aumenta en 1 la cantidad del ítem en el índice especificado.
     *
     * @param indice Índice del ítem a incrementar
     */
    public void sumarUno(int indice) {
        if (indice >= 0 && indice < this.cantidades.size()) {
            int valorActual = this.cantidades.get(indice);
            this.cantidades.set(indice, valorActual + 1);
        } else {
            System.out.println("Índice de inventario no válido.");
        }
    }

    /**
     * Disminuye en 1 la cantidad del ítem en el índice especificado.
     * Solo permite quitar si la cantidad es mayor a 0.
     *
     * @param indice Índice del ítem a reducir
     */
    public void quitarUno(int indice) {
        if (indice < 0 || indice >= this.cantidades.size()) {
            System.out.println("Índice de inventario no válido.");
            return;
        }
        if (this.cantidades.get(indice) > 0) {
            this.cantidades.set(indice, this.cantidades.get(indice) - 1);
        }
    }

    /**
     * Obtiene la cantidad actual de un ítem en el índice especificado.
     *
     * @param indice Índice del ítem a consultar
     * @return Cantidad del ítem, o 0 si el índice no es válido
     */
    public int getCantidad(int indice) {
        if (indice >= 0 && indice < this.cantidades.size()) {
            return this.cantidades.get(indice);
        }
        return 0;
    }
}