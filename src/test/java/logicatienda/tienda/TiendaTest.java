package logicatienda.tienda;

import logicatienda.animales.*;
import logicatienda.comprador.Comprador;
import logicatienda.habitat.*;
import logicatienda.usuario.Item;
import logicatienda.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Tienda.
 * Verifica la correcta gestión de compras de hábitats y animales,
 * ventas a clientes, y la gestión del presupuesto e inventario.
 */
class TiendaTest {

    private Tienda tienda;
    private Habitat jaula;
    private Habitat pecera;
    private Animal perro;
    private Animal ave;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea una tienda con dinero inicial y todos los hábitats y animales necesarios.
     */
    @BeforeEach
    void setUp() {
        tienda = new Tienda(10000);
        jaula = new Jaula();
        pecera = new Pecera();
        perro = new Perro("Rex");
        ave = new Ave("Piolin");
    }

    /**
     * Verifica que el constructor inicialice correctamente el dinero del usuario.
     */
    @Test
    @DisplayName("El constructor inicializa el dinero correctamente")
    void testConstructorInicializaDinero() {
        assertEquals(10000, tienda.getPresupuesto());
        assertEquals(10000, tienda.getUsuario().getDinero());
    }

    /**
     * Verifica que getPresupuesto retorne el dinero actual del usuario.
     */
    @Test
    @DisplayName("getPresupuesto retorna el dinero actual")
    void testGetPresupuesto() {
        assertEquals(10000, tienda.getPresupuesto());
        tienda.getUsuario().quitarDinero(500);
        assertEquals(9500, tienda.getPresupuesto());
    }

    /**
     * Verifica que getUsuario retorne la instancia de Usuario.
     */
    @Test
    @DisplayName("getUsuario retorna el usuario correcto")
    void testGetUsuario() {
        Usuario usuario = tienda.getUsuario();
        assertNotNull(usuario);
        assertEquals(10000, usuario.getDinero());
    }

    /**
     * Verifica que getEspaciosActivos retorne la lista de hábitats comprados.
     */
    @Test
    @DisplayName("getEspaciosActivos retorna la lista de hábitats")
    void testGetEspaciosActivos() {
        assertTrue(tienda.getEspaciosActivos().isEmpty());

        tienda.comprarHabitat(jaula, 200);
        assertEquals(1, tienda.getEspaciosActivos().size());
        assertTrue(tienda.getEspaciosActivos().contains(jaula));
    }

    /**
     * Verifica que comprarHabitat funcione correctamente cuando hay dinero suficiente.
     */
    @Test
    @DisplayName("comprarHabitat funciona con dinero suficiente")
    void testComprarHabitatExitoso() {
        int presupuestoInicial = tienda.getPresupuesto();

        boolean resultado = tienda.comprarHabitat(jaula, 200);

        assertTrue(resultado);
        assertEquals(presupuestoInicial - 200, tienda.getPresupuesto());
        assertEquals(1, tienda.getEspaciosActivos().size());
        assertTrue(tienda.getEspaciosActivos().contains(jaula));
    }

    /**
     * Verifica que comprarHabitat falle cuando no hay dinero suficiente.
     */
    @Test
    @DisplayName("comprarHabitat falla con dinero insuficiente")
    void testComprarHabitatDineroInsuficiente() {
        Tienda tiendaPobre = new Tienda(100);

        boolean resultado = tiendaPobre.comprarHabitat(jaula, 200);

        assertFalse(resultado);
        assertEquals(100, tiendaPobre.getPresupuesto());
        assertTrue(tiendaPobre.getEspaciosActivos().isEmpty());
    }

    /**
     * Verifica que comprarHabitat falle cuando se alcanza la capacidad máxima.
     */
    @Test
    @DisplayName("comprarHabitat falla al alcanzar capacidad máxima")
    void testComprarHabitatCapacidadMaxima() {
        for (int i = 0; i < Tienda.CAPACIDAD_MAXIMA; i++) {
            tienda.comprarHabitat(new Jaula(), 200);
        }

        boolean resultado = tienda.comprarHabitat(new Pecera(), 190);

        assertFalse(resultado);
        assertEquals(Tienda.CAPACIDAD_MAXIMA, tienda.getEspaciosActivos().size());
    }

    /**
     * Verifica que comprarAnimal funcione correctamente con un hábitat compatible y dinero suficiente.
     */
    @Test
    @DisplayName("comprarAnimal funciona con hábitat compatible y dinero suficiente")
    void testComprarAnimalExitoso() {
        tienda.comprarHabitat(jaula, 200);
        int presupuestoInicial = tienda.getPresupuesto();

        boolean resultado = tienda.comprarAnimal(ave, jaula, 700);

        assertTrue(resultado);
        assertEquals(presupuestoInicial - 700, tienda.getPresupuesto());
        assertFalse(jaula.estaVacio());
        assertEquals(ave, jaula.getResidente());
    }

    /**
     * Verifica que comprarAnimal falle si el hábitat no pertenece a la tienda.
     */
    @Test
    @DisplayName("comprarAnimal falla si el hábitat no pertenece a la tienda")
    void testComprarAnimalHabitatNoPertenece() {
        boolean resultado = tienda.comprarAnimal(ave, jaula, 700);

        assertFalse(resultado);
        assertTrue(jaula.estaVacio());
    }

    /**
     * Verifica que comprarAnimal falle si el hábitat ya está ocupado.
     */
    @Test
    @DisplayName("comprarAnimal falla si el hábitat está ocupado")
    void testComprarAnimalHabitatOcupado() {
        tienda.comprarHabitat(jaula, 200);
        tienda.comprarAnimal(ave, jaula, 700);

        boolean resultado = tienda.comprarAnimal(perro, jaula, 400);

        assertFalse(resultado);
        assertEquals(ave, jaula.getResidente());
    }

    /**
     * Verifica que comprarAnimal falle si el hábitat no es compatible con el animal.
     */
    @Test
    @DisplayName("comprarAnimal falla si el hábitat no es compatible con el animal")
    void testComprarAnimalHabitatIncompatible() {
        tienda.comprarHabitat(pecera, 190);
        int presupuestoInicial = tienda.getPresupuesto();

        boolean resultado = tienda.comprarAnimal(perro, pecera, 400);

        assertFalse(resultado);
        assertEquals(presupuestoInicial, tienda.getPresupuesto());
        assertTrue(pecera.estaVacio());
    }

    /**
     * Verifica que comprarAnimal falle si no hay dinero suficiente.
     */
    @Test
    @DisplayName("comprarAnimal falla con dinero insuficiente")
    void testComprarAnimalDineroInsuficiente() {
        Tienda tiendaPobre = new Tienda(100);
        tiendaPobre.comprarHabitat(jaula, 100);

        boolean resultado = tiendaPobre.comprarAnimal(ave, jaula, 700);

        assertFalse(resultado);
        assertTrue(jaula.estaVacio());
    }

    /**
     * Verifica que comprarMedicina funcione correctamente.
     */
    @Test
    @DisplayName("comprarMedicina funciona con dinero suficiente")
    void testComprarMedicinaExitoso() {
        int presupuestoInicial = tienda.getPresupuesto();
        int cantidadInicial = tienda.getUsuario().getCantItem(Item.MEDICINA.getIndex());

        boolean resultado = tienda.comprarMedicina();

        assertTrue(resultado);
        assertEquals(presupuestoInicial - 150, tienda.getPresupuesto());
        assertEquals(cantidadInicial + 1, tienda.getUsuario().getCantItem(Item.MEDICINA.getIndex()));
    }

    /**
     * Verifica que comprarMedicina falle si no hay dinero suficiente.
     */
    @Test
    @DisplayName("comprarMedicina falla con dinero insuficiente")
    void testComprarMedicinaDineroInsuficiente() {
        Tienda tiendaPobre = new Tienda(100);
        int cantidadInicial = tiendaPobre.getUsuario().getCantItem(Item.MEDICINA.getIndex());

        boolean resultado = tiendaPobre.comprarMedicina();

        assertFalse(resultado);
        assertEquals(100, tiendaPobre.getPresupuesto());
        assertEquals(cantidadInicial, tiendaPobre.getUsuario().getCantItem(Item.MEDICINA.getIndex()));
    }

    /**
     * Verifica que comprarComida funcione correctamente.
     */
    @Test
    @DisplayName("comprarComida funciona con dinero suficiente")
    void testComprarComidaExitoso() {
        int presupuestoInicial = tienda.getPresupuesto();
        int cantidadInicial = tienda.getUsuario().getCantItem(Item.COMIDA.getIndex());

        boolean resultado = tienda.comprarComida();

        assertTrue(resultado);
        assertEquals(presupuestoInicial - 200, tienda.getPresupuesto());
        assertEquals(cantidadInicial + 1, tienda.getUsuario().getCantItem(Item.COMIDA.getIndex()));
    }

    /**
     * Verifica que comprarComida falle si no hay dinero suficiente.
     */
    @Test
    @DisplayName("comprarComida falla con dinero insuficiente")
    void testComprarComidaDineroInsuficiente() {
        Tienda tiendaPobre = new Tienda(100);
        int cantidadInicial = tiendaPobre.getUsuario().getCantItem(Item.COMIDA.getIndex());

        boolean resultado = tiendaPobre.comprarComida();

        assertFalse(resultado);
        assertEquals(100, tiendaPobre.getPresupuesto());
        assertEquals(cantidadInicial, tiendaPobre.getUsuario().getCantItem(Item.COMIDA.getIndex()));
    }

    /**
     * Verifica que reembolso añada dinero correctamente al usuario.
     */
    @Test
    @DisplayName("reembolso añade dinero correctamente")
    void testReembolso() {
        int presupuestoInicial = tienda.getPresupuesto();

        tienda.reembolso(500);

        assertEquals(presupuestoInicial + 500, tienda.getPresupuesto());
    }

    /**
     * Verifica que getCompradoractual retorne null cuando no hay cliente.
     */
    @Test
    @DisplayName("getCompradoractual retorna null cuando no hay cliente")
    void testGetCompradoractualSinCliente() {
        assertNull(tienda.getCompradoractual());
    }

    /**
     * Verifica que setCompradoractual establezca el cliente correctamente.
     */
    @Test
    @DisplayName("setCompradoractual establece el cliente correctamente")
    void testSetCompradoractual() {
        Comprador cliente = new Comprador(1400);

        tienda.setCompradoractual(cliente);

        assertEquals(cliente, tienda.getCompradoractual());
    }

    /**
     * Verifica que venderMascotaACliente funcione correctamente.
     */
    @Test
    @DisplayName("venderMascotaACliente funciona correctamente")
    void testVenderMascotaAClienteExitoso() {
        tienda.comprarHabitat(jaula, 200);
        tienda.comprarAnimal(ave, jaula, 700);

        Comprador cliente = new Comprador(1400);
        // Forzar que el cliente quiera Ave
        // Nota: Como el tipo es aleatorio, si no coincide devuelve 0
        tienda.setCompradoractual(cliente);

        int presupuestoInicial = tienda.getPresupuesto();
        boolean resultado = tienda.venderMascotaACliente(jaula, cliente);

        if (cliente.getTipoMascotaDeseada().equals("Ave")) {
            assertTrue(resultado);
            assertTrue(jaula.estaVacio());
            assertTrue(tienda.getPresupuesto() > presupuestoInicial);
        } else {
            assertFalse(resultado);
            assertFalse(jaula.estaVacio());
            assertEquals(presupuestoInicial, tienda.getPresupuesto());
        }
    }

    /**
     * Verifica que venderMascotaACliente falle si el hábitat está vacío.
     */
    @Test
    @DisplayName("venderMascotaACliente falla si el hábitat está vacío")
    void testVenderMascotaAClienteHabitatVacio() {
        tienda.comprarHabitat(jaula, 200);
        Comprador cliente = new Comprador(1400);
        tienda.setCompradoractual(cliente);

        int presupuestoInicial = tienda.getPresupuesto();
        boolean resultado = tienda.venderMascotaACliente(jaula, cliente);

        assertFalse(resultado);
        assertEquals(presupuestoInicial, tienda.getPresupuesto());
    }

    /**
     * Verifica que venderMascotaACliente falle si el cliente no coincide con la especie.
     */
    @Test
    @DisplayName("venderMascotaACliente falla si la especie no coincide")
    void testVenderMascotaAClienteEspecieNoCoincide() {
        tienda.comprarHabitat(jaula, 200);
        tienda.comprarAnimal(ave, jaula, 700);

        Comprador cliente = new Comprador(1400);
        tienda.setCompradoractual(cliente);

        int presupuestoInicial = tienda.getPresupuesto();
        boolean resultado = tienda.venderMascotaACliente(jaula, cliente);

        if (!cliente.getTipoMascotaDeseada().equals("Ave")) {
            assertFalse(resultado);
            assertEquals(presupuestoInicial, tienda.getPresupuesto());
            assertFalse(jaula.estaVacio());
        }
    }

    /**
     * Verifica el flujo completo de compra de hábitat, compra de animal y venta.
     */
    @Test
    @DisplayName("Flujo completo de compra y venta funciona correctamente")
    void testFlujoCompletoCompraYVenta() {
        // 1. Comprar hábitat
        assertTrue(tienda.comprarHabitat(jaula, 200));
        assertEquals(9800, tienda.getPresupuesto());

        // 2. Comprar animal
        assertTrue(tienda.comprarAnimal(ave, jaula, 700));
        assertEquals(9100, tienda.getPresupuesto());
        assertFalse(jaula.estaVacio());
        assertEquals(ave, jaula.getResidente());

        // 3. Crear cliente y vender
        Comprador cliente = new Comprador(1400);
        tienda.setCompradoractual(cliente);

        if (cliente.getTipoMascotaDeseada().equals("Ave")) {
            int presupuestoAntesVenta = tienda.getPresupuesto();
            assertTrue(tienda.venderMascotaACliente(jaula, cliente));
            assertTrue(tienda.getPresupuesto() > presupuestoAntesVenta);
            assertTrue(jaula.estaVacio());
        }
    }
}