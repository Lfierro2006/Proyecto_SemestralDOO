package logicatienda.animales;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Gato.
 * Verifica que Gato herede correctamente de Animal e implemente Terrestre.
 */
class GatoTest {

    /**
     * Verifica que Gato sea una instancia de Animal y Terrestre.
     */
    @Test
    @DisplayName("Gato extiende Animal e implementa Terrestre")
    void testGatoEsAnimalYTerrestre() {
        Gato gato = new Gato("Michi");
        assertTrue(gato instanceof Animal);
        assertTrue(gato instanceof Terrestre);
    }

    /**
     * Verifica que el constructor de Gato asigne correctamente el nombre.
     */
    @Test
    @DisplayName("Gato se crea con el nombre correcto")
    void testGatoConstructorConNombre() {
        Gato gato = new Gato("Garfield");
        assertEquals("Garfield", gato.getNombre());
    }
}