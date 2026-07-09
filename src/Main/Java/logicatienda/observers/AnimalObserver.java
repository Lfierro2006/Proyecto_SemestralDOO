package logicatienda.observers;

import logicatienda.animales.Animal;

/**
 * Interfaz para el patrón Observer.
 * Permite que los objetos sean notificados cuando un animal cambia.
 */

public interface AnimalObserver {

    /**
     * Notifica que las estadísticas del animal han cambiado.
     * @param animal El animal que ha cambiado
     */
    void onEstadisticasCambiadas(Animal animal);
}
