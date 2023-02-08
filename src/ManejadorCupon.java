import java.util.Objects;

public class ManejadorCupon {
    private static ManejadorCupon instance;

    // Devuelve la Instancia de la clase, para evitar problemas de instancias
    public static ManejadorCupon getInstance() {
        if(instance == null) {
            instance = new ManejadorCupon();
        }
        return instance;
    }
    //Arreglo donde se guardaran los cupones
    private Cupon[] cupones;
    private ManejadorCupon() {
        //Un espacio para un máximo de 100 cupones
        cupones = new Cupon[100];
    }

    //Función para validar que el cupon no exista
    public boolean validarExistencia (String codigoCupon){
        //Se recorre el arreglo, posición por posición
        for (int i = 0; i < cupones.length; i++) {
            /*Si encuentra un producto con el mismo nombre dentro del arreglo,
            significa que ya existe un producto con el mismo nombre, por lo tanto
            no se agrega el producto*/
            if(cupones[i]!=null){
                if(Objects.equals(cupones[i].getCodigo(), codigoCupon)){
                    return false;
                }
            }
        }
        //Si no encuentra coincidencias de código, si puede agregarse el cupón
        return true;
    }

    //Procedimiento para la creación de un nuevo cupón
    public void nuevoCupon(String codigo, int porcentaje)  {
        //Se valida que no exista un cupón con el mismo nombre
        if(validarExistencia(codigo)){
            //Se valida un porcentaje mayor a 0 y menor a 100
            if(porcentaje>0 && porcentaje<100){
                /*Se valida que el cupón sea exactamente de 4 dígitos, se le
                aplica un split al código inicial*/
                String[] codigoDividido=codigo.split("");
                if(codigoDividido.length==4){
                    //Validado lo anterior, se procede a agregar el cupón.
                    //Se recorre el arreglo, posición por posición
                    for (int i = 0; i < cupones.length; i++) {
                        //Se encuentra una posición vacía
                        if (cupones[i] == null) {
                            //En la posición Vacía se inserta el nuevo cupón
                            cupones[i] = new Cupon(codigo,porcentaje);
                            break;
                        }
                    }
                    System.out.println("Cupón ingresado con éxito!!");
                }else{
                    System.out.println("Error, el código no tiene 4 dígitos!!");
                }
            }else{
                System.out.println("Error, el porcentaje es incorrecto!!");
            }
        }else{
            System.out.println("Error, el cupón ingresado ya existe!!");
        }

    }

    //método para devolver el porcentaje de descuento del cupón que viene de parámetro
    public int aplicarCupon(String codigoCupon) {
        for (Cupon cupon : cupones) {
            if (cupon != null) {
                if (cupon.getCodigo().equals(codigoCupon)) {
                    return cupon.getPorcentaje();
                }
            }
        }
        return -1;
    }
}
