package logicatienda.inventario;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Integer> cantidades;

    public Inventario(int cantidadDeTiposDiferentes) {
        this.cantidades = new ArrayList<>();

        for (int i = 0; i < cantidadDeTiposDiferentes; i++) {
            this.cantidades.add(0);
        }
    }

    public void sumarUno(int indice) {
        if (indice >= 0 && indice < this.cantidades.size()) {
            int valorActual = this.cantidades.get(indice);
            this.cantidades.set(indice, valorActual + 1);
        } else {
            System.out.println("Índice de inventario no válido.");
        }
    }


    public boolean quitarUno(int indice) {
        if (indice >= 0 && indice < this.cantidades.size()) {
            int valorActual = this.cantidades.get(indice);

            if (valorActual > 0) {
                this.cantidades.set(indice, valorActual - 1);
                return true;
            }
        }
        return false;
    }

    public int getCantidad(int indice) {
        if (indice >= 0 && indice < this.cantidades.size()) {
            return this.cantidades.get(indice);
        }
        return 0;
    }
}