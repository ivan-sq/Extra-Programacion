import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StyledLoginInterface {
    private JFrame frame;
    private JPanel panel;
    private JTextField idField;
    private JTextField usernameField;
    private JTextField phoneField;
    private JTextField emailField;

    public StyledLoginInterface() {
        frame = new JFrame("Registro de Usuario");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        panel.setLayout(new GridBagLayout());
GridBagConstraints constraints = new GridBagConstraints();
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registro de Usuario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        idField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Nombre de usuario:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Teléfono:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneField = new JTextField(15);

        JLabel addressLabel = new JLabel("Correo electrónico:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField = new JTextField(15);


        JButton registerButton = new JButton("Registrar");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registerButton.setBackground(Color.decode("#4CAF50"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

 
        JButton goToNewInterfaceButton = new JButton("Inicia sesion");
        goToNewInterfaceButton.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panel.add(titleLabel, constraints);
        constraints.gridy = 12;
        panel.add(goToNewInterfaceButton, constraints);
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        goToNewInterfaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                NewInterface newInterface = new NewInterface();
                newInterface.mostrarNewInterfaz();
            }
        });
        
        frame.setContentPane(panel);
        frame.setVisible(true);

        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        panel.add(idLabel, constraints);

        constraints.gridy = 2;
        panel.add(idField, constraints);

        constraints.gridy = 3;
        panel.add(usernameLabel, constraints);

        constraints.gridy = 4;
        panel.add(usernameField, constraints);

        constraints.gridy = 5;
        panel.add(phoneLabel, constraints);

        constraints.gridy = 6;
        panel.add(phoneField, constraints);

        constraints.gridy = 7;
        panel.add(addressLabel, constraints);

        constraints.gridy = 8;
        panel.add(emailField, constraints);


        constraints.gridy = 11;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, constraints);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String username = usernameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                Connection conn = null;
                PreparedStatement statement = null;
                try {
                    conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tienda", "dania", "");

                    String query = "INSERT INTO usuarios (id, nombre, telefono, ubicacion, tarjeta_credito) VALUES (?, ?, ?, ?, ?)";
                    statement = conn.prepareStatement(query);
                    statement.setString(1, id);
                    statement.setString(2, username);
                    statement.setString(3, phone);
                    statement.setString(4, email);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(frame, "Registro exitoso");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al registrar usuario");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
    System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
} finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StyledLoginInterface();
            }
        });
    }

    public void mostrarStyledLoginInterface() {
    }
}