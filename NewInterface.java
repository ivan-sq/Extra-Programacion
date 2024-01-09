import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewInterface {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public NewInterface() {
        frame = new JFrame("Inicio de Sesión");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.YELLOW);

        panel.setLayout(new GridBagLayout());
GridBagConstraints constraints = new GridBagConstraints();
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Id:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Nombre:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton goToNewInterfaceButton = new JButton("Registrate");
        goToNewInterfaceButton.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panel.add(titleLabel, constraints);
        constraints.gridy = 12;
        panel.add(goToNewInterfaceButton, constraints);
        goToNewInterfaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                StyledLoginInterface newInterface = new StyledLoginInterface();
                newInterface.mostrarStyledLoginInterface();
            }
        });
        
        frame.setContentPane(panel);
        frame.setVisible(true);

        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        panel.add(usernameLabel, constraints);

        constraints.gridy = 2;
        panel.add(usernameField, constraints);

        constraints.gridy = 3;
        panel.add(passwordLabel, constraints);

        constraints.gridy = 4;
        panel.add(passwordField, constraints);

        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
        
                boolean inicioSesionExitoso = validarCredenciales(username, password);
        
                if (inicioSesionExitoso) {
                    JOptionPane.showMessageDialog(frame, "¡Inicio de sesión exitoso!");
        
                    frame.dispose();
                    FarmaciaInterfaz farmaciaInterfaz = new FarmaciaInterfaz();
                    farmaciaInterfaz.mostrarFarmaciaInterfaz();
                } else {
                    JOptionPane.showMessageDialog(frame, "Inicio de sesión fallido. Inténtalo de nuevo.");
                }
            }
        });
        

        frame.setContentPane(panel);
        frame.setVisible(true);
    }


private boolean validarCredenciales(String id, String nombre) {
    String url = "jdbc:mariadb://localhost:3306/tienda"; 
    String usuarioDB = "dania";
    String contraseñaDB = "";

    try (Connection conn = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
         PreparedStatement statement = conn.prepareStatement("SELECT * FROM Usuarios WHERE id = ? AND nombre = ?")) {
        statement.setString(1, id);
        statement.setString(2, nombre);

        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewInterface();
            }
        });
        
    }

    public void mostrarNewInterfaz() {
    }
}
