package logicatienda.habitat;

import logicatienda.animales.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase abstracta Habitat y sus implementaciones.
 * Verifica la correcta gestión de animales en los hábitats,
 * incluyendo alojar, liberar y la compatibilidad con diferentes tipos de animales.
 */
class HabitatTest {

    private Habitat jaula;
    private Habitat cama;
    private Habitat pecera;
    private Animal perro;
    private Animal gato;
    private Animal ave;
    private Animal pez;

    /**
     * Configuración inicial antes de cada prueba.
     * Crea instancias de todos los hábitats y animales.
     */
    @BeforeEach
    void setUp() {
        jaula = new Jaula();
        cama = new Cama();
        pecera = new Pecera();
        perro = new Perro("Rex");
        gato = new Gato("Michi");
        ave = new Ave("Piolin");
        pez = new Pez("Nemo");
    }

    /**
     * Verifica que un hábitat recién creado esté vacío.
     */
    @Test
    @DisplayName("Un hábitat recién creado está vacío")
    void testHabitatRecienCreadoEstaVacio() {
        assertTrue(jaula.estaVacio());
        assertTrue(cama.estaVacio());
        assertTrue(pecera.estaVacio());
        assertNull(jaula.getResidente());
        assertNull(cama.getResidente());
        assertNull(pecera.getResidente());
    }

    /**
     * Verifica que alojarAnimal funcione correctamente cuando el hábitat está vacío.
     */
    @Test
    @DisplayName("alojarAnimal funciona cuando el hábitat está vacío")
    void testAlojarAnimalCuandoEstaVacio() {
        assertTrue(jaula.alojarAnimal(ave));
        assertFalse(jaula.estaVacio());
        assertEquals(ave, jaula.getResidente());
    }

    /**
     * Verifica que alojarAnimal falle cuando el hábitat ya está ocupado.
     */
    @Test
    @DisplayName("alojarAnimal falla cuando el hábitat está ocupado")
    void testAlojarAnimalCuandoOcupado() {
        jaula.alojarAnimal(ave);
        assertFalse(jaula.alojarAnimal(perro));
        assertEquals(ave, jaula.getResidente());
    }

    /**
     * Verifica que liberarHabitat deje el hábitat vacío.
     */
    @Test
    @DisplayName("liberarHabitat deja el hábitat vacío")
    void testLiberarHabitat() {
        jaula.alojarAnimal(ave);
        assertFalse(jaula.estaVacio());

        jaula.liberarHabitat();
        assertTrue(jaula.estaVacio());
        assertNull(jaula.getResidente());
    }

    /**
     * Verifica que getResidente retorne el animal correcto.
     */
    @Test
    @DisplayName("getResidente retorna el animal correcto")
    void testGetResidente() {
        jaula.alojarAnimal(ave);
        assertEquals(ave, jaula.getResidente());

        cama.alojarAnimal(perro);
        assertEquals(perro, cama.getResidente());

        pecera.alojarAnimal(pez);
        assertEquals(pez, pecera.getResidente());
    }

    /**
     * Verifica que estaVacio retorne true después de liberar el hábitat.
     */
    @Test
    @DisplayName("estaVacio retorna true después de liberar")
    void testEstaVacioDespuesDeLiberar() {
        jaula.alojarAnimal(ave);
        assertFalse(jaula.estaVacio());

        jaula.liberarHabitat();
        assertTrue(jaula.estaVacio());
    }

    /**
     * Verifica que Jaula solo acepte animales Aereos (Aves).
     */
    @Test
    @DisplayName("Jaula solo acepta animales Aereos")
    void testJaulaSoloAceptaAereos() {
        assertTrue(jaula.esCompatible(ave));
        assertFalse(jaula.esCompatible(perro));
        assertFalse(jaula.esCompatible(gato));
        assertFalse(jaula.esCompatible(pez));
    }

    /**
     * Verifica que Jaula aloje correctamente animales Aereos.
     */
    @Test
    @DisplayName("Jaula aloja correctamente animales Aereos")
    void testJaulaAlojaAereos() {
        assertTrue(jaula.alojarAnimal(ave));
        assertEquals(ave, jaula.getResidente());

        assertFalse(jaula.alojarAnimal(perro));
        assertEquals(ave, jaula.getResidente());
    }

    /**
     * Verifica que Cama solo acepte animales Terrestres (Perros y Gatos).
     */
    @Test
    @DisplayName("Cama solo acepta animales Terrestres")
    void testCamaSoloAceptaTerrestres() {
        assertTrue(cama.esCompatible(perro));
        assertTrue(cama.esCompatible(gato));
        assertFalse(cama.esCompatible(ave));
        assertFalse(cama.esCompatible(pez));
    }

    /**
     * Verifica que Cama aloje correctamente animales Terrestres.
     */
    @Test
    @DisplayName("Cama aloja correctamente animales Terrestres")
    void testCamaAlojaTerrestres() {
        assertTrue(cama.alojarAnimal(perro));
        assertEquals(perro, cama.getResidente());

        cama.liberarHabitat();

        assertTrue(cama.alojarAnimal(gato));
        assertEquals(gato, cama.getResidente());

        assertFalse(cama.alojarAnimal(ave));
        assertEquals(gato, cama.getResidente());
    }

    /**
     * Verifica que Pecera solo acepte animales Acuaticos (Peces).
     */
    @Test
    @DisplayName("Pecera solo acepta animales Acuaticos")
    void testPeceraSoloAceptaAcuaticos() {
        assertTrue(pecera.esCompatible(pez));
        assertFalse(pecera.esCompatible(perro));
        assertFalse(pecera.esCompatible(gato));
        assertFalse(pecera.esCompatible(ave));
    }

    /**
     * Verifica que Pecera aloje correctamente animales Acuaticos.
     */
    @Test
    @DisplayName("Pecera aloja correctamente animales Acuaticos")
    void testPeceraAlojaAcuaticos() {
        assertTrue(pecera.alojarAnimal(pez));
        assertEquals(pez, pecera.getResidente());

        assertFalse(pecera.alojarAnimal(perro));
        assertEquals(pez, pecera.getResidente());
    }

    /**
     * Verifica que alojarAnimal con animal incompatible falle y mantenga el hábitat vacío.
     */
    @Test
    @DisplayName("alojarAnimal con animal incompatible falla y mantiene el hábitat vacío")
    void testAlojarAnimalIncompatibleMantieneVacio() {
        assertFalse(jaula.alojarAnimal(perro));
        assertTrue(jaula.estaVacio());
        assertNull(jaula.getResidente());

        assertFalse(cama.alojarAnimal(pez));
        assertTrue(cama.estaVacio());
        assertNull(cama.getResidente());

        assertFalse(pecera.alojarAnimal(ave));
        assertTrue(pecera.estaVacio());
        assertNull(pecera.getResidente());
    }

    /**
     * Verifica que los hábitats mantengan su integridad después de múltiples operaciones.
     */
    @Test
    @DisplayName("Los hábitats mantienen integridad después de múltiples operaciones")
    void testIntegridadDespuesMultiplesOperaciones() {
        assertTrue(jaula.alojarAnimal(ave));
        assertEquals(ave, jaula.getResidente());
        assertFalse(jaula.alojarAnimal(perro));
        assertEquals(ave, jaula.getResidente());

        jaula.liberarHabitat();
        assertTrue(jaula.estaVacio());
        assertNull(jaula.getResidente());
    }
}