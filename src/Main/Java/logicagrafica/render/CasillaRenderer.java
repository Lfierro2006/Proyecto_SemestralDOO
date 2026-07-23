package logicagrafica.render;

import logicatienda.animales.*;
import logicatienda.habitat.*;
import logicagrafica.CasillaMascota;

import javax.swing.*;
import java.awt.*;

/**
 * Responsable del renderizado y pintado de la casilla.
 * Separa la lógica de dibujo del resto de la casilla.
 */
public class CasillaRenderer {

    private CasillaMascota casilla;

    private ImageIcon GCama, GJaula, GPecera, GPerro, GGato, GAve, GPez;

    /**
     * Constructor del renderizador.
     *
     * @param casilla La casilla a renderizar
     */
    public CasillaRenderer(CasillaMascota casilla) {
        this.casilla = casilla;
        cargarSprites();
    }

    /**
     * Carga todos los sprites necesarios para el renderizado.
     */
    private void cargarSprites() {
        GCama = cargarImagen("cama.png", 160, 160);
        GPecera = cargarImagen("pescera.png", 160, 160);
        GJaula = cargarImagen("Jaula.png", 160, 160);
        GGato = cargarImagen("gato.png", 100, 100);
        GPerro = cargarImagen("perro.png", 100, 100);
        GAve = cargarImagen("ave.png", 100, 100);
        GPez = cargarImagen("Pezp.png", 100, 100);
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

    /**
     * Pinta el contenido de la casilla.
     *
     * @param g Objeto Graphics para dibujar
     */
    public void paintComponent(Graphics g) {
        if (casilla.noTieneHabitat()) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("[VACIO]", 10, 20);
            return;
        }

        Habitat habitat = casilla.getHabitat();

        if (habitat.estaVacio()) {
            dibujarHabitatVacio(g);
            return;
        }

        if (!casilla.tieneAnimal()) return;

        Animal mascota = habitat.getResidente();
        dibujarAnimalConHabitat(g, mascota);
        dibujarAlertas(g, mascota);
    }

    /**
     * Dibuja un hábitat vacío.
     *
     * @param g Objeto Graphics para dibujar
     */
    private void dibujarHabitatVacio(Graphics g) {
        Habitat habitat = casilla.getHabitat();
        ImageIcon sprite = null;
        if (habitat instanceof Jaula) sprite = GJaula;
        else if (habitat instanceof Cama) sprite = GCama;
        else if (habitat instanceof Pecera) sprite = GPecera;

        if (sprite != null) {
            g.drawImage(sprite.getImage(), 0, 0, casilla.getWidth(), casilla.getHeight(), null);
        }
    }

    /**
     * Dibuja el animal con su hábitat correspondiente.
     *
     * @param g Objeto Graphics para dibujar
     * @param mascota El animal a dibujar
     */
    private void dibujarAnimalConHabitat(Graphics g, Animal mascota) {
        ImageIcon animalSprite = null;
        ImageIcon habitatSprite = null;

        if (mascota instanceof Perro) {
            animalSprite = GPerro;
            habitatSprite = GCama;
        } else if (mascota instanceof Gato) {
            animalSprite = GGato;
            habitatSprite = GCama;
        } else if (mascota instanceof Ave) {
            animalSprite = GAve;
            habitatSprite = GJaula;
        } else if (mascota instanceof Pez) {
            animalSprite = GPez;
            habitatSprite = GPecera;
        }

        if (habitatSprite != null) {
            g.drawImage(habitatSprite.getImage(), 0, 0, casilla.getWidth(), casilla.getHeight(), null);
        }
        if (animalSprite != null) {
            g.drawImage(animalSprite.getImage(), 0, 0, casilla.getWidth(), casilla.getHeight(), null);
        }
    }

    /**
     * Dibuja las alertas de estado del animal.
     *
     * @param g Objeto Graphics para dibujar
     * @param mascota El animal del que se dibujan las alertas
     */
    private void dibujarAlertas(Graphics g, Animal mascota) {
        boolean[] estados = mascota.getTodosLosEstados();
        int yIcono = 40;

        if (!estados[3]) {
            g.drawImage(casilla.getAlertaEnfermo().getImage(), 10, yIcono, null);
            yIcono += 20;
        }
        if (!estados[1]) {
            g.drawImage(casilla.getAlertaHambre().getImage(), 10, yIcono, null);
            yIcono += 20;
        }
        if (!estados[2]) {
            g.drawImage(casilla.getAlertaSucio().getImage(), 10, yIcono, null);
            yIcono += 20;
        }
        if (!estados[0]) {
            g.drawImage(casilla.getAlertaTriste().getImage(), 10, yIcono, null);
        }
    }
}