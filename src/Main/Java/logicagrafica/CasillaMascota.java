package logicagrafica;
import logicatienda.animales.*;
import logicatienda.observers.AnimalObserver;
import logicatienda.habitat.*;
import logicatienda.tienda.Tienda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class CasillaMascota extends PanelTMAnimal implements AnimalObserver {
    private Habitat habitat;
    private Tienda tiendaLogica;
    private JButton btnMedicina , btnAlimentar, btnLimpiar, btnJugar;
    private ImageIcon alertaHambre, alertaSucio, alertaTriste, alertaEnfermo;
    private JButton btnBuyJaula, btnBuyCama, btnBuyPecera;
    // Animal mascota = habitat.getResidente();
    private JPanel panelMenu;
    private boolean mostrandoMenuAnimal = false;
    private boolean mostrandoMenuHabitat = false; //Menu para comprar habitats
    public CasillaMascota(int x, int y, int ancho, int alto, Habitat habitat, Tienda tienda){
        super(x,y,ancho,alto, "");
        this.habitat=habitat;
        this.tiendaLogica= tienda;
        if (this.habitat != null && !this.habitat.estaVacio()) {
            this.habitat.getResidente().addObserver(this);
        }

        alertaEnfermo= cargarImagen("alertaSal.png",15,15);
        alertaHambre= cargarImagen("alertaHam.png",15,15);
        alertaSucio = cargarImagen("alertaHig.png", 15 , 15);
        alertaTriste = cargarImagen("alertaFel.png", 15, 15);


        this.setLayout(new BorderLayout());
        panelMenu = new JPanel();
        panelMenu.setOpaque(false);
        this.add(panelMenu, BorderLayout.CENTER);



        btnBuyJaula = new JButton("Jaula ($)", cargarImagen("Jaula.png", 40 , 40));
        btnBuyPecera = new JButton("Pecera ($)", cargarImagen("pescera.png",40,40));
        btnBuyCama = new JButton("Cama ($)", cargarImagen("cama.png", 40, 40));

        btnMedicina = new JButton("Dar Medicina", cargarImagen("darMed.png",30,30));
        btnAlimentar = new JButton("Alimentar", cargarImagen("darComida.png", 30, 30));
        btnLimpiar = new JButton("Limpiar Habitat", cargarImagen("limpiar.png", 30, 30));
        btnJugar = new JButton("Jugar", cargarImagen("jugar.png", 30, 30 ));

        java.awt.event.MouseAdapter detectorClicDerecho = new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Si el clic que recibió el botón fue el derecho
                if (SwingUtilities.isRightMouseButton(e)) {
                    ocultarTodosLosBotones(); //
                }
            }
        };

        //BOTONES PARA EL MENU PARA COMPRAR EL HABITAT
        btnBuyJaula.addMouseListener(detectorClicDerecho);
        btnBuyPecera.addMouseListener(detectorClicDerecho);
        btnBuyCama.addMouseListener(detectorClicDerecho);

        btnBuyJaula.addActionListener(e -> intentarComprarHabitat(new Jaula(), 100)); //LOS PRECIOS SON PLACEHOLDERS
        btnBuyPecera.addActionListener(e -> intentarComprarHabitat(new Pecera(), 150));
        btnBuyCama.addActionListener(e -> intentarComprarHabitat(new Cama(), 80));

        //BOTONES PARA INTERACTUAR CON EL ANIMAL DEL HABITAT YA PUESTO
        btnMedicina.addMouseListener(detectorClicDerecho);
        btnAlimentar.addMouseListener(detectorClicDerecho);
        btnLimpiar.addMouseListener(detectorClicDerecho);
        btnJugar.addMouseListener(detectorClicDerecho);

        btnMedicina.addActionListener(e -> {if(tieneAnimal()) habitat.getResidente().Curar(40); ocultarTodosLosBotones();});
        btnAlimentar.addActionListener(e -> {if(tieneAnimal()) habitat.getResidente().Alimentar(); ocultarTodosLosBotones();});
        btnLimpiar.addActionListener(e -> {if(tieneAnimal()) habitat.getResidente().Limpiar(); ocultarTodosLosBotones();});
        btnJugar.addActionListener(e -> {if(tieneAnimal()) habitat.getResidente().Jugar(); ocultarTodosLosBotones();});

        ocultarTodosLosBotones();
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
    private void intentarComprarHabitat(Habitat nuevoHabitat, int costo) {
        //Comprobacion si se pudo comprar o no
        boolean compraExitosa = tiendaLogica.comprarHabitat(nuevoHabitat, costo);

        if (compraExitosa) {
            this.habitat = nuevoHabitat; // La casilla ahora es dueña de este hábitat
            System.out.println("Hábitat instalado en esta casilla.");
        } else {
            System.out.println("No se pudo comprar el hábitat (falta dinero).");

        }

        ocultarTodosLosBotones(); // Redibuja la casilla con su nuevo estado
    }
    private void mostrarBotonesHabitat() {
        mostrandoMenuHabitat = true;
        mostrandoMenuAnimal = false;
        //Vaciar cualquier boton previo por si acaso
        panelMenu.removeAll();
        //formar las filas para los botones
        panelMenu.setLayout(new GridLayout(3, 1, 0, 2));
        //Se añaden los botones
        panelMenu.add(btnBuyJaula);
        panelMenu.add(btnBuyPecera);
        panelMenu.add(btnBuyCama);
        //Se actualiza
        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void mostrarBotonesAnimal() {
        mostrandoMenuHabitat = false;
        mostrandoMenuAnimal = true;

        panelMenu.removeAll();

        panelMenu.setLayout(new GridLayout(4, 1, 0, 2));

        panelMenu.add(btnMedicina);
        panelMenu.add(btnAlimentar);
        panelMenu.add(btnLimpiar);
        panelMenu.add(btnJugar);

        panelMenu.revalidate();
        panelMenu.repaint();
    }


    private void ocultarTodosLosBotones() {
        mostrandoMenuAnimal = false;
        mostrandoMenuHabitat= false;
        panelMenu.removeAll();
        //repintar el animal y los iconos;
        panelMenu.revalidate();
        panelMenu.repaint();
    }




    public boolean noTieneHabitat() {
        return this.habitat == null;
    }

    public boolean tieneAnimal() {
        return this.habitat != null && !this.habitat.estaVacio();
    }
    @Override
    public void ejecutarAccion(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {
                ocultarTodosLosBotones();
                return;
            }


        if (SwingUtilities.isLeftMouseButton(e)) {
            if (noTieneHabitat()){
                //opcion de botones para comprar habitat
                mostrarBotonesHabitat();
            }
            else if (this.habitat.estaVacio()){ //HAY HABITAT PERO NO HAY ANIMAL

            }
            else{ //HAY ANIMAL Y HABITAT
                mostrarBotonesAnimal();
            }
        }

    }




    @Override
    public String getToolTipText(MouseEvent event){//MENSAJE CUANDO EL MOUSE PASA POR ENCIMA
        if (noTieneHabitat()) return "Vacio";
        if (habitat.estaVacio()) return "Animal Pendiente";
        Animal mascota = habitat.getResidente();
        return "<html>" +
                "<b>" + mascota.getNombre() + "</b><br>" +
                "<hr>" + // Una línea divisoria
                "Felicidad: " + mascota.getNivel(Animal.Estadistica.FELICIDAD) + "/100<br>" +
                "Saciedad: " + mascota.getNivel(Animal.Estadistica.SACIEDAD) + "/100<br>" +
                "Higiene: " + mascota.getNivel(Animal.Estadistica.HIGIENE) + "/100<br>" +
                "Salud: " + mascota.getNivel(Animal.Estadistica.SALUD) + "/100" +
                "</html>";

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (noTieneHabitat()) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("[VACIO]", 10, 20);

        }
        else if (habitat.estaVacio()) {
            g.setColor(Color.GRAY);
            g.drawString("Hábitat: " + habitat.getClass().getSimpleName(), 10, 20);
            g.drawString("[Esperando Mascota]", 10, 40);

        } else if (!mostrandoMenuAnimal) {
            Animal mascota = habitat.getResidente();
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
    @Override
    public void onEstadisticasCambiadas(Animal animal) {
        this.repaint();
    }

    @Override
    public void onEstadoCambiado(Animal animal) {
        this.repaint();
    }

    public void removerObserver() {
        Animal mascota= habitat.getResidente();
        if (mascota != null) {
            mascota.removeObserver(this);
        }
    }
}