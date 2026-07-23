package logicagrafica;

import logicatienda.animales.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel base abstracto para las casillas de animales.
 * Proporciona la estructura básica y el manejo de eventos de mouse.
 * Las clases hijas deben implementar la acción a ejecutar al hacer clic.
 */
public abstract class PanelTMAnimal extends JPanel {
    private int CAncho ; //Ancho de Casilla
    private int CAlto; //Alto de Casilla

    /**
     * Constructor del panel base.
     * @param x Posición X
     * @param y Posición Y
     * @param CAncho Ancho de la casilla
     * @param CAlto Alto de la casilla
     * @param mensajeTooltip Texto del tooltip
     */
    public PanelTMAnimal(int x, int y, int CAncho, int CAlto, String mensajeTooltip){
        this.CAlto=CAlto;
        this.CAncho=CAncho;
        this.setBounds(x,y,CAncho,CAlto);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        this.setBackground(Color.lightGray);
        this.setToolTipText(mensajeTooltip);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){

                ejecutarAccion(e);
            }
        });
    }

    /**
     * Obtiene el alto de la casilla.
     * @return Alto en píxeles
     */
    public int getCAlto() {
        return CAlto;
    }

    /**
     * Obtiene el ancho de la casilla.
     * @return Ancho en píxeles
     */
    public int getCAncho() {
        return CAncho;
    }

    /**
     * Ejecuta la acción correspondiente al evento del mouse.
     * Las clases hijas deben implementar este metodo.
     * @param e Evento del mouse
     */
    public abstract void ejecutarAccion(MouseEvent e);


}
