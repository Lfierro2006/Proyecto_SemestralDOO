package logicagrafica;

import logicatienda.comprador.Comprador;
import logicatienda.tienda.Tienda;
import logicatienda.usuario.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa el mostrador de la tienda.
 * Contiene el fondo y los botones para acceder a las diferentes tiendas.
 */
public class Mostrador extends JPanel {
    private Image bg;//BACKGROUND O IMAGEN DE FONDO

    private JButton btnMonitor1;
    private JButton btnMonitor2;
    private JButton btnMonitor3;
    private Tienda tiendaLogica;
    private Runnable actualizarP;

    private JLabel lblDialogoCliente;
    private JLabel lblSpriteCliente;
    private ImageIcon[] spritesClientes;
    private int turnoClienteActual = 0;

    /**
     * Constructor del mostrador.
     * @param x Posición X
     * @param y Posición Y
     * @param ancho Ancho del panel
     * @param alto Alto del panel
     * @param tienda Referencia a la tienda lógica
     */
    public Mostrador(int x, int y, int ancho, int alto, Tienda tienda, Runnable actualizarP){
        this.tiendaLogica=tienda;
        this.actualizarP=actualizarP;

        JFrame framePadre = (JFrame) SwingUtilities.getWindowAncestor(this);
        this.setBounds(x, y ,ancho, alto);
        this.setLayout(null);
        int AN= ancho/6;
        int AL= alto/3;

        ImageIcon BG= cargarImagen("bg.png", ancho*6, alto*3);
        this.bg = BG.getImage();
        btnMonitor1 = new JButton("COMPRAR MEDICINA     ", cargarImagen("darMed.png", 40,40));
        btnMonitor2 = new JButton("COMPRAR COMIDA        ", cargarImagen("darComida.png", 40,40));
        btnMonitor3 = new JButton("COMPRAR ANIMALES    ", cargarImagen("perro.png", 30 ,30));

        btnMonitor1.setHorizontalAlignment(SwingConstants.LEFT);
        btnMonitor2.setHorizontalAlignment(SwingConstants.LEFT);
        btnMonitor3.setHorizontalAlignment(SwingConstants.LEFT);

        btnMonitor1.setMargin(new Insets(2,0,2,0));
        btnMonitor2.setMargin(new Insets(2,2,2,0));
        btnMonitor3.setMargin(new Insets(2,2,2,0));

        btnMonitor1.setBounds(AN+1, (int) (AL*1.69), (int) (AN*1.19), AL/5);
        btnMonitor2.setBounds(AN+1, (int) (AL*1.88), (int) (AN*1.19), AL/5);
        btnMonitor3.setBounds(AN+1, (int) (AL*2.07), (int) (AN*1.19), AL/5);

        btnMonitor1.addActionListener(e -> {new TiendaItem(framePadre, "Medicina", 150, Item.MEDICINA, tiendaLogica, this.actualizarP).setVisible(true);});
        btnMonitor2.addActionListener(e -> {new TiendaItem(framePadre, "Comida", 200, Item.COMIDA, tiendaLogica, this.actualizarP).setVisible(true);});
        btnMonitor3.addActionListener(e -> {new TiendaAnimales(framePadre,tiendaLogica, actualizarP).setVisible(true);});

        this.add(btnMonitor1);
        this.add(btnMonitor2);
        this.add(btnMonitor3);

        lblDialogoCliente = new JLabel("");
        lblDialogoCliente.setFont(new Font("Arial", Font.BOLD, 24));
        lblDialogoCliente.setForeground(Color.WHITE);

        lblDialogoCliente.setBounds(AN * 3, AL, AN * 3, AL);
        this.add(lblDialogoCliente);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (tiendaLogica.getCompradoractual()==null){
                    Comprador nuevoCliente = new Comprador(1400);
                    tiendaLogica.setCompradoractual(nuevoCliente);
                    actualizarLetreroCliente();
                }
            }
        });
        spritesClientes = new ImageIcon[2];


        spritesClientes[0] = cargarImagen("cliente.png", 250, 180);
        spritesClientes[1] = cargarImagen("cliente1.png", 250, 180);
        lblSpriteCliente = new JLabel();
        lblSpriteCliente.setBounds(AN * 3, AL+5, AN*2-70, AL*2-80);
        this.add(lblSpriteCliente);

    }
    public void actualizarLetreroCliente() {
        if (tiendaLogica.getCompradoractual() != null) {
            lblDialogoCliente.setText("¡Hola! Busco un " + tiendaLogica.getCompradoractual().getTipoMascotaDeseada());

            lblSpriteCliente.setIcon(spritesClientes[turnoClienteActual]);
            turnoClienteActual++;
            if (turnoClienteActual>1){
                turnoClienteActual=0;
            }
        } else {
            lblDialogoCliente.setText(""); // Se borra el texto cuando no hay cliente
            lblSpriteCliente.setIcon(null);

        }
    }



    /**
     * Carga y redimensiona una imagen desde la carpeta Sprites.
     * @param nombreArchivo Nombre del archivo de imagen
     * @param ancho Ancho deseado en píxeles
     * @param alto Alto deseado en píxeles
     * @return ImageIcon redimensionado, o un icono vacío si falla
     */
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

    /**
     * Dibuja el fondo del mostrador.
     * @param g Objeto Graphics para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujamos la imagen desde la esquina (0,0) estirándola al ancho y alto total del panel
        if (bg != null) {
            g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
