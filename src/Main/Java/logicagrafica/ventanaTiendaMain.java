package logicagrafica;

import logicatienda.tienda.Tienda;

import javax.swing.*;
import java.awt.*;


public class ventanaTiendaMain extends JFrame {
    public final int width = 1280;
    public final int height = 800;//PROPORCION 8:5
    private Tienda tiendalogica;
    private Mostrador mostrador;
    private JLabel Presupuesto;
    private Runnable actualizarP;
    public ventanaTiendaMain(){ //NOTA: ESTA VENTANA NO TIENE AJUSTE DINAMICO DE NINGUN TIPO, SI SE QUIERE CAMBIAR SU RESOLUCION SE DEBEN CAMBIAR LOS VALORES width y height y volver a ejecutar la virtual machine

        this.setTitle("Tienda Principal");
        this.setSize(width, height); // En caso de querer cambiar la ventana por favor mantener la proporcion de 8:5
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        int alto = height/5-7;
        int ancho = width/8-2;

        tiendalogica = new Tienda(30000);
        mostrador = new Mostrador(ancho*2,0 ,ancho*6 ,alto*3 ,tiendalogica);
        this.add (mostrador);
        Presupuesto = new JLabel("$" + tiendalogica.getPresupuesto());

        Presupuesto.setFont(new Font("Arial", Font.BOLD, 70));
        Presupuesto.setForeground(new Color(49, 243, 49));
        Presupuesto.setBounds((int)(ancho*6.3),(int) (alto*2.3),(int)(ancho*1.6),(int)(alto*0.8));
        this.add(Presupuesto, 0);
        Runnable ActualizarPresupuesto=()  -> {
            Presupuesto.setText("$" + tiendalogica.getPresupuesto());
        };
        CasillaMascota[] casillas = new CasillaMascota[22];
        for (int fila = 0,index=0; fila < 5; fila++) {
            int columnas = (fila <= 2) ? 2 : 8;
            for (int col = 0; col < columnas; col++) {
                int x = col * ancho;
                int y = fila * alto;
                casillas[index] = new CasillaMascota(x, y, ancho, alto, null, tiendalogica, ActualizarPresupuesto);
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
