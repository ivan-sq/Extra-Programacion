import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarritoCompra extends Pedido {
    private int idCarrito;
    private List<Integer> idProductos;
    private List<Integer> cantidadProductos;
    private double precioTotal;

    public CarritoCompra(int idCarrito, int idPedido, int idUsuario, String nombreUsuario, Date fechaPedido) {
        super(idPedido, idUsuario, nombreUsuario, fechaPedido, nombreUsuario);
        this.idCarrito = idCarrito;
        this.idProductos = new ArrayList<>();
        this.cantidadProductos = new ArrayList<>();
        this.precioTotal = 0.0;
    }

    public void agregarProducto(int idProducto, int cantidad, double precioProducto) {
        idProductos.add(idProducto);
        cantidadProductos.add(cantidad);
        precioTotal += cantidad * precioProducto;
    }

    public void eliminarProducto(int idProducto, int cantidad, double precioProducto) {
        int index = idProductos.indexOf(idProducto);
        if (index != -1 && cantidadProductos.get(index) >= cantidad) {
            cantidadProductos.set(index, cantidadProductos.get(index) - cantidad);
            precioTotal -= cantidad * precioProducto;
            if (cantidadProductos.get(index) == 0) {
                idProductos.remove(index);
                cantidadProductos.remove(index);
            }
        }
    }

    public void actualizarCantidad(int idProducto, int nuevaCantidad, double precioProducto) {
        int index = idProductos.indexOf(idProducto);
        if (index != -1) {
            int cantidadAnterior = cantidadProductos.get(index);
            cantidadProductos.set(index, nuevaCantidad);
            precioTotal += (nuevaCantidad - cantidadAnterior) * precioProducto;
        }
    }

    public void verDetallesCarrito() {
        System.out.println("ID Carrito: " + idCarrito);
        System.out.println("ID Pedido: " + getIdPedido());
        System.out.println("ID Usuario: " + getIdUsuario());
        System.out.println("Nombre Usuario: " + getNombreUsuario());
        System.out.println("Productos en el carrito:");
        for (int i = 0; i < idProductos.size(); i++) {
            System.out.println("ID Producto: " + idProductos.get(i) + " - Cantidad: " + cantidadProductos.get(i));
        }
        System.out.println("Precio Total: $" + precioTotal);
    }
}
