public class Item {
    protected int precioVenta;
    protected int precioCompra;
    protected String nombre;

    public Item(String nombreItem) {
        nombre = nombreItem;
    }
    
    public void setPrecioVenta(int valorPrecioVenta) {
        precioVenta = valorPrecioVenta;
    }

    public void setPrecioCompra(int valorPrecioCompra) {
        precioCompra = valorPrecioCompra;
    }

    public void setNombre(String nombreItem) {
        nombre = nombreItem;
    }
}
