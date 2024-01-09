import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfazTiendaPrincipal extends JFrame {

    private JButton categoria1Button;
    private JButton categoria2Button;
    private JButton categoria3Button;
    private JList<String> productosList;
    private DefaultListModel<String> productosListModel;
    private JButton agregarAlCarritoButton;
    private JList<String> carritoList;
    private DefaultListModel<String> carritoListModel;
    private JButton eliminarDelCarritoButton;

    public InterfazTiendaPrincipal() {
        // Configuración del frame
        setTitle("Tienda de Medicamentos - Catálogo");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel bienvenidaLabel = new JLabel("Bienvenido a la Tienda de Medicamentos");
        categoria1Button = new JButton("Analgesicos");
        categoria2Button = new JButton("Antibioticos");
        categoria3Button = new JButton("Vitaminas");

        // Configurar el modelo de la lista de productos
        productosListModel = new DefaultListModel<>();
        productosList = new JList<>(productosListModel);
        JScrollPane productosScrollPane = new JScrollPane(productosList);
        agregarAlCarritoButton = new JButton("Agregar al Carrito");

        // Configurar el modelo de la lista del carrito
        carritoListModel = new DefaultListModel<>();
        carritoList = new JList<>(carritoListModel);
        JScrollPane carritoScrollPane = new JScrollPane(carritoList);
        eliminarDelCarritoButton = new JButton("Eliminar del Carrito");

        // Acción del botón "Analgesicos"
        categoria1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosCategoria("Analgesicos");
            }
        });

        // Acción del botón "Antibioticos"
        categoria2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosCategoria("Antibioticos");
            }
        });

        // Acción del botón "Vitaminas"
        categoria3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductosCategoria("Vitaminas");
            }
        });

        // Acción del botón "Agregar al Carrito"
        agregarAlCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlCarrito();
            }
        });

        // Acción del botón "Eliminar del Carrito"
        eliminarDelCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDelCarrito();
            }
        });

        // Configurar el diseño usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        add(bienvenidaLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(categoria1Button, gbc);

        gbc.gridx++;
        add(categoria2Button, gbc);

        gbc.gridx++;
        add(categoria3Button, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(productosScrollPane, gbc);

        gbc.gridx++;
        gbc.weightx = 0.5;
        add(agregarAlCarritoButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        add(carritoScrollPane, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        add(eliminarDelCarritoButton, gbc);
    }

    private void mostrarProductosCategoria(String categoria) {
        // Lógica para obtener y mostrar productos de la categoría seleccionada
        productosListModel.clear();

        switch (categoria) {
            case "Analgesicos":
                productosListModel.addElement("Paracetamol - $5.00");
                productosListModel.addElement("Ibuprofeno - $8.00");
                productosListModel.addElement("Aspirina - $3.50");
                break;
            case "Antibioticos":
                productosListModel.addElement("Amoxicilina - $10.00");
                productosListModel.addElement("Azitromicina - $15.00");
                productosListModel.addElement("Ciprofloxacina - $12.50");
                break;
            case "Vitaminas":
                productosListModel.addElement("Vitamina C - $7.00");
                productosListModel.addElement("Vitamina D - $9.00");
                productosListModel.addElement("Complejo B - $6.50");
                break;
            default:
                productosListModel.addElement("No hay productos disponibles en esta categoría.");
                break;
        }
    }

    private void agregarAlCarrito() {
        // Lógica para agregar el producto seleccionado al carrito sin duplicación
        String productoSeleccionado = productosList.getSelectedValue();
        if (productoSeleccionado != null && !carritoListModel.contains(productoSeleccionado)) {
            carritoListModel.addElement(productoSeleccionado);
            JOptionPane.showMessageDialog(this, "Producto agregado al carrito: " + productoSeleccionado, "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para agregar al carrito.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "El producto ya está en el carrito.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarDelCarrito() {
        // Lógica para eliminar el producto seleccionado del carrito
        int indiceSeleccionado = carritoList.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            carritoListModel.remove(indiceSeleccionado);
            JOptionPane.showMessageDialog(this, "Producto eliminado del carrito.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto en el carrito para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazTiendaPrincipal().setVisible(true);
            }
        });
    }
}
