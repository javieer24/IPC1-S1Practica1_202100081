public class Producto {
    //Atributos de la clase Producto
    private String nombreProducto;
    private Double precioProducto;
    private int cantidadVendido;

    //Constructor de la clase Producto
    public Producto(String nombreProducto, Double precioProducto,int cantidadVendido){
        this.nombreProducto=nombreProducto;
        this.precioProducto=precioProducto;
        this.cantidadVendido=cantidadVendido;
    }

    // Getters y Setters, para acceder a las propiedades de los productos
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidadVendido() {
        return cantidadVendido;
    }

    public void setCantidadVendido(int cantidadVendido) {
        this.cantidadVendido = cantidadVendido;
    }
}
