package logicatienda.animales;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Pez.
 * Verifica que Pez herede correctamente de Animal e implemente Acuatico.
 */
class PezTest {

    /**
     * Verifica que Pez sea una instancia de Animal y Acuatico.
     */
    @Test
    @DisplayName("Pez extiende Animal e implementa Acuatico")
    void testPezEsAnimalYAcuatico() {
        Pez pez = new Pez("Nemo");
        assertTrue(pez instanceof Animal);
        assertTrue(pez instanceof Acuatico);
    }

    /**
     * Verifica que el constructor de Pez asigne correctamente el nombre.
     */
    @Test
    @DisplayName("Pez se crea con el nombre correcto")
    void testPezConstructorConNombre() {
        Pez pez = new Pez("Dory");
        assertEquals("Dory", pez.getNombre());
    }
}