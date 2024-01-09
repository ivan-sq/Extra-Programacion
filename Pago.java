import java.util.Date;
import java.util.List;


public class Pago extends Pedido {
    private String tipoTarjeta;
    private String numeroTarjeta;

    public Pago(int idPedido, int idUsuario, String nombreUsuario, Date fechaPedido, String tipoTarjeta, String numeroTarjeta) {
        super(idPedido, idUsuario, nombreUsuario, fechaPedido, numeroTarjeta);
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
    }


    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public double calcularTotal(List<Double> preciosProductos) {
        double total = 0.0;  

        for (double precio : preciosProductos) {
            total += precio;
        }

        return total;
    }
}

