package logicatienda.usuario;

/**
 * Enum que define los tipos de ítems que puede tener el jugador en su inventario.
 * Cada ítem tiene un índice asociado para facilitar el acceso al inventario.
 */
public enum Item {
    COMIDA(0),
    MEDICINA(1),
    PERRO(2),
    GATO(3),
    AVE(4),
    PEZ(5);
    private final int indice;

    /**
     * Constructor de ítem.
     * @param indice Índice del ítem en el inventario
     */x
    private Item(int indice){
        this.indice=indice;
    }

    /**
     * Obtiene el índice del item en el inventario.
     * @return El índice numérico
     */
    public int getIndex() {
        return indice;
    }
}
