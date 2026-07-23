package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Enfermo.
 * Verifica la degradación de salud y las restricciones de acciones.
 */
class EnfermoTest {

    private Animal perro;
    private EstadoAnimal enfermo;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Enfermo.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        enfermo = new Enfermo();
    }

    /**
     * Verifica que el estado Enfermo degrade la salud en 5 puntos por ciclo.
     */
    @Test
    @DisplayName("Enfermo degrada la salud en 5 puntos")
    void testEjecutarDegradaSaludEn5() {
        int saludInicial = perro.getNivel(Estadistica.SALUD);
        enfermo.ejecutar(perro);
        assertEquals(saludInicial - 5, perro.getNivel(Estadistica.SALUD));
    }

    /**
     * Verifica que getTipo retorne ENFERMO.
     */
    @Test
    @DisplayName("Enfermo tiene el tipo ENFERMO")
    void testGetTipo() {
        assertEquals(Tipo.ENFERMO, enfermo.getTipo());
    }

    /**
     * Verifica que un animal enfermo no pueda jugar.
     */
    @Test
    @DisplayName("Enfermo no permite jugar")
    void testPuedeJugar() {
        assertFalse(enfermo.puedeJugar());
    }

    /**
     * Verifica que un animal enfermo pueda ser curado.
     */
    @Test
    @DisplayName("Enfermo permite curar")
    void testPuedeCurar() {
        assertTrue(enfermo.puedeCurar());
    }

    /**
     * Verifica que un animal enfermo pueda ser limpiado.
     */
    @Test
    @DisplayName("Enfermo permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(enfermo.puedeLimpiar());
    }
}