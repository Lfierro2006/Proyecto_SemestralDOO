package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Saciado.
 * Verifica la degradación de saciedad y los permisos de acciones.
 */
class SaciadoTest {

    private Animal perro;
    private EstadoAnimal saciado;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Saciado.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        saciado = new Saciado();
    }

    /**
     * Verifica que el estado Saciado degrade la saciedad en 2 puntos por ciclo.
     */
    @Test
    @DisplayName("Saciado degrada la saciedad en 2 puntos")
    void testEjecutarDegradaSaciedadEn2() {
        int saciedadInicial = perro.getNivel(Estadistica.SACIEDAD);
        saciado.ejecutar(perro);
        assertEquals(saciedadInicial - 2, perro.getNivel(Estadistica.SACIEDAD));
    }

    /**
     * Verifica que getTipo retorne SACIADO.
     */
    @Test
    @DisplayName("Saciado tiene el tipo SACIADO")
    void testGetTipo() {
        assertEquals(Tipo.SACIADO, saciado.getTipo());
    }

    /**
     * Verifica que un animal saciado pueda jugar.
     */
    @Test
    @DisplayName("Saciado permite jugar")
    void testPuedeJugar() {
        assertTrue(saciado.puedeJugar());
    }

    /**
     * Verifica que un animal saciado pueda ser curado.
     */
    @Test
    @DisplayName("Saciado permite curar")
    void testPuedeCurar() {
        assertTrue(saciado.puedeCurar());
    }

    /**
     * Verifica que un animal saciado pueda ser limpiado.
     */
    @Test
    @DisplayName("Saciado permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(saciado.puedeLimpiar());
    }
}