package logicatienda.animales;

import logicatienda.animales.estados.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase abstracta Animal.
 * Verifica el correcto funcionamiento de todas las estadísticas, estados
 * y métodos de interacción (Alimentar, Jugar, Limpiar, Curar).
 */
class AnimalTest {

    private Animal perro;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea un perro con nombre "Rex" en estado inicial (todas las estadísticas en 85).
     */
    @BeforeEach
    void setUp() {
        perro = new Perro("Rex");
    }

    /**
     * Verifica que el constructor inicialice todas las estadísticas en 85
     * y que el nombre del animal sea el correcto.
     */
    @Test
    @DisplayName("El animal se inicializa con todas las estadísticas en 85")
    void testConstructorInicializaEstadisticasCorrectamente() {
        assertEquals(85, perro.getNivel(Estadistica.FELICIDAD));
        assertEquals(85, perro.getNivel(Estadistica.SACIEDAD));
        assertEquals(85, perro.getNivel(Estadistica.HIGIENE));
        assertEquals(85, perro.getNivel(Estadistica.SALUD));
        assertEquals("Rex", perro.getNombre());
    }

    /**
     * Verifica que el animal comience con todos los estados positivos
     * y ninguno de los estados negativos.
     */
    @Test
    @DisplayName("El animal inicia con todos los estados positivos")
    void testConstructorInicializaEstadosPositivos() {
        assertTrue(perro.tieneEstado(Tipo.FELIZ));
        assertTrue(perro.tieneEstado(Tipo.SACIADO));
        assertTrue(perro.tieneEstado(Tipo.LIMPIO));
        assertTrue(perro.tieneEstado(Tipo.SANO));
        assertFalse(perro.tieneEstado(Tipo.TRISTE));
        assertFalse(perro.tieneEstado(Tipo.HAMBRIENTO));
        assertFalse(perro.tieneEstado(Tipo.SUCIO));
        assertFalse(perro.tieneEstado(Tipo.ENFERMO));
    }

    /**
     * Verifica que al disminuir una estadística por debajo de 0,
     * el valor se mantenga en 0 (no puede ser negativo).
     */
    @Test
    @DisplayName("No se puede bajar una estadística por debajo de 0")
    void testDisminuirNivelNoBajaDeCero() {
        perro.disminuirNivel(Estadistica.FELICIDAD, 200);
        assertEquals(0, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que al aumentar una estadística por encima de 100,
     * el valor se mantenga en 100 (no puede superar el máximo).
     */
    @Test
    @DisplayName("No se puede subir una estadística por encima de 100")
    void testAumentarNivelNoSubeDeCien() {
        perro.aumentarNivel(Estadistica.FELICIDAD, 200);
        assertEquals(100, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que el método Alimentar solo funciona cuando el animal
     * está en estado HAMBRIENTO, aumentando la saciedad y la felicidad.
     */
    @Test
    @DisplayName("Alimentar funciona solo cuando el animal está hambriento")
    void testAlimentarSoloCuandoHambriento() {
        perro.disminuirNivel(Estadistica.SACIEDAD, 30);
        assertTrue(perro.tieneEstado(Tipo.HAMBRIENTO));
        int saciedadInicial = perro.getNivel(Estadistica.SACIEDAD);

        perro.Alimentar();

        assertTrue(perro.getNivel(Estadistica.SACIEDAD) > saciedadInicial);
        assertTrue(perro.getNivel(Estadistica.FELICIDAD) > 85);
    }

    /**
     * Verifica que el método Alimentar no tenga efecto si el animal
     * no está en estado HAMBRIENTO.
     */
    @Test
    @DisplayName("Alimentar no hace nada si el animal no está hambriento")
    void testAlimentarNoHaceNadaSiNoEstaHambriento() {
        assertFalse(perro.tieneEstado(Tipo.HAMBRIENTO));
        int saciedadInicial = perro.getNivel(Estadistica.SACIEDAD);

        perro.Alimentar();

        assertEquals(saciedadInicial, perro.getNivel(Estadistica.SACIEDAD));
    }

    /**
     * Verifica que el método Jugar solo funciona cuando el animal
     * no está enfermo ni hambriento, aumentando la felicidad y
     * disminuyendo la saciedad e higiene.
     */
    @Test
    @DisplayName("Jugar funciona solo cuando no está enfermo ni hambriento")
    void testJugarSoloCuandoNoEnfermoNiHambriento() {
        assertFalse(perro.tieneEstado(Tipo.ENFERMO));
        assertFalse(perro.tieneEstado(Tipo.HAMBRIENTO));
        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);

        perro.Jugar();

        assertTrue(perro.getNivel(Estadistica.FELICIDAD) > felicidadInicial);
        assertEquals(75, perro.getNivel(Estadistica.SACIEDAD));
        assertEquals(70, perro.getNivel(Estadistica.HIGIENE));
    }

    /**
     * Verifica que el método Jugar no tenga efecto si el animal
     * está en estado ENFERMO.
     */
    @Test
    @DisplayName("Jugar no funciona si el animal está enfermo")
    void testJugarNoFuncionaSiEnfermo() {
        perro.disminuirNivel(Estadistica.SALUD, 30);
        assertTrue(perro.tieneEstado(Tipo.ENFERMO));
        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);

        perro.Jugar();

        assertEquals(felicidadInicial, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que el método Jugar no tenga efecto si el animal
     * está en estado HAMBRIENTO.
     */
    @Test
    @DisplayName("Jugar no funciona si el animal está hambriento")
    void testJugarNoFuncionaSiHambriento() {
        perro.disminuirNivel(Estadistica.SACIEDAD, 30);
        assertTrue(perro.tieneEstado(Tipo.HAMBRIENTO));
        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);

        perro.Jugar();

        assertEquals(felicidadInicial, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que el método Limpiar solo funciona cuando el animal
     * está en estado SUCIO, aumentando la higiene y la salud.
     */
    @Test
    @DisplayName("Limpiar funciona solo cuando el animal está sucio")
    void testLimpiarSoloCuandoSucio() {
        perro.disminuirNivel(Estadistica.HIGIENE, 35);
        assertTrue(perro.tieneEstado(Tipo.SUCIO));
        int higieneInicial = perro.getNivel(Estadistica.HIGIENE);

        perro.Limpiar();

        assertTrue(perro.getNivel(Estadistica.HIGIENE) > higieneInicial);
        assertEquals(95, perro.getNivel(Estadistica.SALUD));
    }

    /**
     * Verifica que el método Limpiar no tenga efecto si el animal
     * no está en estado SUCIO.
     */
    @Test
    @DisplayName("Limpiar no hace nada si el animal no está sucio")
    void testLimpiarNoHaceNadaSiNoEstaSucio() {
        assertFalse(perro.tieneEstado(Tipo.SUCIO));
        int higieneInicial = perro.getNivel(Estadistica.HIGIENE);

        perro.Limpiar();

        assertEquals(higieneInicial, perro.getNivel(Estadistica.HIGIENE));
    }

    /**
     * Verifica que el método Curar solo funciona cuando el animal
     * está en estado ENFERMO, aumentando la salud y disminuyendo la felicidad.
     */
    @Test
    @DisplayName("Curar funciona solo cuando el animal está enfermo")
    void testCurarSoloCuandoEnfermo() {
        perro.disminuirNivel(Estadistica.SALUD, 30);
        assertTrue(perro.tieneEstado(Tipo.ENFERMO));
        int saludInicial = perro.getNivel(Estadistica.SALUD);

        perro.Curar(40);

        assertTrue(perro.getNivel(Estadistica.SALUD) > saludInicial);
        assertEquals(80, perro.getNivel(Estadistica.FELICIDAD));
    }

    /**
     * Verifica que el método Curar no tenga efecto si el animal
     * no está en estado ENFERMO.
     */
    @Test
    @DisplayName("Curar no hace nada si el animal no está enfermo")
    void testCurarNoHaceNadaSiNoEstaEnfermo() {
        assertFalse(perro.tieneEstado(Tipo.ENFERMO));
        int saludInicial = perro.getNivel(Estadistica.SALUD);

        perro.Curar(40);

        assertEquals(saludInicial, perro.getNivel(Estadistica.SALUD));
    }

    /**
     * Verifica que el método ejecutarEstado aplique la degradación
     * correspondiente a cada estado actual del animal.
     */
    @Test
    @DisplayName("ejecutarEstado aplica la degradación de todos los estados actuales")
    void testEjecutarEstadoAplicaDegradacion() {
        perro.disminuirNivel(Estadistica.FELICIDAD, 30);
        perro.disminuirNivel(Estadistica.SACIEDAD, 30);
        perro.disminuirNivel(Estadistica.HIGIENE, 30);
        perro.disminuirNivel(Estadistica.SALUD, 30);

        int felicidadInicial = perro.getNivel(Estadistica.FELICIDAD);
        int saciedadInicial = perro.getNivel(Estadistica.SACIEDAD);
        int higieneInicial = perro.getNivel(Estadistica.HIGIENE);
        int saludInicial = perro.getNivel(Estadistica.SALUD);

        perro.ejecutarEstado();

        assertTrue(perro.getNivel(Estadistica.FELICIDAD) < felicidadInicial);
        assertTrue(perro.getNivel(Estadistica.SACIEDAD) < saciedadInicial);
        assertTrue(perro.getNivel(Estadistica.HIGIENE) < higieneInicial);
        assertTrue(perro.getNivel(Estadistica.SALUD) < saludInicial);
    }

    /**
     * Verifica que el método getTodosLosEstados retorne un array de booleanos
     * en el orden correcto: [FELIZ, SACIADO, LIMPIO, SANO].
     */
    @Test
    @DisplayName("getTodosLosEstados retorna el array correcto en orden")
    void testGetTodosLosEstadosRetornaArrayCorrecto() {
        perro.disminuirNivel(Estadistica.FELICIDAD, 35);
        perro.disminuirNivel(Estadistica.SACIEDAD, 35);
        perro.disminuirNivel(Estadistica.HIGIENE, 35);
        perro.disminuirNivel(Estadistica.SALUD, 35);

        boolean[] estados = perro.getTodosLosEstados();

        assertFalse(estados[0]);
        assertFalse(estados[1]);
        assertFalse(estados[2]);
        assertFalse(estados[3]);
    }

    /**
     * Verifica que getTodosLosEstados retorne todos true cuando el animal
     * está en estado óptimo (todas las estadísticas en 85).
     */
    @Test
    @DisplayName("getTodosLosEstados retorna todos true cuando el animal está bien")
    void testGetTodosLosEstadosRetornaTodosTrueCuandoBien() {
        boolean[] estados = perro.getTodosLosEstados();

        assertTrue(estados[0]);
        assertTrue(estados[1]);
        assertTrue(estados[2]);
        assertTrue(estados[3]);
    }

    /**
     * Verifica que los observers sean notificados cuando las estadísticas
     * del animal cambian.
     */
    @Test
    @DisplayName("Observer es notificado cuando cambian las estadísticas")
    void testObserverEsNotificadoCuandoCambianEstadisticas() {
        TestObserver observer = new TestObserver();
        perro.addObserver(observer);

        perro.aumentarNivel(Estadistica.FELICIDAD, 10);

        assertTrue(observer.fueNotificado);
    }

    /**
     * Clase helper para probar el patrón Observer.
     */
    private static class TestObserver implements logicatienda.observers.AnimalObserver {
        boolean fueNotificado = false;

        @Override
        public void onEstadisticasCambiadas(Animal animal) {
            fueNotificado = true;
        }
    }
}