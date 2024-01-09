import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaInterfaz extends JFrame {

    private List<String> imagenesProductos = new ArrayList<>();
    private JTextArea carritoTextArea;

    public FarmaciaInterfaz() {
        setTitle("Tienda Online");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Bienvenido a la Tienda Online");
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String rutaImagenes = "C:\\Users\\Dania\\Desktop\\Mate";

        File carpeta = new File(rutaImagenes);
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    imagenesProductos.add(archivo.getAbsolutePath());
                }
            }
        }

for (String rutaImagen : imagenesProductos) {
    JPanel productPanel = new JPanel();
    productPanel.setBorder(BorderFactory.createEtchedBorder());

    ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    JLabel productImageLabel = new JLabel(imageIcon);
    JLabel productNameLabel = new JLabel(new File(rutaImagen).getName()); 
    JLabel productPriceLabel = new JLabel("$10"); 

    productPanel.add(productImageLabel);
    productPanel.add(productNameLabel);
    productPanel.add(productPriceLabel);

    centerPanel.add(productPanel);
}

        JPanel sidePanel = new JPanel(new BorderLayout());
        JLabel carritoLabel = new JLabel("Carrito de Compras");
        carritoTextArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(carritoTextArea);
        sidePanel.add(carritoLabel, BorderLayout.NORTH);
        sidePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton addToCartButton = new JButton("Agregar al carrito");
        JButton checkoutButton = new JButton("Realizar compra");

        bottomPanel.add(addToCartButton);
        bottomPanel.add(checkoutButton);

        addToCartButton.addActionListener(e -> agregarAlCarrito());

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); 
    }

    private void agregarAlCarrito() {
        int productoSeleccionado = (int) (Math.random() * imagenesProductos.size());
        String producto = new File(imagenesProductos.get(productoSeleccionado)).getName(); 
        carritoTextArea.append(producto + " agregado al carrito\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FarmaciaInterfaz tienda = new FarmaciaInterfaz();
            tienda.setVisible(true);
        });
    }

    public void mostrarFarmaciaInterfaz() {
        EventQueue.invokeLater(() -> {
            FarmaciaInterfaz tienda = new FarmaciaInterfaz();
            tienda.setVisible(true);
        });
    }
    
}