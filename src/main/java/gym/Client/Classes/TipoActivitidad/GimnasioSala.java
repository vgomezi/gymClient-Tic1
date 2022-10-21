package gym.Client.Classes.TipoActivitidad;

public class GimnasioSala {

    private Integer precio;

    private Integer capacidad;

    private String nombre;


    public GimnasioSala() {
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

    public GimnasioSala(Integer precio, Integer capacidad, String nombre) {
        this.precio = precio;
        this.capacidad = capacidad;
        this.nombre = nombre;
    }
}
