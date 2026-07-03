package logicatienda.animales;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import logicatienda.animales.estados.EstadoAnimal;
import logicatienda.animales.estados.*;

public abstract class Animal {
    public static final int FELICIDAD_MIN=60;
    public static final int SACIEDAD_MIN=60;
    public static final int HIGIENE_MIN=55;
    public static final int SALUD_MIN=65;
    public static final int MAX=100;
    public static final int MIN=0;
    public static final int VAL_INICIO=85;

    private List<EstadoAnimal> estadosActuales;
    private final String nombre;
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
        this.estadosActuales = new ArrayList<>();

        this.estadisticas.put(Estadistica.FELICIDAD,VAL_INICIO);
        this.estadisticas.put(Estadistica.SACIEDAD, VAL_INICIO);
        this.estadisticas.put(Estadistica.HIGIENE, VAL_INICIO);
        this.estadisticas.put(Estadistica.SALUD,VAL_INICIO );

        this.estadosActuales.add(new logicatienda.animales.estados.Feliz());   // índice 0
        this.estadosActuales.add(new logicatienda.animales.estados.Saciado()); // índice 1
        this.estadosActuales.add(new logicatienda.animales.estados.Limpio());  // índice 2
        this.estadosActuales.add(new logicatienda.animales.estados.Sano());    // índice 3
    }



    public String getNombre(){
        return this.nombre;
    }

    private void setNivel(Estadistica estadistica, int nivel){
        int nivelValidado = Math.max(MIN, Math.min(MAX, nivel));
        this.estadisticas.put(estadistica, nivelValidado);
        this.actualizarEstados();
    }

    public int getNivel(Estadistica estadistica){
        return this.estadisticas.getOrDefault(estadistica, VAL_INICIO);
    }

    public void aumentarNivel(Estadistica estadistica, int cantidad){
        this.setNivel(estadistica, this.getNivel(estadistica) + cantidad);
    }

    public void disminuirNivel(Estadistica estadistica, int cantidad) {
        this.setNivel(estadistica, this.getNivel(estadistica) - cantidad);
    }



    public void Alimentar(int cantidad){
        if (!this.tieneEstado(EstadoAnimal.Tipo.HAMBRIENTO)) {
            return;
        }
        int hambre = this.getNivel(Estadistica.SACIEDAD);
        int bonus = (SACIEDAD_MIN-hambre)/3;

        this.aumentarNivel(Estadistica.SACIEDAD, cantidad + bonus);
        this.aumentarNivel(Estadistica.FELICIDAD, 10);
    }

    public void Jugar(){
        if (this.tieneEstado(EstadoAnimal.Tipo.ENFERMO)||this.tieneEstado(EstadoAnimal.Tipo.HAMBRIENTO)){
            return;
        }
        int felicidad = this.getNivel(Estadistica.SACIEDAD);
        int bonus = (FELICIDAD_MIN-felicidad)/3;

        this.aumentarNivel(Estadistica.FELICIDAD,40+bonus);
        this.disminuirNivel(Estadistica.SACIEDAD,10);
        this.disminuirNivel(Estadistica.HIGIENE,15);
    }

    public void Limpiar(){
        if (!this.tieneEstado(EstadoAnimal.Tipo.SUCIO)) {
            return;
        }
        int higiene=this.getNivel(Estadistica.HIGIENE);
        int bonus = (HIGIENE_MIN-higiene)/3;

        this.aumentarNivel(Estadistica.HIGIENE,40+bonus);
        this.aumentarNivel(Estadistica.SALUD,10);
    }

    public void Curar(int cantidad){
        if (!this.tieneEstado(EstadoAnimal.Tipo.ENFERMO)) {
            return;
        }
        int salud=this.getNivel(Estadistica.SALUD);
        int bonus=(salud-SALUD_MIN)/4;

        this.aumentarNivel(Estadistica.SALUD,cantidad);
        this.disminuirNivel(Estadistica.FELICIDAD,5);
    }



    public List<EstadoAnimal> getEstados(){
        return this.estadosActuales;
    }

    public boolean tieneEstado(EstadoAnimal.Tipo tipo){
        for (EstadoAnimal estado : this.estadosActuales){
            if (estado.getTipo() == tipo) {
                return true;
            }
        }
        return false;
    }

    private void actualizarEstados(){
        this.estadosActuales.clear();

        if (this.getNivel(Estadistica.FELICIDAD) >= FELICIDAD_MIN){
            this.estadosActuales.add(new Feliz());
        } else {
            this.estadosActuales.add(new Triste());
        }

        if (this.getNivel(Estadistica.SACIEDAD) >= SACIEDAD_MIN){
            this.estadosActuales.add(new Saciado());
        } else {
            this.estadosActuales.add(new Hambriento());
        }

        if (this.getNivel(Estadistica.HIGIENE) >= HIGIENE_MIN){
            this.estadosActuales.add(new Limpio());
        } else {
            this.estadosActuales.add(new Sucio());
        }

        if (this.getNivel(Estadistica.SALUD) >= SALUD_MIN){
            this.estadosActuales.add(new Sano());
        } else {
            this.estadosActuales.add(new Enfermo());
        }
    }

    public void ejecutarEstado(){
        for (EstadoAnimal estado : this.estadosActuales){
            estado.ejecutar(this);
        }
    }

    public boolean[] getTodosLosEstados(){
        return new boolean[]{
                this.tieneEstado(EstadoAnimal.Tipo.FELIZ),
                this.tieneEstado(EstadoAnimal.Tipo.SACIADO),
                this.tieneEstado(EstadoAnimal.Tipo.LIMPIO),
                this.tieneEstado(EstadoAnimal.Tipo.SANO)
        };
    }
}