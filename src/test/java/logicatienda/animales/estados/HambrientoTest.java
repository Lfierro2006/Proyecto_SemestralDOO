package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Hambriento.
 * Verifica la degradación de saciedad y las restricciones de acciones.
 */
class HambrientoTest {

    private Animal perro;
    private EstadoAnimal hambriento;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Hambriento.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        hambriento = new Hambriento();
    }

    /**
     * Verifica que el estado Hambriento degrade la saciedad en 5 puntos por ciclo.
     */
    @Test
    @DisplayName("Hambriento degrada la saciedad en 5 puntos")
    void testEjecutarDegradaSaciedadEn5() {
        int saciedadInicial = perro.getNivel(Estadistica.SACIEDAD);
        hambriento.ejecutar(perro);
        assertEquals(saciedadInicial - 5, perro.getNivel(Estadistica.SACIEDAD));
    }

    /**
     * Verifica que getTipo retorne HAMBRIENTO.
     */
    @Test
    @DisplayName("Hambriento tiene el tipo HAMBRIENTO")
    void testGetTipo() {
        assertEquals(Tipo.HAMBRIENTO, hambriento.getTipo());
    }

    /**
     * Verifica que un animal hambriento no pueda jugar.
     */
    @Test
    @DisplayName("Hambriento no permite jugar")
    void testPuedeJugar() {
        assertFalse(hambriento.puedeJugar());
    }

    /**
     * Verifica que un animal hambriento pueda ser curado.
     */
    @Test
    @DisplayName("Hambriento permite curar")
    void testPuedeCurar() {
        assertTrue(hambriento.puedeCurar());
    }

    /**
     * Verifica que un animal hambriento pueda ser limpiado.
     */
    @Test
    @DisplayName("Hambriento permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(hambriento.puedeLimpiar());
    }
}