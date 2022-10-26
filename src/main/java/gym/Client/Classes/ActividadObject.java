package gym.Client.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActividadObject {

    private String nombre;

    private LocalTime hora;

    private LocalDate dia;

    private String centroMail;

    private TipoActividadObject tipo;

    private String descripcion;

    private int costo;

    private int cupos;

    private boolean reservable;

    private CentroDeportivoObject centroDeportivo;

    private List<InscripcionesActividadesObject> actividadesInscripto;

    public ActividadObject() {
    }

    public ActividadObject(String nombre, LocalTime hora, LocalDate dia, String centroMail, TipoActividadObject tipo, String descripcion, int costo, int cupos, boolean reservable, CentroDeportivoObject centroDeportivo, List<InscripcionesActividadesObject> actividadesInscripto) {
        this.nombre = nombre;
        this.hora = hora;
        this.dia = dia;
        this.centroMail = centroMail;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupos = cupos;
        this.reservable = reservable;
        this.centroDeportivo = centroDeportivo;
        this.actividadesInscripto = actividadesInscripto;
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

    public TipoActividadObject getTipo() {
        return tipo;
    }

    public void setTipo(TipoActividadObject tipo) {
        this.tipo = tipo;
    }

    public List<InscripcionesActividadesObject> getActividadesInscripto() {
        return actividadesInscripto;
    }

    public void setActividadesInscripto(List<InscripcionesActividadesObject> actividadesInscripto) {
        this.actividadesInscripto = actividadesInscripto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
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

    public String getCentroMail() {
        return centroMail;
    }

    public void setCentroMail(String centroMail) {
        this.centroMail = centroMail;
    }

    @Override
    public String toString() {
        return "ActividadObject{" +
                "nombre='" + nombre + '\'' +
                ", hora=" + hora +
                ", dia=" + dia +
                ", centroMail='" + centroMail + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                ", cupos=" + cupos +
                ", reservable=" + reservable +
                ", centroDeportivo=" + centroDeportivo +
                '}';
    }
}
