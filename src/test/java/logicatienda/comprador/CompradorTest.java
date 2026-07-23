package logicatienda.comprador;

import logicatienda.animales.*;
import logicatienda.animales.estados.Tipo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Comprador.
 * Verifica la correcta generación de tipos de mascotas deseadas
 * y el cálculo de precios finales basado en el estado del animal.
 */
class CompradorTest {

    /**
     * Verifica que el constructor genere un tipo de mascota deseada válido
     * entre las opciones: Perro, Gato, Ave o Pez.
     */
    @Test
    @DisplayName("Comprador se inicializa con un tipo de mascota deseada válido")
    void testConstructorGeneraTipoValido() {
        Comprador comprador = new Comprador(1400);
        String tipo = comprador.getTipoMascotaDeseada();
        assertTrue(tipo.equals("Perro") ||
                tipo.equals("Gato") ||
                tipo.equals("Ave") ||
                tipo.equals("Pez"));
    }

    /**
     * Verifica que el constructor genere diferentes tipos de mascotas
     * de forma aleatoria cuando se ejecuta múltiples veces.
     * Se ejecuta 10 veces para aumentar la probabilidad de detectar
     * diferentes tipos.
     */
    @RepeatedTest(10)
    @DisplayName("Comprador genera tipos aleatorios correctamente")
    void testConstructorGeneraTiposAleatorios() {
        Comprador comprador = new Comprador(1400);
        String tipo = comprador.getTipoMascotaDeseada();
        assertNotNull(tipo);
        assertFalse(tipo.isEmpty());
    }

    /**
     * Verifica que getTipoMascotaDeseada retorne el mismo tipo
     * que fue generado en el constructor.
     */
    @Test
    @DisplayName("getTipoMascotaDeseada retorna el tipo correcto")
    void testGetTipoMascotaDeseada() {
        Comprador comprador = new Comprador(1400);
        String tipo = comprador.getTipoMascotaDeseada();
        assertEquals(tipo, comprador.getTipoMascotaDeseada());
    }

    /**
     * Verifica que calcularPrecioFinal retorne 0 cuando la especie
     * del animal no coincide con la especie deseada por el comprador.
     */
    @Test
    @DisplayName("calcularPrecioFinal retorna 0 si la especie no coincide")
    void testCalcularPrecioFinalEspecieNoCoincide() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        int precio = comprador.calcularPrecioFinal(perro);

        if (!comprador.getTipoMascotaDeseada().equals("Perro")) {
            assertEquals(0, precio);
        } else {
            assertTrue(precio >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique penalizaciones por
     * estados adversos del animal (TRISTE, HAMBRIENTO, SUCIO, ENFERMO).
     * Cada estado adverso penaliza con $50.
     */
    @Test
    @DisplayName("calcularPrecioFinal penaliza estados adversos")
    void testCalcularPrecioFinalPenalizaEstadosAdversos() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            int precioSano = comprador.calcularPrecioFinal(perro);

            perro.disminuirNivel(Estadistica.FELICIDAD, 30);
            perro.disminuirNivel(Estadistica.SACIEDAD, 30);
            int precioEnfermo = comprador.calcularPrecioFinal(perro);

            assertTrue(precioEnfermo < precioSano);
        }
    }

    /**
     * Verifica que calcularPrecioFinal nunca devuelva un valor
     * inferior a PRECIO_MINIMO (500), incluso cuando el animal
     * está en pésimas condiciones.
     */
    @Test
    @DisplayName("calcularPrecioFinal nunca baja de PRECIO_MINIMO (500)")
    void testCalcularPrecioFinalNuncaBajaDeMinimo() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            perro.disminuirNivel(Estadistica.FELICIDAD, 100);
            perro.disminuirNivel(Estadistica.SACIEDAD, 100);
            perro.disminuirNivel(Estadistica.HIGIENE, 100);
            perro.disminuirNivel(Estadistica.SALUD, 100);

            int precio = comprador.calcularPrecioFinal(perro);
            assertTrue(precio >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aumente el precio cuando el animal
     * está por encima de los umbrales mínimos (mejor cuidado).
     * Un animal perfecto (85 en todo) vale más que el precio base.
     */
    @Test
    @DisplayName("calcularPrecioFinal aumenta el precio cuando el animal está perfecto")
    void testCalcularPrecioFinalAnimalPerfecto() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            int precio = comprador.calcularPrecioFinal(perro);

            // 85 en todas las estadísticas → penalización variable negativa
            // pFelicidad = (60 - 85) / 2 = -12
            // pSaciedad = (60 - 85) / 2 = -12
            // pHigiene = (55 - 85) / 2 = -15
            // pSalud = (65 - 85) / 2 = -10
            // Total penalización variable = -49
            // precioCalculado = 1400 - (-49) = 1449
            assertEquals(1449, precio);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique la penalización correcta
     * cuando el animal tiene el estado ENFERMO.
     * Penalización: -$50 por estado + penalización variable
     */
    @Test
    @DisplayName("calcularPrecioFinal penaliza correctamente el estado ENFERMO")
    void testCalcularPrecioFinalPenalizaEnfermo() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            // Animal perfecto: precio = 1449
            int precioPerfecto = comprador.calcularPrecioFinal(perro);

            // Bajar salud a 55 (por debajo de 65)
            perro.disminuirNivel(Estadistica.SALUD, 30);
            assertTrue(perro.tieneEstado(Tipo.ENFERMO));

            int precioConEnfermo = comprador.calcularPrecioFinal(perro);

            // Debe ser menor que el precio perfecto
            assertTrue(precioConEnfermo < precioPerfecto);
            // No debe bajar del mínimo
            assertTrue(precioConEnfermo >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique la penalización correcta
     * cuando el animal tiene el estado HAMBRIENTO.
     * Penalización: -$50 por estado + penalización variable
     */
    @Test
    @DisplayName("calcularPrecioFinal penaliza correctamente el estado HAMBRIENTO")
    void testCalcularPrecioFinalPenalizaHambriento() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            int precioPerfecto = comprador.calcularPrecioFinal(perro);

            perro.disminuirNivel(Estadistica.SACIEDAD, 30);
            assertTrue(perro.tieneEstado(Tipo.HAMBRIENTO));

            int precioConHambriento = comprador.calcularPrecioFinal(perro);

            assertTrue(precioConHambriento < precioPerfecto);
            assertTrue(precioConHambriento >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique la penalización correcta
     * cuando el animal tiene el estado SUCIO.
     * Penalización: -$50 por estado + penalización variable
     */
    @Test
    @DisplayName("calcularPrecioFinal penaliza correctamente el estado SUCIO")
    void testCalcularPrecioFinalPenalizaSucio() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            int precioPerfecto = comprador.calcularPrecioFinal(perro);

            perro.disminuirNivel(Estadistica.HIGIENE, 35);
            assertTrue(perro.tieneEstado(Tipo.SUCIO));

            int precioConSucio = comprador.calcularPrecioFinal(perro);

            assertTrue(precioConSucio < precioPerfecto);
            assertTrue(precioConSucio >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique la penalización correcta
     * cuando el animal tiene el estado TRISTE.
     * Penalización: -$50 por estado + penalización variable
     */
    @Test
    @DisplayName("calcularPrecioFinal penaliza correctamente el estado TRISTE")
    void testCalcularPrecioFinalPenalizaTriste() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            int precioPerfecto = comprador.calcularPrecioFinal(perro);

            perro.disminuirNivel(Estadistica.FELICIDAD, 30);
            assertTrue(perro.tieneEstado(Tipo.TRISTE));

            int precioConTriste = comprador.calcularPrecioFinal(perro);

            assertTrue(precioConTriste < precioPerfecto);
            assertTrue(precioConTriste >= 500);
        }
    }

    /**
     * Verifica que calcularPrecioFinal aplique penalizaciones variables
     * basadas en la diferencia entre el nivel actual y el mínimo requerido.
     * Cuando el nivel está por DEBAJO del mínimo, penaliza.
     * Cuando el nivel está por ENCIMA del mínimo, bonifica (aumenta el precio).
     */
    @Test
    @DisplayName("calcularPrecioFinal aplica penalización variable correctamente")
    void testCalcularPrecioFinalPenalizacionVariable() {
        Comprador comprador = new Comprador(1400);
        Perro perro = new Perro("Rex");

        if (comprador.getTipoMascotaDeseada().equals("Perro")) {
            // Animal perfecto: precio = 1449
            int precioPerfecto = comprador.calcularPrecioFinal(perro);

            // Bajar felicidad a 70 (aún por encima del mínimo de 60)
            perro.disminuirNivel(Estadistica.FELICIDAD, 15);

            // Ahora felicidad = 70, pFelicidad = (60 - 70) / 2 = -5
            // El precio debería ser 1444 (1449 - 5)
            int precio = comprador.calcularPrecioFinal(perro);
            assertEquals(1444, precio);
        }
    }

    /**
     * Verifica que calcularPrecioFinal maneje correctamente animales
     * de diferentes especies que coinciden con la preferencia del comprador.
     */
    @Test
    @DisplayName("calcularPrecioFinal funciona para todas las especies")
    void testCalcularPrecioFinalParaTodasLasEspecies() {
        Comprador comprador = new Comprador(1400);
        String tipoDeseado = comprador.getTipoMascotaDeseada();

        Animal animal = null;
        switch (tipoDeseado) {
            case "Perro":
                animal = new Perro("Rex");
                break;
            case "Gato":
                animal = new Gato("Michi");
                break;
            case "Ave":
                animal = new Ave("Piolin");
                break;
            case "Pez":
                animal = new Pez("Nemo");
                break;
        }

        if (animal != null) {
            int precio = comprador.calcularPrecioFinal(animal);
            // El precio debe ser mayor que el precio base por estar perfecto
            assertTrue(precio > 1400);
        }
    }
}