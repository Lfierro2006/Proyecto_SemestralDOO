package logicagrafica.handler;

import logicatienda.habitat.*;
import logicagrafica.CasillaMascota;
import logicagrafica.menu.MenuManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Maneja los eventos de mouse en la casilla.
 * Separa la lógica de eventos del resto de la casilla.
 */
public class CasillaMouseHandler extends MouseAdapter {

    private CasillaMascota casilla;
    private MenuManager menuManager;

    /**
     * Constructor del manejador de eventos.
     *
     * @param casilla La casilla a manejar
     * @param menuManager El gestor de menús
     */
    public CasillaMouseHandler(CasillaMascota casilla, MenuManager menuManager) {
        this.casilla = casilla;
        this.menuManager = menuManager;
    }

    /**
     * Maneja el evento de presión del mouse.
     *
     * @param e Evento del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ejecutarAccion(e);
    }

    /**
     * Ejecuta la acción correspondiente según el tipo de clic.
     *
     * @param e Evento del mouse
     */
    public void ejecutarAccion(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            manejarClicDerecho();
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)) {
            manejarClicIzquierdo();
        }
    }

    /**
     * Maneja el clic derecho del mouse.
     */
    private void manejarClicDerecho() {
        if (menuManager.isMenuVisible()) {
            menuManager.ocultarTodosLosBotones();
            return;
        }

        if (casilla.getHabitat() != null && casilla.getHabitat().estaVacio()) {
            casilla.desmantelarHabitat();
            return;
        }

        if (casilla.tieneAnimal()) {
            menuManager.mostrarMenuVender();
        }
    }

    /**
     * Maneja el clic izquierdo del mouse.
     */
    private void manejarClicIzquierdo() {
        if (casilla.noTieneHabitat()) {
            menuManager.mostrarMenuHabitat();
            return;
        }

        if (casilla.getHabitat().estaVacio()) {
            menuManager.mostrarMenuAñadirAnimal();
            return;
        }

        if (casilla.tieneAnimal()) {
            menuManager.mostrarMenuAnimal();
        }
    }
}