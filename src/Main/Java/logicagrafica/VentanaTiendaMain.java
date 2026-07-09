package logicagrafica;

import logicatienda.animales.*;
import logicatienda.tienda.Tienda;
import logicatienda.usuario.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la tienda de mascotas.
 * Contiene el mostrador, las casillas y la interfaz principal del juego.
 */
public class VentanaTiendaMain extends JFrame {
    public final int width = 1280;
    public final int height = 800;//PROPORCION 8:5
    private Tienda tiendalogica;
    private Mostrador mostrador;
    private JLabel Presupuesto;
    private CasillaMascota[] casillas;
    private Runnable actualizarP;
    private Timer relojJuego;
    private Usuario Jugador;

    /**
     * Constructor de la ventana principal.
     * Inicializa la tienda, el mostrador y las casillas.
     * NOTA: Esta ventana no tiene ajuste dinámico de resolución.
     */
    public VentanaTiendaMain(){
        //NOTA: ESTA VENTANA NO TIENE AJUSTE DINAMICO DE NINGUN TIPO,
        // SI SE QUIERE CAMBIAR SU RESOLUCION SE DEBEN CAMBIAR LOS VALORES width
        // y height y volver a ejecutar la virtual machine
        this.setTitle("Tienda Principal");
        this.setSize(width, height); // En caso de querer cambiar la ventana por favor mantener la proporcion de 8:5
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        int alto = height/5-7;
        int ancho = width/8-2;

        tiendalogica = new Tienda(30000);


        Presupuesto = new JLabel("$" + tiendalogica.getUsuario().getDinero());

        Presupuesto.setFont(new Font("Arial", Font.BOLD, 70));
        Presupuesto.setForeground(new Color(49, 243, 49));
        Presupuesto.setBounds((int)(ancho*6.3),(int) (alto*2.3),(int)(ancho*1.6),(int)(alto*0.8));
        this.add(Presupuesto, 0);
        Runnable actualizarPr=()  -> {
            Presupuesto.setText("$" + tiendalogica.getUsuario().getDinero());
            mostrador.actualizarLetreroCliente();
        };
        mostrador = new Mostrador(ancho*2,0 ,ancho*6 ,alto*3 ,tiendalogica, actualizarPr);
        this.add (mostrador);
        casillas = new CasillaMascota[22];
        for (int fila = 0,index=0; fila < 5; fila++) {
            int columnas = (fila <= 2) ? 2 : 8;
            for (int col = 0; col < columnas; col++) {
                int x = col * ancho;
                int y = fila * alto;
                casillas[index] = new CasillaMascota(x, y, ancho, alto, null, tiendalogica, actualizarPr);
                this.add(casillas[index]);
                index++;
            }
        }
        relojJuego = new Timer(10000, e -> avanzarTiempo());
        relojJuego.start();

    }
    private void avanzarTiempo(){
        for(CasillaMascota casilla : casillas){
            if (casilla != null && casilla.tieneAnimal()){
                Animal mascota =casilla.getHabitat().getResidente();
                mascota.ejecutarEstado();
            }
        }
    }

    /**
     * Obtiene el alto de la ventana.
     * @return Alto en píxeles
     */
    @Override
    public int getHeight() {
        return height;
    }


    /**
     * Obtiene el alto de la ventana.
     * @return Alto en píxeles
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Punto de entrada principal del juego.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        VentanaTiendaMain v = new VentanaTiendaMain();
        v.setVisible(true);
    }

    /**
     * Libera los recursos de la ventana.
     * Desregistra los observers antes de cerrar para evitar memory leaks.
     */
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
