public class Usuario1 <T> {
    private T usuario;

    public Usuario1 (T usuario) {
        this.usuario = usuario;
    }

    public T getUsuario() {
        return usuario;
    }

    public void setUsuario(T usuario) {
        this.usuario = usuario;
    }

    public static void main(String[] args) {
        
        Cliente cliente = new Cliente(1, "user123", "pass123", "Juan", "juan@example.com", "123456789");
        Usuario1<Cliente> usuarioGenerico = new Usuario1<>(cliente);

        Cliente clienteRecuperado = usuarioGenerico.getUsuario();
        clienteRecuperado.imprimirInformacion();
    }
}
