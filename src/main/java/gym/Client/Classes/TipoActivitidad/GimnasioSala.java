package gym.Client.Classes.TipoActivitidad;

public class GimnasioSala {

    private Integer precio;

    private Integer capacidad;

    private String nombre;

    private String descripcion;

    private String dia;

    private String hora;


    public GimnasioSala() {
    }

    public GimnasioSala(Integer precio, Integer capacidad, String nombre, String descripcion, String dia, String hora) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dia = dia;
        this.hora = hora;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDia() { return dia; }

    public void setDia(String dia) { this.dia = dia; }

    public String getHora() { return hora; }

    public void setHora(String hora) { this.hora = hora; }

}
