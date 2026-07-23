package logicatienda.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Usuario.
 * Verifica la correcta gestión del dinero y del inventario.
 */
class UsuarioTest {

    private Usuario usuario;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea un usuario con 1000 de dinero inicial.
     */
    @BeforeEach
    void setUp() {
        usuario = new Usuario(1000);
    }

    /**
     * Verifica que el constructor inicialice correctamente el dinero.
     */
    @Test
    @DisplayName("El constructor inicializa el dinero correctamente")
    void testConstructorInicializaDinero() {
        assertEquals(1000, usuario.getDinero());
    }

    /**
     * Verifica que getDinero retorne el dinero actual.
     */
    @Test
    @DisplayName("getDinero retorna el dinero actual")
    void testGetDinero() {
        assertEquals(1000, usuario.getDinero());
        usuario.quitarDinero(300);
        assertEquals(700, usuario.getDinero());
    }

    /**
     * Verifica que darDinero aumente correctamente el dinero.
     */
    @Test
    @DisplayName("darDinero aumenta el dinero correctamente")
    void testDarDinero() {
        usuario.darDinero(500);
        assertEquals(1500, usuario.getDinero());

        usuario.darDinero(200);
        assertEquals(1700, usuario.getDinero());
    }

    /**
     * Verifica que quitarDinero disminuya correctamente el dinero.
     */
    @Test
    @DisplayName("quitarDinero disminuye el dinero correctamente")
    void testQuitarDinero() {
        usuario.quitarDinero(300);
        assertEquals(700, usuario.getDinero());

        usuario.quitarDinero(200);
        assertEquals(500, usuario.getDinero());
    }

    /**
     * Verifica que quitarDinero permita que el dinero sea negativo.
     */
    @Test
    @DisplayName("quitarDinero permite que el dinero sea negativo")
    void testQuitarDineroPermiteNegativo() {
        usuario.quitarDinero(1500);
        assertEquals(-500, usuario.getDinero());
    }

    /**
     * Verifica que sumarItem aumente correctamente la cantidad del ítem.
     */
    @Test
    @DisplayName("sumarItem aumenta la cantidad del ítem correctamente")
    void testSumarItem() {
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(1, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(2, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.MEDICINA.getIndex());
        assertEquals(1, usuario.getCantItem(Item.MEDICINA.getIndex()));
    }

    /**
     * Verifica que restarItem disminuya correctamente la cantidad del ítem.
     */
    @Test
    @DisplayName("restarItem disminuye la cantidad del ítem correctamente")
    void testRestarItem() {
        usuario.sumarItem(Item.COMIDA.getIndex());
        usuario.sumarItem(Item.COMIDA.getIndex());
        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(3, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(2, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(1, usuario.getCantItem(Item.COMIDA.getIndex()));
    }

    /**
     * Verifica que restarItem no permita que la cantidad baje de 0.
     */
    @Test
    @DisplayName("restarItem no permite que la cantidad baje de 0")
    void testRestarItemNoBajaDeCero() {
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(1, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));
    }

    /**
     * Verifica que getCantItem retorne la cantidad correcta.
     */
    @Test
    @DisplayName("getCantItem retorna la cantidad correcta")
    void testGetCantItem() {
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.COMIDA.getIndex());
        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(2, usuario.getCantItem(Item.COMIDA.getIndex()));

        usuario.sumarItem(Item.MEDICINA.getIndex());
        assertEquals(1, usuario.getCantItem(Item.MEDICINA.getIndex()));

        assertEquals(0, usuario.getCantItem(Item.PERRO.getIndex()));
    }

    /**
     * Verifica que diferentes items sean independientes entre sí.
     */
    @Test
    @DisplayName("Diferentes items son independientes entre sí")
    void testItemsIndependientes() {
        usuario.sumarItem(Item.COMIDA.getIndex());
        usuario.sumarItem(Item.COMIDA.getIndex());
        usuario.sumarItem(Item.MEDICINA.getIndex());
        usuario.sumarItem(Item.PERRO.getIndex());
        usuario.sumarItem(Item.PERRO.getIndex());
        usuario.sumarItem(Item.PERRO.getIndex());

        assertEquals(2, usuario.getCantItem(Item.COMIDA.getIndex()));
        assertEquals(1, usuario.getCantItem(Item.MEDICINA.getIndex()));
        assertEquals(3, usuario.getCantItem(Item.PERRO.getIndex()));
        assertEquals(0, usuario.getCantItem(Item.GATO.getIndex()));
        assertEquals(0, usuario.getCantItem(Item.AVE.getIndex()));
        assertEquals(0, usuario.getCantItem(Item.PEZ.getIndex()));
    }

    /**
     * Verifica el flujo completo de operaciones de dinero e inventario.
     */
    @Test
    @DisplayName("Flujo completo de operaciones funciona correctamente")
    void testFlujoCompletoOperaciones() {
        // Verificar estado inicial
        assertEquals(1000, usuario.getDinero());
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        // Comprar comida (simulado)
        usuario.quitarDinero(200);
        usuario.sumarItem(Item.COMIDA.getIndex());
        assertEquals(800, usuario.getDinero());
        assertEquals(1, usuario.getCantItem(Item.COMIDA.getIndex()));

        // Comprar medicina
        usuario.quitarDinero(150);
        usuario.sumarItem(Item.MEDICINA.getIndex());
        assertEquals(650, usuario.getDinero());
        assertEquals(1, usuario.getCantItem(Item.MEDICINA.getIndex()));

        // Usar comida
        usuario.restarItem(Item.COMIDA.getIndex());
        assertEquals(0, usuario.getCantItem(Item.COMIDA.getIndex()));

        // Recibir dinero por venta
        usuario.darDinero(500);
        assertEquals(1150, usuario.getDinero());
    }
}