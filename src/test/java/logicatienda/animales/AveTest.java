package logicatienda.animales;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Ave.
 * Verifica que Ave herede correctamente de Animal e implemente Aereo.
 */
class AveTest {

    /**
     * Verifica que Ave sea una instancia de Animal y Aereo.
     */
    @Test
    @DisplayName("Ave extiende Animal e implementa Aereo")
    void testAveEsAnimalYAereo() {
        Ave ave = new Ave("Piolín");
        assertTrue(ave instanceof Animal);
        assertTrue(ave instanceof Aereo);
    }

    /**
     * Verifica que el constructor de Ave asigne correctamente el nombre.
     */
    @Test
    @DisplayName("Ave se crea con el nombre correcto")
    void testAveConstructorConNombre() {
        Ave ave = new Ave("Tweety");
        assertEquals("Tweety", ave.getNombre());
    }
}