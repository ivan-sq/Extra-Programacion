public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int cantidadStock;
    private double precio;

    public Producto(int idProducto, String nombreProducto, String ingredienteActivo, int cantidadStock, double precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadStock = cantidadStock;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void actualizarStock(int cantidad) {
        cantidadStock += cantidad;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }
}

