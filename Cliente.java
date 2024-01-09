public class Cliente extends Usuario {
    private String nombre;
    private String correoElectronico;
    private String telefono;

  
    public Cliente(int idUsuario, String nombreUsuario, String contraseña, String nombre, String correoElectronico, String telefono) {
        super(idUsuario, nombreUsuario, contraseña);
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

  
    @Override
    public void imprimirInformacion() {
        System.out.println("Cliente - ID: " + getIdUsuario());
        System.out.println("Nombre de Usuario: " + getNombreUsuario());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Correo electrónico: " + getCorreoElectronico());
        System.out.println("Teléfono: " + getTelefono());
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Cliente cliente = new Cliente(1, "user123", "pass123", "Juan", "juan@example.com", "123456789");
        cliente.imprimirInformacion();
    }
}

