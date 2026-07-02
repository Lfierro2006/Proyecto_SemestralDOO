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
        this.setLayout(null);
        int alto = height/5-6;
        int ancho = width/8;
        //Considere usar un for, pero considere que tardaba menos haciendolo a mano que pensando en como hacer la forma de "L" en un ciclo for
        CasillaMascota casilla1 = new CasillaMascota(0 ,0, ancho, alto, null );
        CasillaMascota casilla2 = new CasillaMascota(ancho,0, ancho, alto, null );
        CasillaMascota casilla3 = new CasillaMascota(0 ,alto, ancho, alto, null );
        CasillaMascota casilla4 = new CasillaMascota(ancho ,alto, ancho, alto, null );
        CasillaMascota casilla5 = new CasillaMascota(0 ,alto*2, ancho, alto, null );
        CasillaMascota casilla6 = new CasillaMascota(ancho ,alto*2, ancho, alto, null );
        CasillaMascota casilla7 = new CasillaMascota(0 ,alto*3, ancho, alto, null );
        CasillaMascota casilla8 = new CasillaMascota(ancho ,alto*3, ancho, alto, null );
        CasillaMascota casilla9 = new CasillaMascota(ancho*2 ,alto*3, ancho, alto, null );
        CasillaMascota casilla10 = new CasillaMascota(ancho*3 ,alto*3, ancho, alto, null );
        CasillaMascota casilla11 = new CasillaMascota(ancho*4,alto*3, ancho, alto, null );
        CasillaMascota casilla12 = new CasillaMascota(ancho*5 ,alto*3, ancho, alto, null );
        CasillaMascota casilla13= new CasillaMascota(ancho *6 ,alto*3, ancho, alto, null );
        CasillaMascota casilla14 = new CasillaMascota(ancho*7 ,alto*3, ancho, alto, null );
        CasillaMascota casilla15 = new CasillaMascota(0 ,alto*4, ancho, alto, null );
        CasillaMascota casilla16 = new CasillaMascota(ancho ,alto*4, ancho, alto, null );
        CasillaMascota casilla17 = new CasillaMascota(ancho*2 ,alto*4, ancho, alto, null );
        CasillaMascota casilla18 = new CasillaMascota(ancho*3 ,alto*4, ancho, alto, null );
        CasillaMascota casilla19 = new CasillaMascota(ancho*4 ,alto*4, ancho, alto, null );
        CasillaMascota casilla20 = new CasillaMascota(ancho*5 ,alto*4, ancho, alto, null );
        CasillaMascota casilla21 = new CasillaMascota(ancho*6 ,alto*4, ancho, alto, null );
        CasillaMascota casilla22 = new CasillaMascota(ancho*7 ,alto*4, ancho, alto, null );

        this.add(casilla1);
        this.add(casilla2);
        this.add(casilla3);
        this.add(casilla4);
        this.add(casilla5);
        this.add(casilla6);
        this.add(casilla7);
        this.add(casilla8);
        this.add(casilla9);
        this.add(casilla10);
        this.add(casilla11);
        this.add(casilla12);
        this.add(casilla13);
        this.add(casilla14);
        this.add(casilla15);
        this.add(casilla16);
        this.add(casilla17);
        this.add(casilla18);
        this.add(casilla19);
        this.add(casilla20);
        this.add(casilla21);
        this.add(casilla22);


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
