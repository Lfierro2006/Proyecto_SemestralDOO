package logicatienda.animales.estados;

import logicatienda.animales.Animal;
import logicatienda.animales.Estadistica;
import logicatienda.animales.Perro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el estado Triste.
 * Verifica la degradación de felicidad y los permisos de acciones.
 */
class TristeTest {

    private Animal perro;
    private EstadoAnimal triste;

    /**
     * Configuración inicial: crea un perro y una instancia del estado Triste.
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
        triste = new Triste();
    }

    /**
     * Verifica que el estado Triste degrade la felicidad en 4 puntos por ciclo.
     */
    @Test
    @DisplayName("Triste degrada la felicidad en 4 puntos")
    void testEjecutarDegradaFelicidadEn4() {
        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);
        triste.ejecutar(perro);
        assertEquals(felicidadInicial - 4, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que getTipo retorne TRISTE.
     */
    @Test
    @DisplayName("Triste tiene el tipo TRISTE")
    void testGetTipo() {
        assertEquals(Tipo.TRISTE, triste.getTipo());
    }

    /**
     * Verifica que un animal triste pueda jugar.
     */
    @Test
    @DisplayName("Triste permite jugar")
    void testPuedeJugar() {
        assertTrue(triste.puedeJugar());
    }

    /**
     * Verifica que un animal triste pueda ser curado.
     */
    @Test
    @DisplayName("Triste permite curar")
    void testPuedeCurar() {
        assertTrue(triste.puedeCurar());
    }

    /**
     * Verifica que un animal triste pueda ser limpiado.
     */
    @Test
    @DisplayName("Triste permite limpiar")
    void testPuedeLimpiar() {
        assertTrue(triste.puedeLimpiar());
    }
}