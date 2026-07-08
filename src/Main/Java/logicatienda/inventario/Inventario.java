package logicatienda.inventario;

import java.util.ArrayList;
import java.util.List;

public class Inventario<T> {

    private List<T> elementos;

    public Inventario() {
        this.elementos = new ArrayList<>();
    }

    public void agregar(T item) {
        this.elementos.add(item);
        System.out.println("Se guardó en el inventario de espera.");
    }

    public boolean quitar(T item) {
        return this.elementos.remove(item);
    }

    public List<T> getElementos() {
        return this.elementos;
    }

    public boolean estaVacio() {
        return this.elementos.isEmpty();
    }
}