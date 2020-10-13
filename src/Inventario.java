public class Inventario {
    protected Item[] inventario = new Item[13];

    public Inventario() {

    }

    public void ingresarItem(Item item, int indice) {
        inventario[indice] = item;
    }

    public void borrarItem(int indice) {
        inventario[indice] = null;
    }

    public  Item getItem(int Indice) {
        return inventario[Indice];
    }
}
