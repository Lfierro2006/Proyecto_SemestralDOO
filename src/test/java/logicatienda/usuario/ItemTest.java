package logicatienda.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el enum Item.
 * Verifica que los items tengan los índices correctos.
 */
class ItemTest {

    /**
     * Verifica que COMIDA tenga el índice 0.
     */
    @Test
    @DisplayName("COMIDA tiene el índice 0")
    void testComidaIndice() {
        assertEquals(0, Item.COMIDA.getIndex());
    }

    /**
     * Verifica que MEDICINA tenga el índice 1.
     */
    @Test
    @DisplayName("MEDICINA tiene el índice 1")
    void testMedicinaIndice() {
        assertEquals(1, Item.MEDICINA.getIndex());
    }

    /**
     * Verifica que PERRO tenga el índice 2.
     */
    @Test
    @DisplayName("PERRO tiene el índice 2")
    void testPerroIndice() {
        assertEquals(2, Item.PERRO.getIndex());
    }

    /**
     * Verifica que GATO tenga el índice 3.
     */
    @Test
    @DisplayName("GATO tiene el índice 3")
    void testGatoIndice() {
        assertEquals(3, Item.GATO.getIndex());
    }

    /**
     * Verifica que AVE tenga el índice 4.
     */
    @Test
    @DisplayName("AVE tiene el índice 4")
    void testAveIndice() {
        assertEquals(4, Item.AVE.getIndex());
    }

    /**
     * Verifica que PEZ tenga el índice 5.
     */
    @Test
    @DisplayName("PEZ tiene el índice 5")
    void testPezIndice() {
        assertEquals(5, Item.PEZ.getIndex());
    }

    /**
     * Verifica que todos los items tengan índices únicos.
     */
    @Test
    @DisplayName("Todos los items tienen índices únicos")
    void testIndicesUnicos() {
        int[] indices = {
                Item.COMIDA.getIndex(),
                Item.MEDICINA.getIndex(),
                Item.PERRO.getIndex(),
                Item.GATO.getIndex(),
                Item.AVE.getIndex(),
                Item.PEZ.getIndex()
        };

        for (int i = 0; i < indices.length; i++) {
            for (int j = i + 1; j < indices.length; j++) {
                assertNotEquals(indices[i], indices[j]);
            }
        }
    }

    /**
     * Verifica que todos los índices estén en el rango 0-5.
     */
    @Test
    @DisplayName("Todos los índices están en el rango 0-5")
    void testIndicesEnRango() {
        assertTrue(Item.COMIDA.getIndex() >= 0 && Item.COMIDA.getIndex() <= 5);
        assertTrue(Item.MEDICINA.getIndex() >= 0 && Item.MEDICINA.getIndex() <= 5);
        assertTrue(Item.PERRO.getIndex() >= 0 && Item.PERRO.getIndex() <= 5);
        assertTrue(Item.GATO.getIndex() >= 0 && Item.GATO.getIndex() <= 5);
        assertTrue(Item.AVE.getIndex() >= 0 && Item.AVE.getIndex() <= 5);
        assertTrue(Item.PEZ.getIndex() >= 0 && Item.PEZ.getIndex() <= 5);
    }

    /**
     * Verifica que getIndex retorne el índice correcto para cada item.
     */
    @Test
    @DisplayName("getIndex retorna el índice correcto para cada item")
    void testGetIndex() {
        assertEquals(0, Item.COMIDA.getIndex());
        assertEquals(1, Item.MEDICINA.getIndex());
        assertEquals(2, Item.PERRO.getIndex());
        assertEquals(3, Item.GATO.getIndex());
        assertEquals(4, Item.AVE.getIndex());
        assertEquals(5, Item.PEZ.getIndex());
    }
}