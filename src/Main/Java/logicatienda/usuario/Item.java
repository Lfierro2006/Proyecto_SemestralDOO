package logicatienda.usuario;

public enum Item {
    COMIDA(0),
    MEDICINA(1),
    PERRO(2),
    GATO(3),
    AVE(4),
    PEZ(5);
    private final int indice;
    /**
     * Obtiene el índice del item en el inventario.
     * @return El índice numérico
     */
    private Item(int indice){
        this.indice=indice;
    }
    public int getIndex() {
        return indice;
    }
}
