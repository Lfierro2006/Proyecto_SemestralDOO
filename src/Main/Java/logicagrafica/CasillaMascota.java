package logicagrafica;
import logicatienda.animales.*;
import logicatienda.observers.AnimalObserver;
import logicatienda.habitat.*;
import logicatienda.tienda.Tienda;
import logicatienda.animales.Estadistica;
import logicatienda.usuario.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class CasillaMascota extends PanelTMAnimal implements AnimalObserver {
    private Habitat habitat;
    private Tienda tiendaLogica;
    private JButton btnMedicina , btnAlimentar, btnLimpiar, btnJugar;
    private ImageIcon alertaHambre, alertaSucio, alertaTriste, alertaEnfermo;
    private ImageIcon GCama, GJaula, GPecera, GPerro, GGato, GAve, GPez; //G de Grafico
    private JButton btnBuyJaula, btnBuyCama, btnBuyPecera;
    private JButton btnAñadirPerro, btnAñadirGato, btnAñadirAve, btnAñadirPez;
    // Animal mascota = habitat.getResidente();
    private Runnable actualizarP;
    private JPanel panelMenu;
    //  PRECIOS DE LOS HABITATS
    private final int $Jaula = 200;
    private final int $cama = 180;
    private final int $Pecera = 190;

    private final String[] listaDeNombres={"Goku","Ezio", "Patata","Zeus", "Pepe", "Grace","Tolosin","Melasa","Robbie", "Roar", "Shrek", "Mahoraga" , "Rafael", "Miguel Angelo", "Donnatelo", "Leonardo", "Platon", "Socrates", "Mario" ,
                            "Silvio", "Haaland", "Talon", "Zilean", "Jarvan", "Nilah", "Messi", "Vegetta", "Tito Soto", "Alexis", "Gustavo", "Pedro", "Rene", "Miku", "Majin Boo", "Fernanfloo", "Felipe", "Nestle","Corxea", "Bond",  "Braviary", "John Doe", "Jane Doe",
                            "Cupcake","Petrus", "Honda", "Daniel", "El Tata", "Frederickson", "Teao", "Chipp", "Agnes"};

    private boolean mostrandoMenuAnimal = false;
    private boolean mostrandoMenuHabitat = false; //Menu para comprar habitats
    private boolean mostrandoMenuCama= false;
    private boolean mostandoMenuPescera= false;
    private boolean mostrandoMenuJaula = false;

    public CasillaMascota(int x, int y, int ancho, int alto, Habitat habitat, Tienda tienda, Runnable actualizarP){
        super(x,y,ancho,alto, "");
        this.habitat=habitat;
        this.tiendaLogica= tienda;
        this.actualizarP=actualizarP;
        if (this.habitat != null && !this.habitat.estaVacio()) {
            this.habitat.getResidente().addObserver(this);
        }

        GCama =cargarImagen("cama.png",160,160);
        GPecera =cargarImagen("pescera.png",160,160);
        GJaula = cargarImagen("Jaula.png",160,160);
        GGato = cargarImagen("gato.png",100,100);
        GPerro = cargarImagen("perro.png", 100,100);
        GAve = cargarImagen("ave.png", 100,100);
        GPez = cargarImagen("Pezp.png",100,100);


        alertaEnfermo= cargarImagen("alertaSal.png",15,15);
        alertaHambre= cargarImagen("alertaHam.png",15,15);
        alertaSucio = cargarImagen("alertaHig.png", 15 , 15);
        alertaTriste = cargarImagen("alertaFel.png", 15, 15);


        this.setLayout(new BorderLayout());
        panelMenu = new JPanel();
        panelMenu.setOpaque(false);
        this.add(panelMenu, BorderLayout.CENTER);



        btnBuyJaula = new JButton("Jaula ($200)", cargarImagen("Jaula.png", 40 , 40));
        btnBuyPecera = new JButton("Pecera ($190)", cargarImagen("pescera.png",30,30));
        btnBuyCama = new JButton("Cama ($180)", cargarImagen("cama.png", 40, 40));


        btnAñadirPerro= new JButton("Perro: "+this.tiendaLogica.getUsuario().getCantItem(Item.PERRO.getIndex()), cargarImagen("perro.png",40,40));
        btnAñadirGato= new JButton("Gato: "+this.tiendaLogica.getUsuario().getCantItem(Item.GATO.getIndex()), cargarImagen("gato.png",40,40));
        btnAñadirAve= new JButton("Ave: "+this.tiendaLogica.getUsuario().getCantItem(Item.AVE.getIndex()), cargarImagen("ave.png",40,40));
        btnAñadirPez= new JButton("Pez: "+this.tiendaLogica.getUsuario().getCantItem(Item.PEZ.getIndex()), cargarImagen("Pezp.png",40,40));

        btnMedicina = new JButton("Dar Medicina: " +this.tiendaLogica.getUsuario().getCantItem(Item.MEDICINA.getIndex()), cargarImagen("darMed.png",30,30));
        btnAlimentar = new JButton("Alimentar: "+this.tiendaLogica.getUsuario().getCantItem(Item.COMIDA.getIndex()), cargarImagen("darComida.png", 30, 30));
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

        btnBuyJaula.addActionListener(e -> intentarComprarHabitat(new Jaula(), $Jaula));
        btnBuyPecera.addActionListener(e -> intentarComprarHabitat(new Pecera(), $Pecera));
        btnBuyCama.addActionListener(e -> intentarComprarHabitat(new Cama(), $cama));

        //BOTONES PARA AÑADIR ANIMALES YA COMPRADOS AL HABITAT

        btnAñadirPerro.addMouseListener(detectorClicDerecho);
        btnAñadirGato.addMouseListener(detectorClicDerecho);
        btnAñadirAve.addMouseListener(detectorClicDerecho);
        btnAñadirPez.addMouseListener(detectorClicDerecho);

        btnAñadirPerro.addActionListener(e -> {
            if (tiendaLogica.getUsuario().getCantItem(Item.PERRO.getIndex())>0){
                tiendaLogica.getUsuario().restarItem(Item.PERRO.getIndex());
                intentarComprarAnimal(new Perro(RandomName()),0);
                if(actualizarP!=null)actualizarP.run();
            }
            else{
                System.out.println("No hay tal animal disponible");
            }
        });
        btnAñadirGato.addActionListener(e -> {
            if (tiendaLogica.getUsuario().getCantItem(Item.GATO.getIndex())>0){
                tiendaLogica.getUsuario().restarItem(Item.GATO.getIndex());
                intentarComprarAnimal(new Gato(RandomName()),0);
            }
            else{
                System.out.println("No hay tal animal disponible");
            }
        });
        btnAñadirAve.addActionListener(e -> {
            if (tiendaLogica.getUsuario().getCantItem(Item.AVE.getIndex())>0){
                tiendaLogica.getUsuario().restarItem(Item.AVE.getIndex());
                intentarComprarAnimal(new Ave(RandomName()),0);
            }
            else{
                System.out.println("No hay tal animal disponible");
            }
        });
        btnAñadirPez.addActionListener(e -> {
            if (tiendaLogica.getUsuario().getCantItem(Item.PEZ.getIndex())>0){
                tiendaLogica.getUsuario().restarItem(Item.PEZ.getIndex());
                intentarComprarAnimal(new Pez(RandomName()),0);
            }
            else{
                System.out.println("No hay tal animal disponible");
            }
        });


        //BOTONES PARA INTERACTUAR CON EL ANIMAL DEL HABITAT YA PUESTO
        btnMedicina.addMouseListener(detectorClicDerecho);
        btnAlimentar.addMouseListener(detectorClicDerecho);
        btnLimpiar.addMouseListener(detectorClicDerecho);
        btnJugar.addMouseListener(detectorClicDerecho);

        btnMedicina.addActionListener(e -> {if(tieneAnimal()){
            int medicina = Item.MEDICINA.getIndex();
            if(medicina>0){
            this.habitat.getResidente().Curar(40);
            tienda.getUsuario().restarItem(medicina);
            }
            else{
                System.out.println("No tienes existencias");
            }

        }
        ocultarTodosLosBotones();
        });
        btnAlimentar.addActionListener(e -> {if(tieneAnimal()){
            int comida = Item.COMIDA.getIndex();
            if (comida > 0){
                this.habitat.getResidente().Alimentar();
                tienda.getUsuario().restarItem(comida);
            }
            else{
                System.out.println("No tienes existencias");
            }
        }

            ocultarTodosLosBotones();});
        btnLimpiar.addActionListener(e -> {if(tieneAnimal()) this.habitat.getResidente().Limpiar(); ocultarTodosLosBotones();});
        btnJugar.addActionListener(e -> {if(tieneAnimal()) this.habitat.getResidente().Jugar(); ocultarTodosLosBotones();});

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
    private String RandomName(){
        int Aleatorio = (int)(Math.random() * listaDeNombres.length);
        return listaDeNombres[Aleatorio];
    }


    private void intentarComprarHabitat(Habitat nuevoHabitat, int costo) {
        //Comprobacion si se pudo comprar o no
        boolean compraExitosa = tiendaLogica.comprarHabitat(nuevoHabitat, costo);

        if (compraExitosa) {
            this.habitat = nuevoHabitat; // La casilla ahora es dueña de este hábitat
            System.out.println("Hábitat instalado en esta casilla.");
            if (this.actualizarP != null) {
                this.actualizarP.run();
            }
        } else {
            System.out.println("No se pudo comprar el hábitat (falta dinero).");

        }

        ocultarTodosLosBotones(); // Redibuja la casilla con su nuevo estado
    }
    private void intentarComprarAnimal (Animal nuevoAnimal, int costo){
        boolean compraExitosa = tiendaLogica.comprarAnimal(nuevoAnimal, this.habitat, costo);
        if(compraExitosa){
            nuevoAnimal.addObserver(this); //Observer para el patron
            System.out.println("Animal comprado y alojado con exito");
            if (this.actualizarP!= null) actualizarP.run();
        }

        ocultarTodosLosBotones();
    }

    private void mostrarBotonesHabitat() {
        mostrandoMenuHabitat = true;
        mostrandoMenuAnimal = false;
        mostrandoMenuCama= false;
        mostandoMenuPescera= false;
        mostrandoMenuJaula = false;
        //Vaciar cualquier boton previo por si acaso
        panelMenu.removeAll();
        //formar las filas para los botones
        panelMenu.setLayout(new GridLayout(3, 1, 0, 1));
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
        mostrandoMenuCama= false;
        mostandoMenuPescera= false;
        mostrandoMenuJaula = false;
        panelMenu.removeAll();

        panelMenu.setLayout(new GridLayout(4, 1, 0, 1));

        panelMenu.add(btnMedicina);
        panelMenu.add(btnAlimentar);
        panelMenu.add(btnLimpiar);
        panelMenu.add(btnJugar);

        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void mostrarBotonesAñadirCama(){

        mostrandoMenuAnimal= false;
        mostrandoMenuHabitat = false;
        mostrandoMenuCama= true;
        mostandoMenuPescera= false;
        mostrandoMenuJaula = false;

        btnAñadirPerro.setText("Perro: " + tiendaLogica.getUsuario().getCantItem(Item.PERRO.getIndex()));
        btnAñadirGato.setText("Gato: " + tiendaLogica.getUsuario().getCantItem(Item.GATO.getIndex()));

        panelMenu.removeAll();
        panelMenu.setLayout(new GridLayout(2, 1 ,0, 1));

        panelMenu.add(btnAñadirPerro);
        panelMenu.add(btnAñadirGato);

        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void mostrarBotonesAñadirPez(){

        mostrandoMenuAnimal= false;
        mostrandoMenuHabitat = false;
        mostrandoMenuCama= false;
        mostandoMenuPescera= true;
        mostrandoMenuJaula = false;

        btnAñadirPez.setText("Pez: " + tiendaLogica.getUsuario().getCantItem(Item.PEZ.getIndex()));

        panelMenu.removeAll();
        panelMenu.setLayout(new GridLayout(1, 1 ,0, 1));

        panelMenu.add(btnAñadirPez);


        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void mostrarBotonesAñadirAve(){

        mostrandoMenuAnimal= false;
        mostrandoMenuHabitat = false;
        mostrandoMenuCama= false;
        mostandoMenuPescera= false;
        mostrandoMenuJaula = true;

        btnAñadirAve.setText("Ave: " + tiendaLogica.getUsuario().getCantItem(Item.AVE.getIndex()));

        panelMenu.removeAll();
        panelMenu.setLayout(new GridLayout(1, 1 ,0, 1));

        panelMenu.add(btnAñadirAve);


        panelMenu.revalidate();
        panelMenu.repaint();
    }

    private void ocultarTodosLosBotones() {
        mostrandoMenuAnimal = false;
        mostrandoMenuHabitat= false;
        mostrandoMenuCama= false;
        mostandoMenuPescera= false;
        mostrandoMenuJaula = false;
        panelMenu.removeAll();
        //repintar el animal y los iconos;
        panelMenu.revalidate();
        panelMenu.repaint();
    }


    private void desmantelarHabitat(){ //SOLO SE PUEDE USAR EN UN HABITAT VACIO
        int valorReembolso = 0;
        if (habitat instanceof Jaula) valorReembolso = $Jaula;
        else if (habitat instanceof Pecera) valorReembolso= $Pecera;
        else if (habitat instanceof Cama) valorReembolso= $cama;

        tiendaLogica.reembolso(valorReembolso);
        tiendaLogica.getEspaciosActivos().remove(habitat);
        this.habitat = null;
        if(actualizarP !=null)actualizarP.run();
        System.out.println("Hábitat desmantelado desde la Casilla. Reembolso: $" + valorReembolso);
        ocultarTodosLosBotones();
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
            if(mostrandoMenuHabitat || mostrandoMenuAnimal) ocultarTodosLosBotones(); //OCULTAR BOTONES
            else if (this.habitat != null && this.habitat.estaVacio()) desmantelarHabitat(); //REEMBOLSAR HABITAT
            return;

        }


        if (SwingUtilities.isLeftMouseButton(e)) {
            if (noTieneHabitat()){
                //opcion de botones para comprar habitat
                mostrarBotonesHabitat();
            }
            else if (this.habitat.estaVacio()){ //HAY HABITAT PERO NO HAY ANIMAL
                    if (habitat instanceof Cama) mostrarBotonesAñadirCama();
                    else if (habitat instanceof Pecera) mostrarBotonesAñadirPez();
                    else if (habitat instanceof Jaula) mostrarBotonesAñadirAve();
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
                "Felicidad: " + mascota.getNivel(Estadistica.FELICIDAD) + "/100<br>" +
                "Saciedad: " + mascota.getNivel(Estadistica.SACIEDAD) + "/100<br>" +
                "Higiene: " + mascota.getNivel(Estadistica.HIGIENE) + "/100<br>" +
                "Salud: " + mascota.getNivel(Estadistica.SALUD) + "/100" +
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
            if (habitat instanceof Jaula){
                g.drawImage(GJaula.getImage(), 0,0, getWidth(), getHeight(),null);
            }
            else if (habitat instanceof Cama){
                g.drawImage(GCama.getImage(), 0,0, getWidth(), getHeight(),null);
            }
            else if (habitat instanceof Pecera){
                g.drawImage(GPecera.getImage(), 0,0, getWidth(), getHeight(),null);
            }


        } else if (!mostrandoMenuAnimal && tieneAnimal()) {
            Animal mascota = habitat.getResidente();
            g.setColor(Color.BLACK);
            if (mascota instanceof Perro){
                g.drawImage(GPerro.getImage(),0,0, getWidth(),getHeight(),null );
                g.drawImage(GCama.getImage(), 0,0, getWidth(), getHeight(),null);
            } else if (mascota instanceof Gato) {
                g.drawImage(GGato.getImage(),0,0, getWidth(),getHeight(),null );
                g.drawImage(GCama.getImage(), 0,0, getWidth(), getHeight(),null);
            }
            else if (mascota instanceof Ave){
                g.drawImage(GAve.getImage(),0,0, getWidth(),getHeight(),null );
                g.drawImage(GJaula.getImage(), 0,0, getWidth(), getHeight(),null);
            } else if (mascota instanceof Pez) {
                g.drawImage(GPecera.getImage(),0,0, getWidth(),getHeight(),null );
                g.drawImage(GPez.getImage(), 0,0, getWidth(), getHeight(),null);

            }

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

    public Habitat getHabitat() {
        return habitat;
    }
}