package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Sucio.
 * Verifica la degradación de higiene y los permisos de acciones.
 */
class SucioTest {

    private Animal perro;
    private EstadoAnimal sucio;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Sucio.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        sucio = new Sucio();
    }

    /**
     * Verifica que el estado Sucio degrade la higiene en 3 puntos por ciclo.
     */
    @Test
    @DisplayName("Sucio degrada la higiene en 3 puntos")
    void testEjecutarDegradaHigieneEn3() {
        int higieneInicial = perro.getNivel(Estadistica.HIGIENE);
        sucio.ejecutar(perro);
        assertEquals(higieneInicial - 3, perro.getNivel(Estadistica.HIGIENE));
    }

    /**
     * Verifica que getTipo retorne SUCIO.
     */
    @Test
    @DisplayName("Sucio tiene el tipo SUCIO")
    void testGetTipo() {
        assertEquals(Tipo.SUCIO, sucio.getTipo());
    }

    /**
     * Verifica que un animal sucio pueda jugar.
     */
    @Test
    @DisplayName("Sucio permite jugar")
    void testPuedeJugar() {
        assertTrue(sucio.puedeJugar());
    }

    /**
     * Verifica que un animal sucio pueda ser curado.
     */
    @Test
    @DisplayName("Sucio permite curar")
    void testPuedeCurar() {
        assertTrue(sucio.puedeCurar());
    }

    /**
     * Verifica que un animal sucio pueda ser limpiado.
     */
    @Test
    @DisplayName("Sucio permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(sucio.puedeLimpiar());
    }
}