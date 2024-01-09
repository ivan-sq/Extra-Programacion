public class Main {
    public static void main(String[] args) {
     
        Producto producto1 = new Producto(1, "Producto A", "Ingrediente X", 50, 19.99);

       
        System.out.println("Nombre del producto: " + producto1.getNombreProducto());
        System.out.println("Precio del producto: $" + producto1.getPrecio());

        
        producto1.actualizarStock(-10); 
        System.out.println("Cantidad en stock actualizada: " + producto1.getCantidadStock());
    }
}
