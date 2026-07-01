package logicagrafica;
import logicatienda.animales.*;

import java.awt.event.MouseEvent;

public class CasillaMascota extends PanelTMAnimal{
    private Animal mascota;
    public CasillaMascota(int x, int y, int ancho, int alto, Animal mascota){
        super(x,y,ancho,alto, "");
        this.mascota=mascota;
    }
    @Override
    public String getToolTipText(MouseEvent event){
        if (mascota== null) return "Vacio";
        return "<html>" +
                "<b>" + mascota.getNombre() + "</b><br>" +
                "<hr>" + // Una línea divisoria
                "Felicidad: " + mascota.getNivel(Animal.Estadistica.FELICIDAD) + "/100<br>" +
                "Saciedad: " + mascota.getNivel(Animal.Estadistica.SACIEDAD) + "/100<br>" +
                "Higiene: " + mascota.getNivel(Animal.Estadistica.HIGIENE) + "/100<br>" +
                "Salud: " + mascota.getNivel(Animal.Estadistica.SALUD) + "/100" +
                "</html>";

    }

}
