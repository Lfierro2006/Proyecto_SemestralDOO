package logicagrafica;

import logicatienda.animales.*;
import logicatienda.tienda.Tienda;
import logicatienda.usuario.*;

import javax.swing.*;
import java.awt.*;
/**
 * Representa un cuadro de diálogo modal (JDialog) para confirmar la compra de un ítem en la tienda.
 * Esta clase se encarga de mostrar la interfaz de confirmación, validar si el usuario
 * tiene el dinero suficiente y ejecutar la transacción actualizando el inventario y presupuesto.
 */

public class TiendaItem extends JDialog {
    /**
     * Construye y muestra la ventana de confirmación de compra.
     * * @param ventanaPadre El JFrame principal del juego sobre el cual aparecerá este diálogo centrado.
     * @param nombreItem   El nombre del artículo que se va a comprar (ej. "Comida", "Medicina") para mostrar en el texto.
     * @param costo        El valor monetario (precio) del ítem.
     * @param Itemcompra   El objeto o enumerador que representa el ítem que se va a añadir al inventario.
     * @param tienda       La instancia principal de la lógica del juego (Tienda) que gestiona al usuario y su economía.
     * @param actualizarP  Un bloque de código (Runnable) que se ejecutará si la compra es exitosa, 
     * utilizado para actualizar los paneles visuales (como el contador de dinero).
     */
    public TiendaItem (JFrame ventanaPadre, String nombreItem, int costo, Item Itemcompra, Tienda tienda, Runnable actualizarP){
        super(ventanaPadre, "Comprar "+ nombreItem, true);

        this.setSize(240, 150);
        this.setLocationRelativeTo(ventanaPadre);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        JLabel lblInfo = new JLabel("¿Comprar " + nombreItem + " por $" + costo + "?");
        JButton btnComprar = new JButton("Comprar");
        JButton btnCancelar = new JButton("Cancelar");

        btnComprar.addActionListener(e -> {
            if (tienda.getUsuario().getDinero() >= costo){
                tienda.getUsuario().quitarDinero(costo);
                tienda.getUsuario().sumarItem(Itemcompra.getIndex());
                System.out.println("Compraste 1 " + nombreItem );
                if (actualizarP != null) actualizarP.run();
                this.dispose();}
            else {
                System.out.println("No tienes suficiente dinero, lo minimo es "+ costo);
            }
                }
                );
        btnCancelar.addActionListener(e -> this.dispose());
        this.add(lblInfo);
        this.add(btnComprar);
        this.add(btnCancelar);
    }
}
