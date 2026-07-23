package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Feliz.
 * Verifica la degradación de felicidad y los permisos de acciones.
 */
class FelizTest {

    private Animal perro;
    private EstadoAnimal feliz;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Feliz.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        feliz = new Feliz();
    }

    /**
     * Verifica que el estado Feliz degrade la felicidad en 2 puntos por ciclo.
     */
    @Test
    @DisplayName("Feliz degrada la felicidad en 2 puntos")
    void testEjecutarDegradaFelicidadEn2() {
        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);
        feliz.ejecutar(perro);
        assertEquals(felicidadInicial - 2, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que getTipo retorne FELIZ.
     */
    @Test
    @DisplayName("Feliz tiene el tipo FELIZ")
    void testGetTipo() {
        assertEquals(Tipo.FELIZ, feliz.getTipo());
    }

    /**
     * Verifica que un animal feliz pueda jugar.
     */
    @Test
    @DisplayName("Feliz permite jugar")
    void testPuedeJugar() {
        assertTrue(feliz.puedeJugar());
    }

    /**
     * Verifica que un animal feliz pueda ser curado.
     */
    @Test
    @DisplayName("Feliz permite curar")
    void testPuedeCurar() {
        assertTrue(feliz.puedeCurar());
    }

    /**
     * Verifica que un animal feliz pueda ser limpiado.
     */
    @Test
    @DisplayName("Feliz permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(feliz.puedeLimpiar());
    }
}