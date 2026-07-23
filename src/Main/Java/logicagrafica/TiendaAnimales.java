package logicagrafica;

import logicatienda.tienda.Tienda;
import logicatienda.usuario.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Representa un cuadro de diálogo modal para la compra de animales.
 * Permite al usuario seleccionar entre Perro, Gato, Ave o Pez,
 * validando que tenga suficiente dinero y actualizando el inventario.
 */
public class TiendaAnimales extends JDialog {

    /**
     * Constructor de la ventana de compra de animales.
     * Crea un diálogo modal con botones para cada tipo de animal.
     * @param ventanaPadre El JFrame principal sobre el cual aparecerá el diálogo
     * @param tienda La instancia de la tienda que contiene la lógica de negocio
     * @param actualizarP Runnable para actualizar la interfaz después de la compra
     */
    public TiendaAnimales(JFrame ventanaPadre, Tienda tienda, Runnable actualizarP) {
        super(ventanaPadre, "Comprar Animales", true);
        this.setSize(400, 250);
        this.setLocationRelativeTo(ventanaPadre);
        this.setLayout(new GridLayout(5, 1, 1, 1));

        final int PRECIO_PERRO = 400;
        final int PRECIO_GATO = 400;
        final int PRECIO_AVE = 700;
        final int PRECIO_PEZ = 500;

        JButton btnPerro = new JButton("Comprar Perro: $" + PRECIO_PERRO, cargarImagen("perro.png", 50, 50));
        JButton btnGato = new JButton("Comprar Gato: $" + PRECIO_GATO, cargarImagen("gato.png", 50, 50));
        JButton btnAve = new JButton("Comprar Ave: $" + PRECIO_AVE, cargarImagen("pajaro.png", 50, 50));
        JButton btnPez = new JButton("Comprar Pez: $" + PRECIO_PEZ, cargarImagen("Pezp.png", 50, 50));
        JButton btnCancelar = new JButton("Cancelar");

        configurarBoton(btnPerro, PRECIO_PERRO, Item.PERRO, tienda, actualizarP);
        configurarBoton(btnGato, PRECIO_GATO, Item.GATO, tienda, actualizarP);
        configurarBoton(btnAve, PRECIO_AVE, Item.AVE, tienda, actualizarP);
        configurarBoton(btnPez, PRECIO_PEZ, Item.PEZ, tienda, actualizarP);

        btnCancelar.addActionListener(e -> this.dispose());

        this.add(btnPerro);
        this.add(btnGato);
        this.add(btnAve);
        this.add(btnPez);
        this.add(btnCancelar);
    }

    /**
     * Configura un botón de compra para un animal específico.
     * Verifica si el usuario tiene suficiente dinero, descuenta el costo
     * y añade el animal al inventario.
     * @param boton El botón a configurar
     * @param costo El precio del animal
     * @param animal El tipo de animal (Item)
     * @param tienda La instancia de la tienda
     * @param actualizarP Runnable para actualizar la interfaz
     */
    private void configurarBoton(JButton boton, int costo, Item animal, Tienda tienda, Runnable actualizarP) {
        boton.addActionListener(e -> {
            int presupuesto = tienda.getUsuario().getDinero();
            if (presupuesto >= costo) {
                tienda.getUsuario().quitarDinero(costo);
                tienda.getUsuario().sumarItem(animal.getIndex());
                System.out.println("Has comprado un " + animal + ".");
                if (actualizarP != null) actualizarP.run();
                this.dispose();
            } else {
                System.out.println("Te falta dinero para comprar " + animal + ".");
            }
        });
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
}