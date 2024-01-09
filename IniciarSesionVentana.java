import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class IniciarSesionVentana extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private Map<String, String> usuarios;

    public IniciarSesionVentana() {
        // Inicializar la estructura de datos para almacenar usuarios
        usuarios = new HashMap<>();

        // Configuración de la ventana principal
        setTitle("Iniciar Sesión");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registerButton = new JButton("Registrarse");

        // Establecer bordes y alineación
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Agregar componentes al panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        // Personalizar el botón de inicio de sesión
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Agregar evento al botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de autenticación
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (usuarios.containsKey(username) && usuarios.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(IniciarSesionVentana.this, "Inicio de sesión exitoso");
                } else {
                    JOptionPane.showMessageDialog(IniciarSesionVentana.this, "Error de inicio de sesión", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar campos después del intento de inicio de sesión
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Agregar evento al botón de registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de registro
                new RegistroVentana(IniciarSesionVentana.this, usuarios).setVisible(true);
            }
        });

        // Agregar panel a la ventana principal
        add(panel);
    }

    // Ventana de registro
    private class RegistroVentana extends JFrame {

        private JTextField nuevoUsuarioField;
        private JPasswordField nuevaPasswordField;

        public RegistroVentana(JFrame parent, Map<String, String> usuarios) {
            // Configuración de la ventana de registro
            super("Registrarse");
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(parent);

            // Creación de componentes
            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel nuevoUsuarioLabel = new JLabel("Nuevo Usuario:");
            JLabel nuevaPasswordLabel = new JLabel("Nueva Contraseña:");
            nuevoUsuarioField = new JTextField();
            nuevaPasswordField = new JPasswordField();
            JButton registrarButton = new JButton("Registrar");

            // Agregar componentes al panel de registro
            panel.add(nuevoUsuarioLabel);
            panel.add(nuevoUsuarioField);
            panel.add(nuevaPasswordLabel);
            panel.add(nuevaPasswordField);
            panel.add(new JLabel()); // Espacio en blanco
            panel.add(registrarButton);

            // Personalizar el botón de registro
            registrarButton.setBackground(new Color(50, 205, 50));
            registrarButton.setForeground(Color.WHITE);
            registrarButton.setFocusPainted(false);

            // Agregar evento al botón de registro
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica de registro
                    String nuevoUsuario = nuevoUsuarioField.getText();
                    char[] nuevaPasswordChars = nuevaPasswordField.getPassword();
                    String nuevaPassword = new String(nuevaPasswordChars);

                    if (!usuarios.containsKey(nuevoUsuario)) {
                        usuarios.put(nuevoUsuario, nuevaPassword);
                        JOptionPane.showMessageDialog(RegistroVentana.this, "Registro exitoso");
                        dispose(); // Cerrar la ventana de registro después del registro exitoso
                    } else {
                        JOptionPane.showMessageDialog(RegistroVentana.this, "El usuario ya existe", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    // Limpiar campos después del registro
                    nuevoUsuarioField.setText("");
                    nuevaPasswordField.setText("");
                }
            });

            // Agregar panel de registro a la ventana de registro
            add(panel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IniciarSesionVentana().setVisible(true);
            }
        });
    }
}
