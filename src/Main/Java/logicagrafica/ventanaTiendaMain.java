package logicagrafica;

import logicatienda.tienda.Tienda;

import javax.swing.*;
import java.awt.*;


public class ventanaTiendaMain extends JFrame {
    public final int width = 1280;
    public final int height = 800;//PROPORCION 8:5
    public ventanaTiendaMain(){
        //DEBO AÑADIR UN JLABEL PARA EL PRESUPUESTO
        this.setTitle("Tienda Principal");
        this.setSize(width, height); // En caso de querer cambiar la ventana por favor mantener la proporcion de 8:5
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        int alto = height/5-6;
        int ancho = width/8;
        Tienda tiendalogica = new Tienda(30000);
        CasillaMascota[] casillas = new CasillaMascota[22];
        for (int fila = 0,index=0; fila < 5; fila++) {
            int columnas = (fila <= 2) ? 2 : 8;
            for (int col = 0; col < columnas; col++) {
                int x = col * ancho;
                int y = fila * alto;
                casillas[index] = new CasillaMascota(x, y, ancho, alto, null, tiendalogica);
                this.add(casillas[index]);
                index++;
            }
        }


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

    @Override
    public void dispose() {
        // Desregistrar observers antes de cerrar
        for (Component comp : this.getContentPane().getComponents()) {
            if (comp instanceof CasillaMascota) {
                ((CasillaMascota) comp).removerObserver();
            }
        }
        super.dispose();
    }
}
