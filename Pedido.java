import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends Usuario {
    private int idPedido;
    private List<Producto> productos;
    private Date fechaPedido;
    private String estatus;

    public Pedido(int idPedido, int idUsuario, String nombreUsuario, Date fechaPedido, String estatus) {
        super(idUsuario, nombreUsuario);
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estatus = estatus;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public void imprimirInformacion() {
        System.out.println("ID Usuario: " + getIdUsuario());
        System.out.println("Nombre Usuario: " + getNombreUsuario());
        System.out.println("ID Pedido: " + getIdPedido());
        System.out.println("Fecha de Pedido: " + getFechaPedido());
        System.out.println("Estatus: " + getEstatus());
        System.out.println("Productos en el pedido:");
        for (Producto producto : productos) {
            System.out.println("ID Producto: " + producto.getIdProducto());
            System.out.println("Nombre Producto: " + producto.getNombreProducto());
            System.out.println("Precio Producto: " + producto.getPrecio());
            System.out.println("---------------------------");
        }
    }
}
