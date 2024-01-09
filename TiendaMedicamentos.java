import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TiendaMedicamentos extends JFrame {

    private List<Producto> catalogo;
    private Map<Producto, Integer> carrito;
    private double totalCompra;

    private DefaultListModel<Producto> catalogoListModel;
    private JList<Producto> catalogoList;
    private DefaultListModel<String> carritoListModel;
    private JList<String> carritoList;

    private JLabel totalLabel;

    private JButton agregarAlCarritoButton;
    private JButton quitarDelCarritoButton;
    private JButton finalizarCompraButton;

    public TiendaMedicamentos() {
        // Inicializar catálogo y carrito
        catalogo = new ArrayList<>();
        carrito = new HashMap<>();
        totalCompra = 0.0;

        // Configuración de la ventana principal
        setTitle("Tienda de Medicamentos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear algunos productos de ejemplo
        Producto paracetamol = new Producto("Paracetamol", "Categoría: Analgésico y Antipirético\r\n" + //
                "Propósito: Alivio del dolor y reducción de la fiebre.", 5.99);
        Producto ibuprofeno = new Producto("Ibuprofeno", "Categoría: Antiinflamatorio no esteroideo (AINE)\r\n" + //
                "Propósito: Alivio del dolor, reducción de la inflamación y la fiebre.", 7.99);
        Producto aspirina = new Producto("Aspirina", "Analgésico", 4.99);
        Producto amoxicilina = new Producto("Amoxicilina", "Categoría: Antibiótico\r\n" + //
                "Propósito: Tratamiento de infecciones bacterianas, como infecciones respiratorias y del oído.", 20.00);
        Producto omeprazol = new Producto("Omeprazol", "Categoría:(antiácido)\r\n" + //
                "Propósito: Reducción de la producción de ácido estomacal para tratar úlceras gástricas y problemas de reflujo.",
                4.99);
        Producto loratadina = new Producto("Loratadina", "Categoría: Antihistamínico (para alergias)\r\n" + //
                "Propósito: Alivio de los síntomas de alergias, como estornudos, picazón y ojos llorosos.", 4.99);
        Producto hidroclorotiazida = new Producto("Hidroclorotiazida",
                "Categoría: Diurético (para la presión arterial)\r\n" + //
                        "Propósito: Reducción de la presión arterial al aumentar la eliminación de líquidos del cuerpo.",
                4.99);
        Producto dipirona = new Producto("Dipirona", "Categoría: Analgésico y Antipirético\r\n" + //
                "Propósito: Alivio del dolor y reducción de la fiebre.", 4.99);

        // Agregar productos al catálogo
        catalogo.add(paracetamol);
        catalogo.add(ibuprofeno);
        catalogo.add(aspirina);
        catalogo.add(amoxicilina);
        catalogo.add(omeprazol);
        catalogo.add(loratadina);
        catalogo.add(hidroclorotiazida);
        catalogo.add(dipirona);

        // Crear componentes
        catalogoListModel = new DefaultListModel<>();
        catalogoList = new JList<>(catalogoListModel);

        carritoListModel = new DefaultListModel<>();
        carritoList = new JList<>(carritoListModel);

        totalLabel = new JLabel("Total: $" + totalCompra);

        agregarAlCarritoButton = new JButton("Agregar al Carrito");
        quitarDelCarritoButton = new JButton("Quitar del Carrito");
        finalizarCompraButton = new JButton("Finalizar Compra");

        // Inicializar el modelo de lista del catálogo
        for (Producto producto : catalogo) {
            catalogoListModel.addElement(producto);
        }

        // Configurar el diseño de la ventana principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Catálogo", new JScrollPane(catalogoList));
        tabbedPane.addTab("Carrito", new JScrollPane(carritoList));

        // Agregar pestañas a la ventana
        add(tabbedPane, BorderLayout.CENTER);

        // Crear panel para botones y total
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(agregarAlCarritoButton);
        panelBotones.add(quitarDelCarritoButton);
        panelBotones.add(finalizarCompraButton);

        // Agregar total al panel
        panelBotones.add(totalLabel);

        // Agregar panel de botones a la parte inferior de la ventana
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar eventos de botones
        agregarAlCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlCarrito();
            }
        });

        quitarDelCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDelCarrito();
            }
        });

        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
    }

    private void agregarAlCarrito() {
        Producto productoSeleccionado = catalogoList.getSelectedValue();
        if (productoSeleccionado != null) {
            // Verificar si el producto ya está en el carrito
            if (carrito.containsKey(productoSeleccionado)) {
                // Incrementar la cantidad si ya está en el carrito
                int cantidadActual = carrito.get(productoSeleccionado);
                carrito.put(productoSeleccionado, cantidadActual + 1);
            } else {
                // Agregar el producto al carrito si no está presente
                carrito.put(productoSeleccionado, 1);
            }

            // Actualizar el precio total
            totalCompra += productoSeleccionado.getPrecio();
            totalLabel.setText("Total: $" + totalCompra);

            actualizarListaCarrito();
        }
    }

    private void quitarDelCarrito() {
        Producto productoSeleccionado = obtenerProductoSeleccionadoCarrito();
        if (productoSeleccionado != null) {
            // Verificar la cantidad en el carrito
            int cantidadActual = carrito.get(productoSeleccionado);
            if (cantidadActual > 1) {
                // Reducir la cantidad si hay más de uno en el carrito
                carrito.put(productoSeleccionado, cantidadActual - 1);
            } else {
                // Eliminar el producto del carrito si hay solo uno
                carrito.remove(productoSeleccionado);
            }

            // Actualizar el precio total
            totalCompra -= productoSeleccionado.getPrecio();
            totalLabel.setText("Total: $" + totalCompra);

            actualizarListaCarrito();
        }
    }

    private void finalizarCompra() {
        // Puedes realizar acciones adicionales aquí antes de finalizar la compra
        // Por ejemplo, calcular el total, actualizar inventario, etc.

        // Limpiar el carrito después de la compra
        carrito.clear();
        carritoListModel.clear();

        // Reiniciar el total
        totalCompra = 0.0;
        totalLabel.setText("Total: $" + totalCompra);

        JOptionPane.showMessageDialog(this, "Compra finalizada con éxito. ¡Gracias por su compra!");
    }

    private void actualizarListaCarrito() {
        carritoListModel.clear();
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            carritoListModel.addElement(producto + " - Cantidad: " + cantidad);
        }
    }

    private Producto obtenerProductoSeleccionadoCarrito() {
        int indiceSeleccionado = carritoList.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            String[] partes = carritoList.getModel().getElementAt(indiceSeleccionado).split(" - Cantidad: ");
            String nombreProducto = partes[0];
            for (Producto producto : carrito.keySet()) {
                if (producto.toString().equals(nombreProducto)) {
                    return producto;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TiendaMedicamentos().setVisible(true);
            }
        });
    }
}

class Producto {
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}