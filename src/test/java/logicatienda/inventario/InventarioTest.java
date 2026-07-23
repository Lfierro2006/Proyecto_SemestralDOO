package logicatienda.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Inventario.
 * Verifica la correcta gestión de cantidades de ítems,
 * incluyendo sumar, quitar y consultar cantidades.
 */
class InventarioTest {

    private Inventario inventario;
    private static final int CANTIDAD_TIPOS = 6;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea un inventario con 6 tipos de ítems.
     */
    @BeforeEach
    void setUp() {
        inventario = new Inventario(CANTIDAD_TIPOS);
    }

    /**
     * Verifica que el constructor inicialice todas las cantidades en 0.
     */
    @Test
    @DisplayName("El constructor inicializa todas las cantidades en 0")
    void testConstructorInicializaCantidadesEnCero() {
        for (int i = 0; i < CANTIDAD_TIPOS; i++) {
            assertEquals(0, inventario.getCantidad(i));
        }
    }

    /**
     * Verifica que sumarUno incremente correctamente la cantidad de un ítem.
     */
    @Test
    @DisplayName("sumarUno incrementa correctamente la cantidad")
    void testSumarUnoIncrementaCantidad() {
        int indice = 0;

        inventario.sumarUno(indice);
        assertEquals(1, inventario.getCantidad(indice));

        inventario.sumarUno(indice);
        assertEquals(2, inventario.getCantidad(indice));

        inventario.sumarUno(indice);
        assertEquals(3, inventario.getCantidad(indice));
    }

    /**
     * Verifica que quitarUno decremente correctamente la cantidad de un ítem.
     */
    @Test
    @DisplayName("quitarUno decrementa correctamente la cantidad")
    void testQuitarUnoDecrementaCantidad() {
        int indice = 0;

        inventario.sumarUno(indice);
        inventario.sumarUno(indice);
        inventario.sumarUno(indice);
        assertEquals(3, inventario.getCantidad(indice));

        inventario.quitarUno(indice);
        assertEquals(2, inventario.getCantidad(indice));

        inventario.quitarUno(indice);
        assertEquals(1, inventario.getCantidad(indice));
    }

    /**
     * Verifica que quitarUno no permita que la cantidad baje de 0.
     */
    @Test
    @DisplayName("quitarUno no permite que la cantidad baje de 0")
    void testQuitarUnoNoBajaDeCero() {
        int indice = 0;

        // Intentar quitar cuando ya está en 0
        inventario.quitarUno(indice);
        assertEquals(0, inventario.getCantidad(indice));

        // Agregar uno y quitar dos
        inventario.sumarUno(indice);
        assertEquals(1, inventario.getCantidad(indice));

        inventario.quitarUno(indice);
        assertEquals(0, inventario.getCantidad(indice));

        inventario.quitarUno(indice);
        assertEquals(0, inventario.getCantidad(indice));
    }

    /**
     * Verifica que getCantidad retorne 0 para índices inválidos.
     */
    @Test
    @DisplayName("getCantidad retorna 0 para índices inválidos")
    void testGetCantidadIndiceInvalido() {
        assertEquals(0, inventario.getCantidad(-1));
        assertEquals(0, inventario.getCantidad(CANTIDAD_TIPOS));
        assertEquals(0, inventario.getCantidad(CANTIDAD_TIPOS + 10));
    }

    /**
     * Verifica que sumarUno no haga nada con índices inválidos.
     */
    @Test
    @DisplayName("sumarUno no hace nada con índices inválidos")
    void testSumarUnoIndiceInvalido() {
        inventario.sumarUno(-1);
        inventario.sumarUno(CANTIDAD_TIPOS);
        inventario.sumarUno(CANTIDAD_TIPOS + 10);

        // Ninguna cantidad debe haber cambiado
        for (int i = 0; i < CANTIDAD_TIPOS; i++) {
            assertEquals(0, inventario.getCantidad(i));
        }
    }

    /**
     * Verifica que quitarUno no haga nada con índices inválidos.
     */
    @Test
    @DisplayName("quitarUno no hace nada con índices inválidos")
    void testQuitarUnoIndiceInvalido() {
        // Agregar algunos ítems primero
        for (int i = 0; i < CANTIDAD_TIPOS; i++) {
            inventario.sumarUno(i);
        }

        // Intentar quitar con índices inválidos
        inventario.quitarUno(-1);
        inventario.quitarUno(CANTIDAD_TIPOS);
        inventario.quitarUno(CANTIDAD_TIPOS + 10);

        // Las cantidades deben permanecer igual
        for (int i = 0; i < CANTIDAD_TIPOS; i++) {
            assertEquals(1, inventario.getCantidad(i));
        }
    }

    /**
     * Verifica que diferentes índices sean independientes entre sí.
     */
    @Test
    @DisplayName("Los índices son independientes entre sí")
    void testIndicesIndependientes() {
        inventario.sumarUno(0);
        inventario.sumarUno(0);
        inventario.sumarUno(1);
        inventario.sumarUno(2);
        inventario.sumarUno(2);
        inventario.sumarUno(2);

        assertEquals(2, inventario.getCantidad(0));
        assertEquals(1, inventario.getCantidad(1));
        assertEquals(3, inventario.getCantidad(2));
        assertEquals(0, inventario.getCantidad(3));
        assertEquals(0, inventario.getCantidad(4));
        assertEquals(0, inventario.getCantidad(5));
    }

    /**
     * Verifica el flujo completo de operaciones: sumar, quitar y consultar.
     */
    @Test
    @DisplayName("Flujo completo de operaciones funciona correctamente")
    void testFlujoCompletoOperaciones() {
        int indice = 0;

        // Sumar múltiples veces
        inventario.sumarUno(indice);
        inventario.sumarUno(indice);
        inventario.sumarUno(indice);
        assertEquals(3, inventario.getCantidad(indice));

        // Quitar algunas
        inventario.quitarUno(indice);
        assertEquals(2, inventario.getCantidad(indice));

        // Sumar más
        inventario.sumarUno(indice);
        assertEquals(3, inventario.getCantidad(indice));

        // Quitar todas
        inventario.quitarUno(indice);
        inventario.quitarUno(indice);
        inventario.quitarUno(indice);
        assertEquals(0, inventario.getCantidad(indice));

        // Intentar quitar cuando está en 0
        inventario.quitarUno(indice);
        assertEquals(0, inventario.getCantidad(indice));
    }

    /**
     * Verifica que el inventario maneje correctamente múltiples tipos de ítems.
     */
    @Test
    @DisplayName("Maneja correctamente múltiples tipos de ítems")
    void testManejaMultiplesTipos() {
        // Sumar diferentes cantidades a diferentes índices
        inventario.sumarUno(0);
        inventario.sumarUno(0);
        inventario.sumarUno(1);
        inventario.sumarUno(2);
        inventario.sumarUno(2);
        inventario.sumarUno(2);
        inventario.sumarUno(3);
        inventario.sumarUno(4);
        inventario.sumarUno(5);
        inventario.sumarUno(5);

        // Verificar todas las cantidades
        assertEquals(2, inventario.getCantidad(0));
        assertEquals(1, inventario.getCantidad(1));
        assertEquals(3, inventario.getCantidad(2));
        assertEquals(1, inventario.getCantidad(3));
        assertEquals(1, inventario.getCantidad(4));
        assertEquals(2, inventario.getCantidad(5));
    }
}