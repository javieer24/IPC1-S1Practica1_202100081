public class Cupon {
    //Atributos de la clase Cupon
    private String codigo;
    private int porcentaje;

    //Constructor de la clase Cupon
    public Cupon(String codigo, int porcentaje){
        this.codigo=codigo;
        this.porcentaje=porcentaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
