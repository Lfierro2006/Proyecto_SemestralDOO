package logicatienda.animales;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Perro.
 * Verifica que Perro herede correctamente de Animal e implemente Terrestre.
 */
class PerroTest {

    /**
     * Verifica que Perro sea una instancia de Animal y Terrestre.
     */
    @Test
    @DisplayName("Perro extiende Animal e implementa Terrestre")
    void testPerroEsAnimalYTerrestre() {
        Perro perro = new Perro("Rex");
        assertTrue(perro instanceof Animal);
        assertTrue(perro instanceof Terrestre);
    }

    /**
     * Verifica que el constructor de Perro asigne correctamente el nombre.
     */
    @Test
    @DisplayName("Perro se crea con el nombre correcto")
    void testPerroConstructorConNombre() {
        Perro perro = new Perro("Firulais");
        assertEquals("Firulais", perro.getNombre());
    }
}