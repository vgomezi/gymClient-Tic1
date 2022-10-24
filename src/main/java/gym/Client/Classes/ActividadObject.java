package gym.Client.Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ActividadObject {

    private String nombre;

    private LocalTime hora;

    private LocalDate dia;

    private String tipo;

    private String descripcion;

    private List<EmpleadoObject> listaEmpleadoInscriptos;

    private boolean conCupos;

    private int cupos;

    private boolean reservable;

    private CentroDeportivoObject centroDeportivo;

    public ActividadObject() {
    }

    public ActividadObject(String nombre, LocalTime hora, LocalDate dia, String tipo, String descripcion, List<EmpleadoObject> listaEmpleadoInscriptos, boolean conCupos, int cupos, boolean reservable, CentroDeportivoObject centroDeportivo) {
        this.nombre = nombre;
        this.hora = hora;
        this.dia = dia;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.listaEmpleadoInscriptos = listaEmpleadoInscriptos;
        this.conCupos = conCupos;
        this.cupos = cupos;
        this.reservable = reservable;
        this.centroDeportivo = centroDeportivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<EmpleadoObject> getListaEmpleadoInscriptos() {
        return listaEmpleadoInscriptos;
    }

    public void setListaEmpleadoInscriptos(List<EmpleadoObject> listaEmpleadoInscriptos) {
        this.listaEmpleadoInscriptos = listaEmpleadoInscriptos;
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

    public CentroDeportivoObject getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivoObject centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
