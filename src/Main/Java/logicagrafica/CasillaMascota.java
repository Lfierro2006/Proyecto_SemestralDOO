package logicagrafica;
import logicatienda.animales.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CasillaMascota extends PanelTMAnimal{
    private Animal mascota;
    private JButton btnMedicina;
    private JButton btnAlimentar;
    private JButton btnLimpiar;
    private JButton btnJugar;
    private ImageIcon alertaHambre;
    private ImageIcon alertaSucio;
    private ImageIcon alertaTriste;
    private ImageIcon alertaEnfermo;


    private boolean mostrandoMenu = false;

    public CasillaMascota(int x, int y, int ancho, int alto, Animal mascota){
        super(x,y,ancho,alto, "");
        this.mascota=mascota;

        alertaEnfermo= cargarImagen("alertaSal.png",15,15);
        alertaHambre= cargarImagen("alertaHam.png",15,15);
        alertaSucio = cargarImagen("alertaHig.png", 15 , 15);
        alertaTriste = cargarImagen("alertaFel.png", 15, 15);


        this.setLayout(new GridLayout(4,1));

        btnMedicina = new JButton("Dar Medicina", cargarImagen("darMed.png",30,30));
        btnAlimentar = new JButton("Alimentar", cargarImagen("darComida.png", 30, 30));
        btnLimpiar = new JButton("Limpiar Habitat", cargarImagen("limpiar.png", 30, 30));
        btnJugar = new JButton("Jugar", cargarImagen("jugar.png", 30, 30 ));

        btnMedicina.addActionListener(e -> {mascota.Curar(40); ocultarBotones();});
        btnAlimentar.addActionListener(e -> {mascota.Alimentar(40); ocultarBotones();});
        btnLimpiar.addActionListener(e -> {mascota.Limpiar(); ocultarBotones();});
        btnJugar.addActionListener(e -> {mascota.Jugar(); ocultarBotones();});
        this.add(btnMedicina);
        this.add(btnAlimentar);
        this.add(btnLimpiar);
        this.add(btnJugar);
        ocultarBotones();
    }
    /**
     * Metodo ayudante para cargar y redimensionar iconos de forma segura
     * @param nombreArchivo El nombre de la imagen (ej. "carne.png").
     * @param ancho El ancho deseado en píxeles.
     * @param alto El alto deseado en píxeles.
     * @return El ImageIcon listo para usar, o un icono vacío si falla.
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

    private void mostrarBotones() {
        mostrandoMenu = true;
        btnMedicina.setVisible(true);
        btnAlimentar.setVisible(true);
        btnJugar.setVisible(true);
        btnLimpiar.setVisible(true);
        btnMedicina.setVisible(true);
        this.repaint();
    }
    private void ocultarBotones() {
        mostrandoMenu = false;
        btnMedicina.setVisible(false);
        btnAlimentar.setVisible(false);
        btnJugar.setVisible(false);
        btnLimpiar.setVisible(false);
        //repintar el animal y los iconos;
        this.repaint();
    }


    @Override
    public String getToolTipText(MouseEvent event){//MENSAJE CUANDO EL MOUSE PASA POR ENCIMA
        if (estaVacia()) return "Vacio";
        return "<html>" +
                "<b>" + mascota.getNombre() + "</b><br>" +
                "<hr>" + // Una línea divisoria
                "Felicidad: " + mascota.getNivel(Animal.Estadistica.FELICIDAD) + "/100<br>" +
                "Saciedad: " + mascota.getNivel(Animal.Estadistica.SACIEDAD) + "/100<br>" +
                "Higiene: " + mascota.getNivel(Animal.Estadistica.HIGIENE) + "/100<br>" +
                "Salud: " + mascota.getNivel(Animal.Estadistica.SALUD) + "/100" +
                "</html>";

    }
    public boolean estaVacia() {
        return this.mascota == null;
    }
    //me preocupo despues
    @Override
    public void ejecutarAccion(MouseEvent e) {
        if (estaVacia()) {

        } else {
            // Si tiene animal: Izquierdo = Menú, Derecho = Ocultar
            if (SwingUtilities.isLeftMouseButton(e)) {
                mostrarBotones();
            } else if (SwingUtilities.isRightMouseButton(e)) {
                ocultarBotones();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (estaVacia()) {
            g.setColor(Color.GRAY);
            g.drawString("[Vacío]", 10, 20);
        } else if (!mostrandoMenu) {
            g.setColor(Color.BLACK);
            g.drawString(mascota.getNombre(), 10, 20);

            boolean[] estados = mascota.getTodosLosEstados();
            int yIcono = 40;

            //  ALERTA DE SALUD
            if (!estados[3]){
                g.drawImage(alertaEnfermo.getImage(), 10 ,yIcono, null);
                yIcono +=20;
            }

            // ALERTA DE HAMBRE
            if (!estados[1]) {

                g.drawImage(alertaHambre.getImage(), 10, yIcono, null);

                yIcono += 20;
            }

            // ALERTA DE HIGIENE
            if (!estados[2]) {
                g.drawImage(alertaSucio.getImage(), 10, yIcono, null);

                yIcono += 20;
            }

            // ALERTA DE FELICIDAD
            if (!estados[0]) {
                g.drawImage(alertaTriste.getImage(), 10, yIcono, null);


            }
        }
    }
}
