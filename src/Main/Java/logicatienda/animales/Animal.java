package logicatienda.animales;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import logicatienda.animales.estados.*;
import logicatienda.observers.AnimalObserver;

/**
 * Clase abstracta que representa a un animal en la tienda.
 * Implementa los patrones State y Observer para manejar estados y notificaciones.
 */

public abstract class Animal {
    public static final int FELICIDAD_MIN=60;
    public static final int SACIEDAD_MIN=60;
    public static final int HIGIENE_MIN=55;
    public static final int SALUD_MIN=65;
    public static final int MAX=100;
    public static final int MIN=0;
    public static final int VAL_INICIO=85;

    private List<EstadoAnimal> estadosActuales;
    private List<AnimalObserver> observers = new ArrayList<>();
    private final String nombre;
    protected Map<Estadistica, Integer> estadisticas;

    /**
     * Constructor de Animal.
     * Inicializa todas las estadísticas al valor inicial y los estados positivos.
     *
     * @param nombre El nombre del animal
     */
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

    /**
     * Obtiene el nombre del animal.
     * @return El nombre
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Establece el nivel de una estadística validando que esté entre 0 y 100.
     * Actualiza los estados y notifica a los observers.
     * @param estadistica La estadística a modificar
     * @param nivel El nuevo nivel
     */
    private void setNivel(Estadistica estadistica, int nivel){
        int nivelValidado = Math.max(MIN, Math.min(MAX, nivel));
        this.estadisticas.put(estadistica, nivelValidado);
        this.actualizarEstados();
        this.notificarObservers();
    }

    /**
     * Obtiene el nivel actual de una estadística.
     * @param estadistica La estadística a consultar
     * @return El nivel actual
     */
    public int getNivel(Estadistica estadistica){
        return this.estadisticas.getOrDefault(estadistica, VAL_INICIO);
    }

    /**
     * Aumenta el nivel de una estadística.
     * @param estadistica La estadística a aumentar
     * @param cantidad La cantidad a aumentar
     */
    public void aumentarNivel(Estadistica estadistica, int cantidad){
        this.setNivel(estadistica, this.getNivel(estadistica) + cantidad);
    }

    /**
     * Disminuye el nivel de una estadística.
     * @param estadistica La estadística a disminuir
     * @param cantidad La cantidad a disminuir
     */
    public void disminuirNivel(Estadistica estadistica, int cantidad) {
        this.setNivel(estadistica, this.getNivel(estadistica) - cantidad);
    }


    /**
     * Alimenta al animal. Solo funciona si está hambriento.
     * Aplica un bonus adicional según el déficit de saciedad.
     */
    public void Alimentar(){
        if (!this.tieneEstado(Tipo.HAMBRIENTO)) {
            return;
        }
        int hambre = this.getNivel(Estadistica.SACIEDAD);
        int bonus = (SACIEDAD_MIN-hambre)/3;

        this.aumentarNivel(Estadistica.SACIEDAD, 35 + bonus);
        this.aumentarNivel(Estadistica.FELICIDAD, 10);
    }

    /**
     * Jugar con el animal. Solo funciona si no está enfermo ni hambriento.
     * Aplica un bonus adicional según el déficit de felicidad.
     */
    public void Jugar(){
        if (this.tieneEstado(Tipo.ENFERMO)||this.tieneEstado(Tipo.HAMBRIENTO)){
            return;
        }
        int felicidad = this.getNivel(Estadistica.FELICIDAD);
        int bonus = (FELICIDAD_MIN-felicidad)/3;

        this.aumentarNivel(Estadistica.FELICIDAD,40+bonus);
        this.disminuirNivel(Estadistica.SACIEDAD,10);
        this.disminuirNivel(Estadistica.HIGIENE,15);
    }

    /**
     * Limpia al animal. Solo funciona si está sucio.
     * Aplica un bonus adicional según el déficit de higiene.
     */
    public void Limpiar(){
        if (!this.tieneEstado(Tipo.SUCIO)) {
            return;
        }
        int higiene=this.getNivel(Estadistica.HIGIENE);
        int bonus = (HIGIENE_MIN-higiene)/3;

        this.aumentarNivel(Estadistica.HIGIENE,40+bonus);
        this.aumentarNivel(Estadistica.SALUD,10);
    }

    /**
     * Cura al animal. Solo funciona si está enfermo.
     * Aplica un bonus adicional según el déficit de salud.
     *
     * @param cantidad La cantidad de medicina a aplicar
     */
    public void Curar(int cantidad){
        if (!this.tieneEstado(Tipo.ENFERMO)) {
            return;
        }
        int salud=this.getNivel(Estadistica.SALUD);
        int bonus=(SALUD_MIN-salud)/4;

        this.aumentarNivel(Estadistica.SALUD,cantidad+bonus);
        this.disminuirNivel(Estadistica.FELICIDAD,5);
    }


    /**
     * Verifica si el animal tiene un estado específico.
     * @param tipo El tipo de estado a verificar
     * @return true si tiene el estado, false en caso contrario
     */
    public boolean tieneEstado(Tipo tipo){
        for (EstadoAnimal estado : this.estadosActuales){
            if (estado.getTipo() == tipo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza la lista de estados según los niveles actuales de estadísticas.
     * Notifica a los observers del cambio.
     */
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
        this.notificarObservers();
    }

    /**
     * Ejecuta el comportamiento de todos los estados actuales del animal.
     * Cada estado aplica su efecto sobre las estadísticas.
     */
    public void ejecutarEstado(){
        for (EstadoAnimal estado : this.estadosActuales){
            estado.ejecutar(this);
        }
    }

    /**
     * Obtiene un array de booleanos con el estado positivo de cada estadística.
     * Orden: [FELIZ, SACIADO, LIMPIO, SANO]
     * @return Array de 4 booleanos
     */
    public boolean[] getTodosLosEstados(){
        return new boolean[]{
                this.tieneEstado(Tipo.FELIZ),
                this.tieneEstado(Tipo.SACIADO),
                this.tieneEstado(Tipo.LIMPIO),
                this.tieneEstado(Tipo.SANO)
        };
    }

    /**
     * Registra un observer para recibir notificaciones de cambios.
     * @param observer El observer a registrar
     */
    public void addObserver(AnimalObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Elimina un observer registrado.
     * @param observer El observer a eliminar
     */
    public void removeObserver(AnimalObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifica a todos los observers que las estadísticas han cambiado.
     */
    private void notificarObservers() {
        for (AnimalObserver observer : this.observers) {
            observer.onEstadisticasCambiadas(this);
        }
    }
}