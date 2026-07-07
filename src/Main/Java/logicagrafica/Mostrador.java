package logicagrafica;

import logicatienda.tienda.Tienda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Mostrador extends JPanel {
    private Image bg;//BACKGROUND O IMAGEN DE FONDO

    private JButton btnMonitor1;
    private JButton btnMonitor2;
    private Tienda tiendaLogica;
    //DEBO AÑADIR LA FUNCIONALIDAD DE LOS BOTONES
    public Mostrador(int x, int y, int ancho, int alto, Tienda tienda){
        this.tiendaLogica=tienda;
        this.setBounds(x, y ,ancho, alto);
        this.setLayout(null);
        int AN= ancho/6;
        int AL= alto/3;

        ImageIcon BG= cargarImagen("bg.png", ancho*6, alto*3);
        this.bg = BG.getImage();
        btnMonitor1 = new JButton("COMPRAR MEDICINA     ", cargarImagen("darMed.png", 40,40));
        btnMonitor2 = new JButton("COMPRAR COMIDA        ", cargarImagen("darComida.png", 40,40));
        btnMonitor1.setHorizontalAlignment(SwingConstants.LEFT);
        btnMonitor2.setHorizontalAlignment(SwingConstants.LEFT);
        btnMonitor1.setMargin(new Insets(2,0,2,0));
        btnMonitor2.setMargin(new Insets(2,2,2,0));
        btnMonitor1.setBounds(AN+1,(int) (AL*1.69),(int) (AN*1.19), AL/4+10);
        btnMonitor2.setBounds(AN+1,(int) (AL*1.99), (int) (AN*1.19), AL/4+10);
        this.add(btnMonitor1);
        this.add(btnMonitor2);
    }
    private ImageIcon cargarImagen(String nombreArchivo, int ancho, int alto){
        //Buscar la imagen
        java.net.URL urlImagen = getClass().getResource("Sprites/" + nombreArchivo);
        //Cargar la imagen y ajustar el tamaño
        if (urlImagen != null) {
            ImageIcon spriteOriginal = new ImageIcon(urlImagen);
            Image spriteEscalado = spriteOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
            return new ImageIcon(spriteEscalado);
        }
        else{ //en caso de no cargar
            System.out.println("NO SE ENCONTRO EL SPRITE" + nombreArchivo);
            return new ImageIcon();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujamos la imagen desde la esquina (0,0) estirándola al ancho y alto total del panel
        if (bg != null) {
            g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
