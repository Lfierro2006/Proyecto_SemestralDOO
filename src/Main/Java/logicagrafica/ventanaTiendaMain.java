package logicagrafica;

import javax.swing.*;
import java.awt.*;


public class ventanaTiendaMain extends JFrame {
    public final int width = 1280;
    public final int height = 800;//PROPORCION 8:5
    public ventanaTiendaMain(){
        this.setTitle("Tienda Principal");
        this.setSize(width, height); // En caso de querer cambiar la ventana por favor mantener la proporcion de 8:5
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public static void main() {
        ventanaTiendaMain v = new ventanaTiendaMain();
        v.setVisible(true);
    }
}
