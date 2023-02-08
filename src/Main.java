import java.util.Objects;
import java.util.Scanner;


public class Main {
    /*Variables globales, se usarán para comprobar el ingreso correcto de credenciales
    ( Usuario y Contraseña)
    */
    static String usuario="cajero_202100081";
    static String contrasenia="ipc1_202100081";

    //Variable que almacena la opción del menú principal seleccionada
    static int opcionMenuPrincipal;
    //Estos scanners, recolectarán la información ingresada en consola
    //Scanner para recolectar la opción escogida del menú principal
    static Scanner scannerMenuPrincipal = new Scanner(System.in);
    static Scanner scannerUsuario=new Scanner(System.in);
    static Scanner scannerContrasenia=new Scanner(System.in);
    static Scanner scannerNombreProducto=new Scanner(System.in);
    static Scanner scannerPrecioProducto=new Scanner(System.in);
    static Scanner scannerCodigoCupon=new Scanner(System.in);
    static Scanner scannerPorcentajeCupon=new Scanner(System.in);
    static Scanner scannerProductoVenta=new Scanner(System.in);
    static Scanner scannerCantidadVenta=new Scanner(System.in);
    static Scanner scannerContinua=new Scanner(System.in);
    static Scanner scannerCliente=new Scanner(System.in);
    static Scanner scannerNit=new Scanner(System.in);
    static Scanner scannerConfirmarCupon=new Scanner(System.in);
    static Scanner scannerDarCupon=new Scanner(System.in);



    // Sentinela que verifica si hay una sesión iniciada
    static boolean loginCorrecto=false;

    // Instancia del manejador de productos
    static ManejadorProducto manejadorProducto = ManejadorProducto.getInstance();
    //Instancia del manejador de cupones
    static ManejadorCupon manejadorCupon = ManejadorCupon.getInstance();

    // Variable que almacena el valor total de la venta que esté en ese momento
    static Double totalVenta=0.00;
    //Variable que almacena el historial de la venta
    static String desgloseVenta="";

    static boolean ventaContinua=true;
    public static void main(String[] args)  {
        login();
    }


    public static void login() {
        /* Login que valida las credenciales ingresadas por el usuario*/
        do {
            String contraseniaTemporal="";
            String usuarioTemporal="";
            System.out.println("\n");
            System.out.println("=============Super 25 - Login===========");
            System.out.println("| Ingresa tu usuario:                   ");
            usuarioTemporal=scannerUsuario.nextLine();
            System.out.println("| Ingresa tu contrasenia:               ");
            contraseniaTemporal=scannerContrasenia.nextLine();
            //Se verifica que el usuario y contraseña ingresada sean los correctos
            if(Objects.equals(usuarioTemporal, usuario) && Objects.equals(contraseniaTemporal, contrasenia)){
                /*Si las credenciales son correctas, ya podemos ingresar al menú principal.
                Ya que la condición del Do-While es que no haya logín correcto, mediante
                el cambio a nuestro sentinela, ya se dará como un hecho el login correcto.
                 */
                System.out.println("Inicio de sesion Exitoso!!");
                System.out.println("========================================");;
                System.out.println();
                loginCorrecto=true;
            }else{
                //Mensaje de credenciales Incorrectas
                System.out.println("Error, Credenciales Incorrectas :( !!");
                System.out.println("========================================");;
                //Si las credenciales no son correctas, se volverá a repetir el ciclo

            }

            //Lo hará hasta que se cumpla la condición del while
        } while (!loginCorrecto);
            /*Una vez cumplida la condición del Do-While, indica un login correcto, por
            lo que podemos acceder al menú principal*/
        mainMenu();
    }

    public static void mainMenu() {
        /* Menú iniciado con un Do-While, esto con el objetivo de mostrar el menú
        las veces que sea necesario, hasta que se desee finalizar con la ejecución*/
        do {
            System.out.println("\n\n");
            System.out.println("==============***Super 25***============");
            System.out.println("| 1. Agregar Producto                  |");
            System.out.println("| 2. Agregar Cupón de descuento        |");
            System.out.println("| 3. Realizar Ventas                   |");
            System.out.println("| 4. Realizar Reporte                  |");
            System.out.println("| 5. Salir                             |");
            System.out.println("========================================");;
            System.out.println();
            System.out.println("Teclee la opción requerida: ");
            //Variable que almacena el dígito de la opción seleccionada
            opcionMenuPrincipal = scannerMenuPrincipal.nextInt();
            //Lo hará hasta que se cumpla la condición del while
        } while (opcionMenuPrincipal < 1 || opcionMenuPrincipal >5);
            /*Una vez cumplida la condición del Do-While, le sigue un switch, ya que para
            cada opción seleccionada hay una diferente acción que debe ser realizada*/
        switch (opcionMenuPrincipal) {
            // En caso se haya escogido la opción 1, se agregará un nuevo producto al sistema
            case 1:
                //Variables que guardan los datos temporales del producto
                String nombreTemporal;
                Double precioTemporal;
                System.out.println("\n\n\n");
                System.out.println("=============Datos Producto============");
                System.out.println("Nombre Del Producto:         ");
                nombreTemporal=scannerNombreProducto.nextLine();
                System.out.println("Precio Del Producto:         ");
                precioTemporal=scannerPrecioProducto.nextDouble();
                manejadorProducto.nuevoProducto(nombreTemporal,precioTemporal);
                System.out.println("========================================");
                mainMenu();
            case 2:
                //Variables que guardan los datos temporales del cupon
                String codigoTemporal;
                int porcentajeTemporal;
                System.out.println("\n\n\n");
                System.out.println("===============Datos Cupon==============");
                System.out.println("Código Del Cupon:             ");
                codigoTemporal=scannerCodigoCupon.nextLine();
                System.out.println("Porcentaje Del Cupon:         ");
                porcentajeTemporal=scannerPorcentajeCupon.nextInt();
                manejadorCupon.nuevoCupon(codigoTemporal,porcentajeTemporal);
                System.out.println("========================================");
                mainMenu();
            case 3:
                ventaContinua=true;
                desgloseVenta="";
                totalVenta=0.00;
                String cliente="";
                String nit="";
                System.out.println("Cliente:         ");
                cliente=scannerCliente.nextLine();
                System.out.println("Nit:         ");
                nit=scannerNit.nextLine();
                if(nit==""){
                    nit="C/F";
                }
                String productoCompra="";
                int cantidadCompra=0;
                System.out.println("\n\n\n");
                System.out.println("=============Venta Productos============");
                System.out.println("**Producto -------- Precio**");
                //Se mostrarán todos los productos disponibles
                manejadorProducto.mostrarProductosDisponibles();
                do{
                    productoCompra="";
                    cantidadCompra=0;
                    String opcion="";
                    System.out.println("Nombre Del Producto:         ");
                    productoCompra=scannerProductoVenta.nextLine();
                    System.out.println("Cantidad A Comprar:         ");
                    cantidadCompra=scannerCantidadVenta.nextInt();
                    //Se valida que el producto ingresado exista
                    if(manejadorProducto.actualizarUnidadesVendidas(productoCompra,cantidadCompra)){
                        //Se le agrega al total de la venta lo acumulado
                        totalVenta+=cantidadCompra*manejadorProducto.devolverPrecioProducto(productoCompra);
                        //Se agrega cada producto al desglose de la compra
                        desgloseVenta+=productoCompra+"----"+manejadorProducto.devolverPrecioProducto(productoCompra)+"----"+cantidadCompra+"----"+(cantidadCompra*manejadorProducto.devolverPrecioProducto(productoCompra))+"\n";
                        System.out.println("Producto agregado a la venta preliminar!!");
                    }else{
                        System.out.println("El producto solicitado no existe!!");
                    }
                    System.out.println("Desea agregar más productos S/N:");
                    opcion=scannerContinua.nextLine();
                    System.out.println(opcion.toUpperCase());
                    if(opcion.equalsIgnoreCase("S")){
                        ventaContinua=true;
                    }else{
                        //Se cancela la venta
                        break;

                    }
                }while(ventaContinua);
                String quieroCupon="";
                String elCupon="";
                Double ventaConDescuento=0.00;
                int porcentajeCupon=0;
                //Hora de pedir cupon de descuento
                System.out.println("Desea agregar cupón de descuento S/N: ");
                quieroCupon=scannerConfirmarCupon.nextLine();
                if(quieroCupon.equalsIgnoreCase("S")){
                    System.out.println("Ingresar Cupon: ");
                    elCupon=scannerDarCupon.nextLine();
                    porcentajeCupon=manejadorCupon.aplicarCupon(elCupon);
                    if(porcentajeCupon!=-1){
                        ventaConDescuento=totalVenta-(totalVenta*(Double.parseDouble(String.valueOf(porcentajeCupon))/100));
                    }else{
                        System.out.println("El cupón no existe!!");
                    }
                }else{
                    porcentajeCupon=0;
                    ventaConDescuento=totalVenta;
                }

                /*Generando la factura, practimente solo impimir en consola la información
                recolectada*/
                System.out.println("========================================");
                System.out.println("\n\n");
                System.out.println("Generando factura......\n\n");
                System.out.println("Empresa: Super25");
                System.out.println("Cajero: Javier Monjes");
                System.out.println("Cliente: "+cliente);
                System.out.println("Nit: "+nit);
                /*Calcular la fecha:
                    El método System.currentTimeMillis(): Este método devuelve el número de
                    milisegundos desde la época (1 de enero de 1970, 00:00:00 UTC) hasta la
                    fecha y hora actual.
                */
                long currentTimeMillis = System.currentTimeMillis();
                // Calcular los segundos desde la época
                long segundosAcumulados = currentTimeMillis / 1000;
                // Calcular los días desde la época
                long diasAcumulados = segundosAcumulados / 86400;
                // Calcular los años desde la época
                int aniosAcumulados = (int) (diasAcumulados / 365);
                // Calcular los días restantes después de calcular los años
                long diasRestantes = diasAcumulados % 365;
                // Calcular el mes actual (en formato 0-11)
                int mesActual = (int) (diasRestantes / 30);
                // Calcular los días restantes después de calcular el mes
                long diaActual = diasRestantes % 30 + 1;
                // Imprimir la fecha actual en formato dd/MM/yyyy
                System.out.println(String.format("%02d/%02d/%04d", diaActual-14, mesActual + 1, 1970 + aniosAcumulados));
                System.out.println("Producto--Precio Unitario--Cantidad--Total");
                System.out.println(desgloseVenta);
                System.out.println("Subtotal: "+totalVenta);
                System.out.println("Descuento: "+porcentajeCupon);
                System.out.println("Total: "+ventaConDescuento);
                System.out.println("========================================");
                mainMenu();
            case 4:
                //Reporte de los artículos más vendidos
                System.out.println("\n\n\n");
                System.out.println("Reporte Productos Más Vendidos: ");
                System.out.println("Producto ---- Unidades Vendidas");
                //Ordenamos el arreglo, usando el ordenamiento burbuja
                manejadorProducto.ordenarArreglo();
                //Se imprimen todos los articulos ya ordenados
                manejadorProducto.mostrarMasVendidos();
                //Regresamos al menú
                mainMenu();
            case 5:
                //Se termina con la ejecución del programa
                System.exit(0);
        }
    }




}