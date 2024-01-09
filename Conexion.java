import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConexionBD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/farmacia";
        String usuario = " LAPTOP-CMO8QV7I ";
        String contraseña = "1234";
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement statement = conexion.createStatement();
            String crearTablaUsuario = "CREATE TABLE Usuario ("
                    + "id_usuario INT PRIMARY KEY" + "nombre_usuario VARCHAR(50) NOT NULL,"
+ "contraseña VARCHAR(50) NOT NULL)";
            statement.executeUpdate(crearTablaUsuario);
            String crearTablaProductos = "CREATE TABLE Productos ("
                    + "id_producto INT PRIMARY KEY AUTO_INCREMENT,"
                    + "nombre_producto VARCHAR(100) NOT NULL,"
                    + "ingrediente_activo VARCHAR(50) NOT NULL," 
      + "categoria VARCHAR(50) NOT NULL,"
                    + "cantidad_en_stock INT NOT NULL,"
                    + "precio DECIMAL(10, 2) NOT NULL)";
            statement.executeUpdate(crearTablaProductos);
            statement.close();
            conexion.close();
            System.out.println("Tablas creadas exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
