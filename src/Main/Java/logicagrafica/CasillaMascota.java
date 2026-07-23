package logicagrafica;

import logicagrafica.menu.MenuManager;
import logicagrafica.render.CasillaRenderer;
import logicagrafica.handler.CasillaMouseHandler;
import logicatienda.animales.Animal;
import logicatienda.observers.AnimalObserver;
import logicatienda.animales.Estadistica;
import logicatienda.comprador.Comprador;
import logicatienda.habitat.*;
import logicatienda.tienda.Tienda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Representa una casilla individual en el mostrador de la tienda.
 * Actúa como contenedor y coordinador de los diferentes componentes.
 * Implementa AnimalObserver para recibir notificaciones de cambios en el animal.
 */
public class CasillaMascota extends PanelTMAnimal implements AnimalObserver {

    private Habitat habitat;
    private Tienda tiendaLogica;
    private Runnable actualizarP;

    private MenuManager menuManager;
    private CasillaRenderer renderer;
    private CasillaMouseHandler mouseHandler;

    private ImageIcon alertaHambre, alertaSucio, alertaTriste, alertaEnfermo;

    private final int PRECIO_JAULA = 200;
    private final int PRECIO_CAMA = 180;
    private final int PRECIO_PECERA = 190;

    private final String[] listaDeNombres = {
            "Goku","Ezio", "Patata","Zeus", "Pepe", "Grace","Tolosin","Melasa","Robbie", "Roar",
            "Shrek", "Mahoraga" , "Rafael", "Miguel Angelo", "Donnatelo", "Leonardo", "Platon",
            "Socrates", "Mario", "Silvio", "Haaland", "Talon", "Zilean", "Jarvan", "Geoffrey",
            "Nilah", "Messi", "Vegetta", "Tito Soto", "Alexis", "Gustavo", "Pedro", "Rene",
            "Miku", "Majin Boo", "Fernanfloo", "Felipe", "Nestle","Corxea", "Bond", "Braviary",
            "John Doe", "Jane Doe", "Cupcake","Petrus", "Honda", "Daniel", "El Tata",
            "Frederickson", "Teao", "Chipp", "Agnes"
    };

    /**
     * Constructor de la casilla.
     * @param x Posición X
     * @param y Posición Y
     * @param ancho Ancho de la casilla
     * @param alto Alto de la casilla
     * @param habitat Hábitat inicial (puede ser null)
     * @param tienda Referencia a la tienda lógica
     * @param actualizarP Runnable para actualizar la interfaz
     */
    public CasillaMascota(int x, int y, int ancho, int alto, Habitat habitat, Tienda tienda, Runnable actualizarP) {
        super(x, y, ancho, alto, "");
        this.habitat = habitat;
        this.tiendaLogica = tienda;
        this.actualizarP = actualizarP;
        cargarImagenes();

        if (this.habitat != null && !this.habitat.estaVacio()) {
            this.habitat.getResidente().addObserver(this);
        }

        this.setLayout(new BorderLayout());

        this.menuManager = new MenuManager(this, tienda, actualizarP);
        this.renderer = new CasillaRenderer(this);
        this.mouseHandler = new CasillaMouseHandler(this, menuManager);

        this.add(menuManager.getPanelMenu(), BorderLayout.CENTER);
        this.addMouseListener(mouseHandler);
    }

    /**
     * Carga todas las imágenes de alerta.
     */
    private void cargarImagenes() {
        alertaEnfermo = cargarImagen("alertaSal.png", 15, 15);
        alertaHambre = cargarImagen("alertaHam.png", 15, 15);
        alertaSucio = cargarImagen("alertaHig.png", 15, 15);
        alertaTriste = cargarImagen("alertaFel.png", 15, 15);
    }

    /**
     * Carga y redimensiona una imagen desde la carpeta Sprites.
     * @param nombreArchivo Nombre del archivo de imagen
     * @param ancho Ancho deseado en píxeles
     * @param alto Alto deseado en píxeles
     * @return ImageIcon redimensionado, o un icono vacío si falla
     */
    private ImageIcon cargarImagen(String nombreArchivo, int ancho, int alto) {
        java.net.URL urlImagen = getClass().getResource("Sprites/" + nombreArchivo);
        if (urlImagen != null) {
            ImageIcon spriteOriginal = new ImageIcon(urlImagen);
            Image spriteEscalado = spriteOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
            return new ImageIcon(spriteEscalado);
        } else {
            System.out.println("NO SE ENCONTRO EL SPRITE: " + nombreArchivo);
            return new ImageIcon();
        }
    }

    /**
     * Genera un nombre aleatorio para un animal.
     * @return Nombre aleatorio de la lista predefinida
     */
    public String generarNombreAleatorio() {
        int aleatorio = (int)(Math.random() * listaDeNombres.length);
        return listaDeNombres[aleatorio];
    }

    /**
     * Muestra el menú de compra de hábitats.
     */
    public void mostrarMenuHabitat() {
        menuManager.mostrarMenuHabitat();
    }

    /**
     * Muestra el menú de interacción con el animal.
     */
    public void mostrarMenuAnimal() {
        menuManager.mostrarMenuAnimal();
    }

    /**
     * Muestra el menú para añadir animales al hábitat.
     */
    public void mostrarMenuAñadirAnimal() {
        menuManager.mostrarMenuAñadirAnimal();
    }

    /**
     * Muestra el menú de venta de animales.
     */
    public void mostrarMenuVender() {
        menuManager.mostrarMenuVender();
    }

    /**
     * Oculta todos los botones y menús.
     */
    public void ocultarTodosLosBotones() {
        menuManager.ocultarTodosLosBotones();
    }

    /**
     * Intenta comprar un hábitat y colocarlo en la casilla.
     * @param nuevoHabitat El hábitat a comprar
     * @param costo Costo del hábitat
     */
    public void intentarComprarHabitat(Habitat nuevoHabitat, int costo) {
        boolean compraExitosa = tiendaLogica.comprarHabitat(nuevoHabitat, costo);
        if (compraExitosa) {
            this.habitat = nuevoHabitat;
            System.out.println("Hábitat instalado en esta casilla.");
            if (this.actualizarP != null) this.actualizarP.run();
        } else {
            System.out.println("No se pudo comprar el hábitat (falta dinero).");
        }
        ocultarTodosLosBotones();
    }

    /**
     * Intenta comprar un animal y alojarlo en el hábitat.
     * @param nuevoAnimal El animal a comprar
     * @param costo Costo del animal
     */
    public void intentarComprarAnimal(Animal nuevoAnimal, int costo) {
        if (this.habitat == null) {
            System.out.println("Error: No hay hábitat en esta casilla.");
            ocultarTodosLosBotones();
            return;
        }
        if (!this.habitat.estaVacio()) {
            System.out.println("Error: El hábitat ya está ocupado.");
            ocultarTodosLosBotones();
            return;
        }
        if (!this.habitat.esCompatible(nuevoAnimal)) {
            System.out.println("Error: Este hábitat no es compatible con este tipo de animal.");
            ocultarTodosLosBotones();
            return;
        }

        boolean compraExitosa = tiendaLogica.comprarAnimal(nuevoAnimal, this.habitat, costo);
        if (compraExitosa) {
            nuevoAnimal.addObserver(this);
            System.out.println("Animal comprado y alojado con éxito");
            if (this.actualizarP != null) actualizarP.run();
        }
        ocultarTodosLosBotones();
    }

    /**
     * Desmantela el hábitat actual y reembolsa su valor al jugador.
     * Solo se puede usar si el hábitat está vacío.
     */
    public void desmantelarHabitat() {
        if (this.habitat == null || !this.habitat.estaVacio()) return;

        int valorReembolso = 0;
        if (habitat instanceof Jaula) valorReembolso = PRECIO_JAULA;
        else if (habitat instanceof Pecera) valorReembolso = PRECIO_PECERA;
        else if (habitat instanceof Cama) valorReembolso = PRECIO_CAMA;

        tiendaLogica.reembolso(valorReembolso);
        tiendaLogica.getEspaciosActivos().remove(habitat);
        this.habitat = null;
        if (actualizarP != null) actualizarP.run();
        System.out.println("Hábitat desmantelado. Reembolso: $" + valorReembolso);
        ocultarTodosLosBotones();
    }

    /**
     * Vende el animal al comprador actual.
     */
    public void venderAnimal() {
        if (!tieneAnimal()) return;

        Comprador cliente = tiendaLogica.getCompradoractual();
        if (cliente != null) {
            boolean exito = tiendaLogica.venderMascotaACliente(this.habitat, cliente);
            if (exito) {
                tiendaLogica.setCompradoractual(null);
                if (actualizarP != null) actualizarP.run();
            } else {
                System.out.println("No es la especie que busca, o está en muy mal estado.");
            }
        } else {
            System.out.println("No hay ningún cliente esperando en el mostrador.");
        }
        ocultarTodosLosBotones();
    }

    /**
     * Ejecuta la acción correspondiente al clic del mouse en la casilla.
     * @param e Evento del mouse
     */
    @Override
    public void ejecutarAccion(MouseEvent e) {
        mouseHandler.ejecutarAccion(e);
    }

    /**
     * Obtiene el texto del tooltip que muestra la información del animal.
     * @param event Evento del mouse
     * @return Texto HTML con la información del animal
     */
    @Override
    public String getToolTipText(MouseEvent event) {
        if (noTieneHabitat()) return "Vacio";
        if (habitat.estaVacio()) return "Animal Pendiente";
        Animal mascota = habitat.getResidente();
        return "<html><b>" + mascota.getNombre() + "</b><hr>" +
                "Felicidad: " + mascota.getNivel(Estadistica.FELICIDAD) + "/100<br>" +
                "Saciedad: " + mascota.getNivel(Estadistica.SACIEDAD) + "/100<br>" +
                "Higiene: " + mascota.getNivel(Estadistica.HIGIENE) + "/100<br>" +
                "Salud: " + mascota.getNivel(Estadistica.SALUD) + "/100</html>";
    }

    /**
     * Notifica que las estadísticas del animal han cambiado.
     * Actualiza la interfaz gráfica.
     * @param animal El animal que cambió
     */
    @Override
    public void onEstadisticasCambiadas(Animal animal) {
        this.repaint();
    }

    /**
     * Elimina el observer del animal residente.
     */
    public void removerObserver() {
        if (tieneAnimal()) {
            habitat.getResidente().removeObserver(this);
        }
    }

    /**
     * Obtiene el hábitat de la casilla.
     * @return El hábitat actual
     */
    public Habitat getHabitat() {
        return habitat;
    }

    /**
     * Establece el hábitat de la casilla.
     * @param habitat El nuevo hábitat
     */
    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
        if (this.habitat != null && !this.habitat.estaVacio()) {
            this.habitat.getResidente().addObserver(this);
        }
    }

    /**
     * Verifica si la casilla no tiene hábitat.
     * @return true si no tiene hábitat, false en caso contrario
     */
    public boolean noTieneHabitat() {
        return this.habitat == null;
    }

    /**
     * Verifica si la casilla tiene un animal.
     * @return true si tiene hábitat y no está vacío, false en caso contrario
     */
    public boolean tieneAnimal() {
        return this.habitat != null && !this.habitat.estaVacio();
    }

    /**
     * Obtiene la tienda lógica.
     * @return La tienda
     */
    public Tienda getTiendaLogica() {
        return tiendaLogica;
    }

    /**
     * Obtiene el Runnable de actualización.
     * @return El Runnable
     */
    public Runnable getActualizarP() {
        return actualizarP;
    }

    /**
     * Obtiene el icono de alerta de hambre.
     * @return ImageIcon de alerta de hambre
     */
    public ImageIcon getAlertaHambre() {
        return alertaHambre;
    }

    /**
     * Obtiene el icono de alerta de suciedad.
     * @return ImageIcon de alerta de suciedad
     */
    public ImageIcon getAlertaSucio() {
        return alertaSucio;
    }

    /**
     * Obtiene el icono de alerta de tristeza.
     * @return ImageIcon de alerta de tristeza
     */
    public ImageIcon getAlertaTriste() {
        return alertaTriste;
    }

    /**
     * Obtiene el icono de alerta de enfermedad.
     * @return ImageIcon de alerta de enfermedad
     */
    public ImageIcon getAlertaEnfermo() {
        return alertaEnfermo;
    }

    /**
     * Obtiene el precio de la jaula.
     * @return Precio de la jaula
     */
    public int getPrecioJaula() {
        return PRECIO_JAULA;
    }

    /**
     * Obtiene el precio de la cama.
     * @return Precio de la cama
     */
    public int getPrecioCama() {
        return PRECIO_CAMA;
    }

    /**
     * Obtiene el precio de la pecera.
     * @return Precio de la pecera
     */
    public int getPrecioPecera() {
        return PRECIO_PECERA;
    }
}