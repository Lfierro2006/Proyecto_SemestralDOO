package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Limpio.
 * Verifica la degradación de higiene y los permisos de acciones.
 */
class LimpioTest {

    private Animal perro;
    private EstadoAnimal limpio;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Limpio.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        limpio = new Limpio();
    }

    /**
     * Verifica que el estado Limpio degrade la higiene en 2 puntos por ciclo.
     */
    @Test
    @DisplayName("Limpio degrada la higiene en 2 puntos")
    void testEjecutarDegradaHigieneEn2() {
        int higieneInicial = perro.getNivel(Estadistica.HIGIENE);
        limpio.ejecutar(perro);
        assertEquals(higieneInicial - 2, perro.getNivel(Estadistica.HIGIENE));
    }

    /**
     * Verifica que getTipo retorne LIMPIO.
     */
    @Test
    @DisplayName("Limpio tiene el tipo LIMPIO")
    void testGetTipo() {
        assertEquals(Tipo.LIMPIO, limpio.getTipo());
    }

    /**
     * Verifica que un animal limpio pueda jugar.
     */
    @Test
    @DisplayName("Limpio permite jugar")
    void testPuedeJugar() {
        assertTrue(limpio.puedeJugar());
    }

    /**
     * Verifica que un animal limpio pueda ser curado.
     */
    @Test
    @DisplayName("Limpio permite curar")
    void testPuedeCurar() {
        assertTrue(limpio.puedeCurar());
    }

    /**
     * Verifica que un animal limpio no pueda ser limpiado (ya está limpio).
     */
    @Test
    @DisplayName("Limpio no permite limpiar")
    void testPuedeLimpiar() {
        assertFalse(limpio.puedeLimpiar());
    }
}