package logicagrafica.menu;

import logicatienda.animales.*;
import logicatienda.habitat.*;
import logicatienda.tienda.Tienda;
import logicatienda.usuario.Item;
import logicagrafica.CasillaMascota;

import javax.swing.*;
import java.awt.*;

/**
 * Gestiona todos los menús de la casilla.
 * Centraliza la creación y visualización de menús de hábitats, animales y ventas.
 */
public class MenuManager {

    private CasillaMascota casilla;
    private Tienda tienda;
    private Runnable actualizarP;
    private JPanel panelMenu;

    private JButton btnMedicina, btnAlimentar, btnLimpiar, btnJugar;
    private JButton btnBuyJaula, btnBuyCama, btnBuyPecera;
    private JButton btnAñadirPerro, btnAñadirGato, btnAñadirAve, btnAñadirPez;
    private JButton btnVender;

    private boolean mostrandoMenuHabitat = false;
    private boolean mostrandoMenuAnimal = false;
    private boolean mostrandoMenuAñadir = false;
    private boolean mostrandoMenuVender = false;

    /**
     * Constructor del gestor de menús.
     *
     * @param casilla La casilla a la que pertenece el menú
     * @param tienda La tienda lógica
     * @param actualizarP Runnable para actualizar la interfaz
     */
    public MenuManager(CasillaMascota casilla, Tienda tienda, Runnable actualizarP) {
        this.casilla = casilla;
        this.tienda = tienda;
        this.actualizarP = actualizarP;
        this.panelMenu = new JPanel();
        this.panelMenu.setOpaque(false);
        inicializarBotones();
    }

    /**
     * Inicializa todos los botones y sus listeners.
     */
    private void inicializarBotones() {
        btnBuyJaula = new JButton("Jaula ($200)", cargarImagen("Jaula.png", 40, 40));
        btnBuyPecera = new JButton("Pecera ($190)", cargarImagen("pescera.png", 30, 30));
        btnBuyCama = new JButton("Cama ($180)", cargarImagen("cama.png", 40, 40));

        btnBuyJaula.addActionListener(e -> casilla.intentarComprarHabitat(new Jaula(), casilla.getPrecioJaula()));
        btnBuyPecera.addActionListener(e -> casilla.intentarComprarHabitat(new Pecera(), casilla.getPrecioPecera()));
        btnBuyCama.addActionListener(e -> casilla.intentarComprarHabitat(new Cama(), casilla.getPrecioCama()));

        btnAñadirPerro = new JButton("Perro: " + tienda.getUsuario().getCantItem(Item.PERRO.getIndex()), cargarImagen("perro.png", 40, 40));
        btnAñadirGato = new JButton("Gato: " + tienda.getUsuario().getCantItem(Item.GATO.getIndex()), cargarImagen("gato.png", 40, 40));
        btnAñadirAve = new JButton("Ave: " + tienda.getUsuario().getCantItem(Item.AVE.getIndex()), cargarImagen("ave.png", 40, 40));
        btnAñadirPez = new JButton("Pez: " + tienda.getUsuario().getCantItem(Item.PEZ.getIndex()), cargarImagen("Pezp.png", 40, 40));

        btnAñadirPerro.addActionListener(e -> {
            if (tienda.getUsuario().getCantItem(Item.PERRO.getIndex()) > 0) {
                tienda.getUsuario().restarItem(Item.PERRO.getIndex());
                casilla.intentarComprarAnimal(new Perro(casilla.generarNombreAleatorio()), 0);
                if (actualizarP != null) actualizarP.run();
            }
        });

        btnAñadirGato.addActionListener(e -> {
            if (tienda.getUsuario().getCantItem(Item.GATO.getIndex()) > 0) {
                tienda.getUsuario().restarItem(Item.GATO.getIndex());
                casilla.intentarComprarAnimal(new Gato(casilla.generarNombreAleatorio()), 0);
                if (actualizarP != null) actualizarP.run();
            }
        });

        btnAñadirAve.addActionListener(e -> {
            if (tienda.getUsuario().getCantItem(Item.AVE.getIndex()) > 0) {
                tienda.getUsuario().restarItem(Item.AVE.getIndex());
                casilla.intentarComprarAnimal(new Ave(casilla.generarNombreAleatorio()), 0);
                if (actualizarP != null) actualizarP.run();
            }
        });

        btnAñadirPez.addActionListener(e -> {
            if (tienda.getUsuario().getCantItem(Item.PEZ.getIndex()) > 0) {
                tienda.getUsuario().restarItem(Item.PEZ.getIndex());
                casilla.intentarComprarAnimal(new Pez(casilla.generarNombreAleatorio()), 0);
                if (actualizarP != null) actualizarP.run();
            }
        });

        btnMedicina = new JButton("Dar Medicina: " + tienda.getUsuario().getCantItem(Item.MEDICINA.getIndex()), cargarImagen("darMed.png", 30, 30));
        btnAlimentar = new JButton("Alimentar: " + tienda.getUsuario().getCantItem(Item.COMIDA.getIndex()), cargarImagen("darComida.png", 30, 30));
        btnLimpiar = new JButton("Limpiar Habitat", cargarImagen("limpiar.png", 30, 30));
        btnJugar = new JButton("Jugar", cargarImagen("jugar.png", 30, 30));

        btnMedicina.addActionListener(e -> {
            if (casilla.tieneAnimal()) {
                int medicina = Item.MEDICINA.getIndex();
                if (tienda.getUsuario().getCantItem(medicina) > 0) {
                    casilla.getHabitat().getResidente().curar(40);
                    tienda.getUsuario().restarItem(medicina);
                    if (actualizarP != null) actualizarP.run();
                }
            }
            ocultarTodosLosBotones();
        });

        btnAlimentar.addActionListener(e -> {
            if (casilla.tieneAnimal()) {
                int comida = Item.COMIDA.getIndex();
                if (tienda.getUsuario().getCantItem(comida) > 0) {
                    casilla.getHabitat().getResidente().alimentar();
                    tienda.getUsuario().restarItem(comida);
                    if (actualizarP != null) actualizarP.run();
                }
            }
            ocultarTodosLosBotones();
        });

        btnLimpiar.addActionListener(e -> {
            if (casilla.tieneAnimal()) {
                casilla.getHabitat().getResidente().limpiar();
                if (actualizarP != null) actualizarP.run();
            }
            ocultarTodosLosBotones();
        });

        btnJugar.addActionListener(e -> {
            if (casilla.tieneAnimal()) {
                casilla.getHabitat().getResidente().jugar();
                if (actualizarP != null) actualizarP.run();
            }
            ocultarTodosLosBotones();
        });

        btnVender = new JButton("Vender Animal");
        btnVender.addActionListener(e -> {
            casilla.venderAnimal();
            ocultarTodosLosBotones();
        });

        java.awt.event.MouseAdapter detectorClicDerecho = new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    ocultarTodosLosBotones();
                }
            }
        };

        for (JButton btn : new JButton[]{btnBuyJaula, btnBuyPecera, btnBuyCama,
                btnAñadirPerro, btnAñadirGato, btnAñadirAve, btnAñadirPez,
                btnMedicina, btnAlimentar, btnLimpiar, btnJugar}) {
            btn.addMouseListener(detectorClicDerecho);
        }
    }

    /**
     * Obtiene el panel del menú.
     *
     * @return El panel del menú
     */
    public JPanel getPanelMenu() {
        return panelMenu;
    }

    /**
     * Muestra el menú de compra de hábitats.
     */
    public void mostrarMenuHabitat() {
        mostrandoMenuHabitat = true;
        mostrandoMenuAnimal = false;
        mostrandoMenuAñadir = false;
        mostrandoMenuVender = false;

        panelMenu.removeAll();
        panelMenu.setLayout(new GridLayout(3, 1, 0, 1));
        panelMenu.add(btnBuyJaula);
        panelMenu.add(btnBuyPecera);
        panelMenu.add(btnBuyCama);
        actualizarPanel();
    }

    /**
     * Muestra el menú de interacción con el animal.
     */
    public void mostrarMenuAnimal() {
        mostrandoMenuHabitat = false;
        mostrandoMenuAnimal = true;
        mostrandoMenuAñadir = false;
        mostrandoMenuVender = false;

        actualizarTextosBotones();
        panelMenu.removeAll();
        panelMenu.setLayout(new GridLayout(4, 1, 0, 1));
        panelMenu.add(btnMedicina);
        panelMenu.add(btnAlimentar);
        panelMenu.add(btnLimpiar);
        panelMenu.add(btnJugar);
        actualizarPanel();
    }

    /**
     * Muestra el menú para añadir animales al hábitat.
     */
    public void mostrarMenuAñadirAnimal() {
        if (!casilla.tieneAnimal()) {
            Habitat habitat = casilla.getHabitat();
            if (habitat != null && habitat.estaVacio()) {
                mostrandoMenuHabitat = false;
                mostrandoMenuAnimal = false;
                mostrandoMenuAñadir = true;
                mostrandoMenuVender = false;

                actualizarTextosBotones();
                panelMenu.removeAll();
                panelMenu.setLayout(new GridLayout(0, 1, 0, 1));

                if (habitat.esCompatible(new Perro(""))) {
                    panelMenu.add(btnAñadirPerro);
                }
                if (habitat.esCompatible(new Gato(""))) {
                    panelMenu.add(btnAñadirGato);
                }
                if (habitat.esCompatible(new Ave(""))) {
                    panelMenu.add(btnAñadirAve);
                }
                if (habitat.esCompatible(new Pez(""))) {
                    panelMenu.add(btnAñadirPez);
                }
                actualizarPanel();
            }
        }
    }

    /**
     * Muestra el menú de venta de animales.
     */
    public void mostrarMenuVender() {
        if (casilla.tieneAnimal()) {
            ocultarTodosLosBotones();
            mostrandoMenuVender = true;
            panelMenu.removeAll();
            panelMenu.setLayout(new GridLayout(1, 1));
            panelMenu.add(btnVender);
            actualizarPanel();
        }
    }

    /**
     * Oculta todos los botones y menús.
     */
    public void ocultarTodosLosBotones() {
        mostrandoMenuHabitat = false;
        mostrandoMenuAnimal = false;
        mostrandoMenuAñadir = false;
        mostrandoMenuVender = false;
        panelMenu.removeAll();
        panelMenu.revalidate();
        panelMenu.repaint();
    }

    /**
     * Verifica si algún menú está visible.
     *
     * @return true si hay algún menú visible, false en caso contrario
     */
    public boolean isMenuVisible() {
        return mostrandoMenuHabitat || mostrandoMenuAnimal || mostrandoMenuAñadir || mostrandoMenuVender;
    }

    /**
     * Actualiza los textos de los botones con las cantidades actuales.
     */
    private void actualizarTextosBotones() {
        btnAñadirPerro.setText("Perro: " + tienda.getUsuario().getCantItem(Item.PERRO.getIndex()));
        btnAñadirGato.setText("Gato: " + tienda.getUsuario().getCantItem(Item.GATO.getIndex()));
        btnAñadirAve.setText("Ave: " + tienda.getUsuario().getCantItem(Item.AVE.getIndex()));
        btnAñadirPez.setText("Pez: " + tienda.getUsuario().getCantItem(Item.PEZ.getIndex()));
        btnMedicina.setText("Dar Medicina: " + tienda.getUsuario().getCantItem(Item.MEDICINA.getIndex()));
        btnAlimentar.setText("Alimentar: " + tienda.getUsuario().getCantItem(Item.COMIDA.getIndex()));
    }

    /**
     * Actualiza el panel del menú.
     */
    private void actualizarPanel() {
        panelMenu.revalidate();
        panelMenu.repaint();
    }

    /**
     * Carga y redimensiona una imagen desde la carpeta Sprites.
     *
     * @param nombreArchivo Nombre del archivo de imagen
     * @param ancho Ancho deseado en píxeles
     * @param alto Alto deseado en píxeles
     * @return ImageIcon redimensionado, o un icono vacío si falla
     */
    private ImageIcon cargarImagen(String nombreArchivo, int ancho, int alto) {
        java.net.URL urlImagen = getClass().getResource("/logicagrafica/Sprites/" + nombreArchivo);
        if (urlImagen != null) {
            ImageIcon spriteOriginal = new ImageIcon(urlImagen);
            Image spriteEscalado = spriteOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
            return new ImageIcon(spriteEscalado);
        } else {
            System.out.println("NO SE ENCONTRO EL SPRITE: " + nombreArchivo);
            return new ImageIcon();
        }
    }
}