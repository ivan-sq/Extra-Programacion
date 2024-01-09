import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetodoDePago extends JFrame {

    private JTextField numeroTarjetaField;
    private JTextField nombreTitularField;
    private JTextField fechaExpiracionField;
    private JPasswordField codigoSeguridadField;

    public MetodoDePago() {
        // Configuración del frame
        setTitle("Metodo De Pago");
        setSize(500, 300); // Se aumentó el ancho del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel numeroTarjetaLabel = new JLabel("Número de Tarjeta:");
        numeroTarjetaField = new JTextField();
        numeroTarjetaField.setColumns(20); // Se aumentó el ancho del campo de texto

        JLabel nombreTitularLabel = new JLabel("Nombre del Titular:");
        nombreTitularField = new JTextField();
        nombreTitularField.setColumns(20); // Se aumentó el ancho del campo de texto

        JLabel fechaExpiracionLabel = new JLabel("Fecha de Expiración:");
        fechaExpiracionField = new JTextField();
        fechaExpiracionField.setColumns(20); // Se aumentó el ancho del campo de texto

        JLabel codigoSeguridadLabel = new JLabel("Código de Seguridad:");
        codigoSeguridadField = new JPasswordField();
        codigoSeguridadField.setColumns(20); // Se aumentó el ancho del campo de texto

        JButton registrarButton = new JButton("Pago");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTarjeta();
            }
        });

        // Configurar el diseño usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(numeroTarjetaLabel, gbc);
        gbc.gridy++;
        add(numeroTarjetaField, gbc);
        gbc.gridy++;
        add(nombreTitularLabel, gbc);
        gbc.gridy++;
        add(nombreTitularField, gbc);
        gbc.gridy++;
        add(fechaExpiracionLabel, gbc);
        gbc.gridy++;
        add(fechaExpiracionField, gbc);
        gbc.gridy++;
        add(codigoSeguridadLabel, gbc);
        gbc.gridy++;
        add(codigoSeguridadField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(registrarButton, gbc);
    }

    private void registrarTarjeta() {
        String numeroTarjeta = numeroTarjetaField.getText();
        String nombreTitular = nombreTitularField.getText();
        String fechaExpiracion = fechaExpiracionField.getText();
        char[] codigoSeguridad = codigoSeguridadField.getPassword();

        // Verificar la tarjeta utilizando el algoritmo de Luhn
        if (!verificarTarjeta(numeroTarjeta)) {
            JOptionPane.showMessageDialog(this, "Número de tarjeta no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Realizar acciones de registro aquí
        // Por ejemplo, validar la información y almacenarla en una base de datos

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "PAGO EXITOSO", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    }

    private boolean verificarTarjeta(String numeroTarjeta) {
        // Implementación básica del algoritmo de Luhn para verificar el número de
        // tarjeta
        int sum = 0;
        boolean alternate = false;

        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numeroTarjeta.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MetodoDePago().setVisible(true);
            }
        });
    }
}