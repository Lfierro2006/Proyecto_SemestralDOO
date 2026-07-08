package logicatienda.usuario;

import logicatienda.inventario.Inventario;

public class Usuario {
    private int dinero;
    private Inventario inventario;

    public Usuario(int dineroInicial) {
        this.dinero = dineroInicial;
        this.inventario = new Inventario(6);
    }

    public int getDinero() {
        return dinero;
    }

    public void sumarItem(int indice){
        this.inventario.sumarUno(indice);
    }

    public void restarItem(int indice){
        this.inventario.quitarUno(indice);
    }

    public void getCantItem(int indice){
        this.inventario.getCantidad(indice);
    }
}