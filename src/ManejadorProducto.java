import java.util.Objects;

public class ManejadorProducto {
    private static ManejadorProducto instance;

    // Devuelve la Instancia de la clase, para evitar problemas de instancias
    public static ManejadorProducto getInstance() {
        if(instance == null) {
            instance = new ManejadorProducto();
        }
        return instance;
    }
    //Arreglo donde se guardaran los productos
    private Producto[] productos;
    private ManejadorProducto() {
        //Un espacio para un máximo de 100 productos
        productos = new Producto[100];
    }

    //Función para validar que el producto no exista
    public boolean validarExistencia (String nombreProducto){
        //Se recorre el arreglo, posición por posición
        for (int i = 0; i < productos.length; i++) {
            /*Si encuentra un producto con el mismo nombre dentro del arreglo,
            significa que ya existe un producto con el mismo nombre, por lo tanto
            no se agrega el producto*/
            if(productos[i]!=null){
                if(Objects.equals(productos[i].getNombreProducto(), nombreProducto)){
                    return false;
                }
            }
        }
        //Si no encuentra coincidencias de nombre, si puede agregarse el producto
        return true;
    }

    //Procedimiento para la creación de un nuevo producto
    public void nuevoProducto(String nombreProducto, Double precioProducto) {
        //Se valida que no exista un producto con el mismo nombre
        if(validarExistencia(nombreProducto)){
            //Se recorre el arreglo, posición por posición
            for (int i = 0; i < productos.length; i++) {
                //Se encuentra una posición vacía
                if (productos[i] == null) {
                    //En la posición Vacía se inserta el nuevo producto
                    productos[i] = new Producto(nombreProducto,precioProducto,0);
                    break;
                }
            }
            System.out.println("Producto ingresado con éxito!!");
        }else{
            System.out.println("Error, el producto ingresado ya existe!!");
        }

    }

    //Método para imprimir todos los productos almacenados
    public void mostrarProductosDisponibles() {
        //Se recorre el arreglo, posición por posición
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] == null) {
                break;
            }else{
                //Se imprimen los datos del producto
                System.out.println(productos[i].getNombreProducto()+"<--->"+productos[i].getPrecioProducto());
            }
        }
    }

    //Procedimiento para aumentar unidades vendidas de la  compra efectuada
    public boolean actualizarUnidadesVendidas(String nombreProducto, int cantidadVenta) {
        for (Producto producto : productos) {
            //Mientras la posición no sea nula
            if (producto != null) {
                //Si encuentra el nombre del producto
                if (producto.getNombreProducto().equals(nombreProducto)) {
                    //Le aumenta el número  de unidades vendidas
                    producto.setCantidadVendido(producto.getCantidadVendido() + cantidadVenta);
                    return true;
                }
            }
        }
        //Por si no encuentra el producto, indica que no existe
        return false;
    }

    public double devolverPrecioProducto(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto != null) {
                if (producto.getNombreProducto().equals(nombreProducto)) {
                    return producto.getPrecioProducto();
                }
            }
        }
        return 0.00;
    }

    public void ordenarArreglo(){
        int n = 0;
        Producto temp;
        //Contar cuantos espacios del  array hay ocupados
        for (int k = 0; k < productos.length; k++) {
            if (productos[k]!=null){
                n++;
            }
        }
        //Ordenar el array
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (productos[j].getCantidadVendido() < productos[j+1].getCantidadVendido()) {
                    temp = productos[j];
                    productos[j] = productos[j+1];
                    productos[j+1] = temp;
                }
            }
        }
    }

    public void mostrarMasVendidos() {
        //Se recorre el arreglo, posición por posición
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] == null) {
                break;
            }else{
                System.out.println(productos[i].getNombreProducto()+"<--->"+productos[i].getCantidadVendido());
            }
        }
    }
}
