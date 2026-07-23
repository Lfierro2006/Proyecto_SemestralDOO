package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Sano.
 * Verifica la degradación de salud y los permisos de acciones.
 */
class SanoTest {

    private Animal perro;
    private EstadoAnimal sano;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Sano.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        sano = new Sano();
    }

    /**
     * Verifica que el estado Sano degrade la salud en 3 puntos por ciclo.
     */
    @Test
    @DisplayName("Sano degrada la salud en 3 puntos")
    void testEjecutarDegradaSaludEn3() {
        int saludInicial = perro.getNivel(Estadistica.SALUD);
        sano.ejecutar(perro);
        assertEquals(saludInicial - 3, perro.getNivel(Estadistica.SALUD));
    }

    /**
     * Verifica que getTipo retorne SANO.
     */
    @Test
    @DisplayName("Sano tiene el tipo SANO")
    void testGetTipo() {
        assertEquals(Tipo.SANO, sano.getTipo());
    }

    /**
     * Verifica que un animal sano pueda jugar.
     */
    @Test
    @DisplayName("Sano permite jugar")
    void testPuedeJugar() {
        assertTrue(sano.puedeJugar());
    }

    /**
     * Verifica que un animal sano no pueda ser curado (no está enfermo).
     */
    @Test
    @DisplayName("Sano no permite curar")
    void testPuedeCurar() {
        assertFalse(sano.puedeCurar());
    }

    /**
     * Verifica que un animal sano pueda ser limpiado.
     */
    @Test
    @DisplayName("Sano permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(sano.puedeLimpiar());
    }
}