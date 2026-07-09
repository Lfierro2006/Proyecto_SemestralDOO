package logicagrafica;

import logicatienda.tienda.Tienda;
import logicatienda.usuario.Item;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class TiendaAnimales extends JDialog {

    public TiendaAnimales(JFrame ventanaPadre, Tienda tienda, Runnable actualizarP){
        super(ventanaPadre, "Comprar Animales ", true);
        this.setSize(400, 250);
        this.setLocationRelativeTo(ventanaPadre);
        this.setLayout(new GridLayout(5,1,1,1));
        final int $Perro = 400;
        final int $Gato= 400;
        final int $Ave = 700;
        final int $Pez= 500;

        JButton btnPerro = new JButton("Comprar Perro: "+ $Perro, cargarImagen("perro.png",50,50));
        JButton btnGato = new JButton("Comprar Gato: "+ $Gato, cargarImagen("gato.png", 50, 50));
        JButton btnAve = new JButton("Comprar Ave: "+ $Ave, cargarImagen("pajaro.png",50,50)); //Me dieron ganas de dejar un easter egg
        JButton btnPez = new JButton("Comprar Pez: "+ $Pez, cargarImagen("Pezp.png",50,50));
        JButton btnCancelar = new JButton("Cancelar");

        Botones(btnPerro, $Perro, Item.PERRO, tienda, actualizarP);
        Botones(btnGato, $Gato, Item.GATO, tienda, actualizarP);
        Botones(btnAve, $Ave, Item.AVE, tienda, actualizarP);
        Botones(btnPez, $Pez, Item.PEZ, tienda, actualizarP);
        btnCancelar.addActionListener(e -> this.dispose());

        this.add(btnPerro);
        this.add(btnGato);
        this.add(btnAve);
        this.add(btnPez);
        this.add(btnCancelar);

    }
    private void Botones(JButton boton, int costo, Item animal, Tienda tiendal, Runnable actualizarP){
        boton.addActionListener(e -> {
            int presupuesto = tiendal.getUsuario().getDinero();
            if(presupuesto >= costo){
                tiendal.getUsuario().quitarDinero(costo);
                tiendal.getUsuario().sumarItem(animal.getIndex());
                System.out.println("Has comprado un "+ animal+ " , para interactuar colocalo en su habitat correspondiente.");
                if (actualizarP!= null) actualizarP.run();
            }
            else {
                System.out.println("Te falta dinero.");
            }
        });

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
}
