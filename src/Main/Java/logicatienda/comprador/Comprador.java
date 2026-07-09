package logicatienda.comprador;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import java.util.Random;
import logicatienda.animales.estados.Tipo;

/**
 * Representa a un cliente que visita la tienda para comprar una mascota.
 * El comprador tiene una preferencia por un tipo específico de animal
 * y calcula el precio de compra en función del estado del animal.
 */
public class Comprador {

    private final String tipoMascotaDeseada;
    private final int valorDeCompra;
    public static final int PRECIO_MINIMO = 500;
    public static final int PENALIZACION_ESTADO = 50;

    /**
     * Constructor del comprador.
     * Elige aleatoriamente un tipo de mascota deseada entre Perro, Gato, Ave y Pez.
     *
     * @param valorDeCompraBase Valor base que el comprador está dispuesto a pagar
     */
    public Comprador(int valorDeCompraBase) {
        this.valorDeCompra = valorDeCompraBase;
        Random rand = new Random();
        int eleccion = rand.nextInt(4);

        switch (eleccion) {
            case 0:
                this.tipoMascotaDeseada = "Perro";
                break;
            case 1:
                this.tipoMascotaDeseada = "Gato";
                break;
            case 2:
                this.tipoMascotaDeseada = "Ave";
                break;
            case 3:
                this.tipoMascotaDeseada = "Pez";
                break;
            default:
                this.tipoMascotaDeseada = "Perro";
                break;
        }
    }

    /**
     * Obtiene el tipo de mascota que desea el comprador.
     *
     * @return El tipo de mascota deseada (Perro, Gato, Ave o Pez)
     */
    public String getTipoMascotaDeseada() {
        return tipoMascotaDeseada;
    }

    /**
     * Calcula el precio final que el comprador está dispuesto a pagar por una mascota.
     * El precio varía según:
     * - Estados adversos del animal (TRISTE, HAMBRIENTO, SUCIO, ENFERMO) provocan -$50 c/u
     * - Diferencia entre el mínimo y el nivel actual de cada estadística penalización variable
     *   (puede sumar si las estaadisticas están bien cuidadas)
     *
     * @param mascota El animal a evaluar
     * @return El precio final calculado, o 0 si no coincide con la especie deseada
     */
    public int calcularPrecioFinal(Animal mascota) {

        String especieMascota = mascota.getClass().getSimpleName();
        if (!especieMascota.equalsIgnoreCase(this.tipoMascotaDeseada)) {
            System.out.println("El cliente busca un " + this.tipoMascotaDeseada);
            return 0;
        }

        int precioCalculado = this.valorDeCompra;

        int cantidadEstadosAdversos = 0;
        if (mascota.tieneEstado(Tipo.TRISTE)) cantidadEstadosAdversos++;
        if (mascota.tieneEstado(Tipo.HAMBRIENTO)) cantidadEstadosAdversos++;
        if (mascota.tieneEstado(Tipo.SUCIO)) cantidadEstadosAdversos++;
        if (mascota.tieneEstado(Tipo.ENFERMO)) cantidadEstadosAdversos++;

        precioCalculado -= (cantidadEstadosAdversos * PENALIZACION_ESTADO);

        int pFelicidad = (Animal.FELICIDAD_MIN - mascota.getNivel(Estadistica.FELICIDAD)) / 2;
        int pSaciedad  = (Animal.SACIEDAD_MIN - mascota.getNivel(Estadistica.SACIEDAD)) / 2;
        int pHigiene   = (Animal.HIGIENE_MIN - mascota.getNivel(Estadistica.HIGIENE))/ 2;
        int pSalud     = (Animal.SALUD_MIN - mascota.getNivel(Estadistica.SALUD)) / 2;

        precioCalculado -= (pFelicidad + pSaciedad + pHigiene + pSalud);

        if (precioCalculado < PRECIO_MINIMO) {
            precioCalculado = PRECIO_MINIMO;
        }

        return precioCalculado;
    }
}
