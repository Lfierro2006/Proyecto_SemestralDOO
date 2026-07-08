package logicatienda.comprador;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import java.util.Random;
import logicatienda.animales.estados.Tipo;

public class Comprador {

    private final String tipoMascotaDeseada;
    private final int valorDeCompra;
    public static final int PRECIO_MINIMO = 500;
    public static final int PENALIZACION_ESTADO = 50;

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

    public String getTipoMascotaDeseada() {
        return tipoMascotaDeseada;
    }


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

        int pFelicidad = ((Animal.MAX - mascota.getNivel(Estadistica.FELICIDAD)) / 10) * 5;
        int pSaciedad  = ((Animal.MAX - mascota.getNivel(Estadistica.SACIEDAD)) / 10) * 5;
        int pHigiene   = ((Animal.MAX - mascota.getNivel(Estadistica.HIGIENE)) / 10) * 5;
        int pSalud     = ((Animal.MAX - mascota.getNivel(Estadistica.SALUD)) / 10) * 5;

        precioCalculado -= (pFelicidad + pSaciedad + pHigiene + pSalud);

        if (precioCalculado < PRECIO_MINIMO) {
            precioCalculado = PRECIO_MINIMO;
        }

        return precioCalculado;
    }
}
