import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazBienvenida extends JFrame {

    private JButton ingresarButton;

    public InterfazBienvenida() {
        // Configuración del frame
        setTitle("Bienvenido a la Tienda de Medicamentos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel bienvenidaLabel = new JLabel("¡Bienvenido a nuestra tienda en línea!");

        ingresarButton = new JButton("Ingresar");
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInterfazPrincipal();
            }
        });

        // Configurar el diseño usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(bienvenidaLabel, gbc);

        gbc.gridy++;
        add(ingresarButton, gbc);
    }

    private void mostrarInterfazPrincipal() {
        JOptionPane.showMessageDialog(this, "Bienvenido a la tienda en línea.", "Bienvenida",
                JOptionPane.INFORMATION_MESSAGE);

        // Puedes abrir la nueva interfaz principal aquí o realizar otras acciones según
        // tus necesidades.
        // Por ejemplo, podrías cerrar esta interfaz y abrir la interfaz de la tienda
        // principal.
        // Aquí se muestra un mensaje para fines demostrativos.
        JOptionPane.showMessageDialog(this, "Redirigiendo a la interfaz principal...", "Mensaje",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazBienvenida().setVisible(true);
            }
        });
    }
}
