package logicagrafica;
import logicatienda.animales.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelTMAnimal extends JPanel {
    private int CAncho = getWidth() /8; //Ancho de Casilla
    private int CAlto = getHeight()/5; //Alto de Casilla

    public PanelTMAnimal(int x, int y, int CAncho, int CAlto, String mensajeTooltip){
        this.CAlto=CAlto;
        this.CAncho=CAncho;
        this.setBounds(x,y,CAncho,CAlto);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        this.setBackground(Color.lightGray);
        this.setToolTipText(mensajeTooltip);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                ejecutarAccion();
            }
        });
    }
    public void ejecutarAccion(){

    }
}
