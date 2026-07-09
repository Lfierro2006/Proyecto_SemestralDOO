package logicagrafica;

import logicatienda.animales.*;
import logicatienda.tienda.Tienda;
import logicatienda.usuario.*;

import javax.swing.*;
import java.awt.*;

public class TiendaItem extends JDialog {
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
