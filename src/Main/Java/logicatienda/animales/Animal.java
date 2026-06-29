package logicatienda.animales;

import java.util.Map;
import java.util.HashMap;

public abstract class Animal {
    public static final int FELICIDAD_MIN=60;
    public static final int SACIEDAD_MIN=60;
    public static final int HIGIENE_MIN=55;
    public static final int SALUD_MIN=65;
    public static final int MAX=100;
    public static final int MIN=0;
    public static final int VAL_INICIO=85;

    protected final String nombre;
    protected Map<Estadistica, Integer> estadisticas;
    public enum Estadistica {
        FELICIDAD(0),
        SACIEDAD(1),
        HIGIENE(2),
        SALUD(3);
        private final int indice;

        private Estadistica(int indice){
            this.indice=indice;
        }

        public int getIndex() {
            return indice;
        }

    }

    public Animal(String nombre){
        this.nombre=nombre;
        this.estadisticas = new HashMap<>();

        this.estadisticas.put(Estadistica.FELICIDAD,VAL_INICIO);
        this.estadisticas.put(Estadistica.SACIEDAD, VAL_INICIO);
        this.estadisticas.put(Estadistica.HIGIENE, VAL_INICIO);
        this.estadisticas.put(Estadistica.SALUD,VAL_INICIO );
    }

    public String getNombre(){
        return this.nombre;
    }

    private void setNivel(Estadistica estadistica, int nivel){
        int nivelValidado = Math.max(MIN, Math.min(MAX, nivel));
        this.estadisticas.put(estadistica, nivelValidado);
    }

    private int getNivel(Estadistica estadistica){
        return this.estadisticas.getOrDefault(estadistica, VAL_INICIO);
    }

    private void aumentarNivel(Estadistica estadistica, int cantidad){
        this.setNivel(estadistica, this.getNivel(estadistica) + cantidad);
    }

    private void disminuirNivel(Estadistica estadistica, int cantidad) {
        this.setNivel(estadistica, this.getNivel(estadistica) - cantidad);
    }



    public void Alimentar(int cantidad){
        this.aumentarNivel(Estadistica.SACIEDAD,cantidad);
        this.aumentarNivel(Estadistica.FELICIDAD,10);
    }

    public void Jugar(){
        this.aumentarNivel(Estadistica.FELICIDAD,40);
        this.disminuirNivel(Estadistica.SACIEDAD,10);
        this.disminuirNivel(Estadistica.HIGIENE,15);
    }

    public void Limpiar(){
        this.aumentarNivel(Estadistica.HIGIENE,40);
        this.aumentarNivel(Estadistica.SALUD,10);
    }

    public void Curar(int cantidad){
        this.aumentarNivel(Estadistica.SALUD,cantidad);
        this.disminuirNivel(Estadistica.FELICIDAD,5);
    }


    private boolean getEstadoStat(Estadistica estadistica) {
        int valor = this.getNivel(estadistica);
        switch(estadistica) {
            case FELICIDAD: return valor >= FELICIDAD_MIN;  // 60
            case SACIEDAD:  return valor >= SACIEDAD_MIN;   // 60
            case HIGIENE:   return valor >= HIGIENE_MIN;    // 55
            case SALUD:     return valor >= SALUD_MIN;      // 65
            default: return false;
        }
    }
    //puede servir más adelante
    /*public boolean[] getTodosLosEstados() {
        return new boolean[]{
                getEstadoStat(Estadistica.FELICIDAD),
                getEstadoStat(Estadistica.SACIEDAD),
                getEstadoStat(Estadistica.HIGIENE),
                getEstadoStat(Estadistica.SALUD)
        };
    }*/
}