package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Actividades")
@Component
public class Actividades {

    private @Id String nombre;

    private String tipo;

    private String descripcion;

    private @Id LocalTime hora;

    private @Id LocalDate dia;

    private List<Usuarios> listaUsuariosInscriptos;

    private boolean conCupos;

    private int cupos;

    private boolean reservable;

    private @Id CentrosDeportivos centroDeportivo;

    public Actividades() {
    }

    public Actividades(String nombre, String tipo, String descripcion, LocalTime hora, LocalDate dia, List<Usuarios> listaUsuariosInscriptos, boolean conCupos, int cupos, boolean reservable) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.hora = hora;
        this.dia = dia;
        this.listaUsuariosInscriptos = listaUsuariosInscriptos;
        this.conCupos = conCupos;
        this.cupos = cupos;
        this.reservable = reservable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public List<Usuarios> getListaUsuariosInscriptos() {
        return listaUsuariosInscriptos;
    }

    public void setListaUsuariosInscriptos(List<Usuarios> listaUsuariosInscriptos) {
        this.listaUsuariosInscriptos = listaUsuariosInscriptos;
    }

    public boolean isConCupos() {
        return conCupos;
    }

    public void setConCupos(boolean conCupos) {
        this.conCupos = conCupos;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public boolean isReservable() {
        return reservable;
    }

    public void setReservable(boolean reservable) {
        this.reservable = reservable;
    }
}
