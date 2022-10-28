package gym.Client.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    private Date dateCreada;

    private String imagenActividad;

    private CentroDeportivoObject centroDeportivo;

    private List<InscripcionesActividadesObject> actividadesInscripto;

    public ActividadObject() {
    }

    public ActividadObject(String nombre, LocalTime hora, LocalDate dia, String centroMail, TipoActividadObject tipo, String descripcion, int costo, int cupos, boolean reservable, Date dateCreada, String imagenActividad, CentroDeportivoObject centroDeportivo) {
        this.nombre = nombre;
        this.hora = hora;
        this.dia = dia;
        this.centroMail = centroMail;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupos = cupos;
        this.reservable = reservable;
        this.dateCreada = dateCreada;
        this.imagenActividad = imagenActividad;
        this.centroDeportivo = centroDeportivo;
        this.actividadesInscripto = new ArrayList<>();
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

    public Date getDateCreada() {
        return dateCreada;
    }

    public void setDateCreada(Date dateCreada) {
        this.dateCreada = dateCreada;
    }

    public String getImagenActividad() {
        return imagenActividad;
    }

    public void setImagenActividad(String imagenActividad) {
        this.imagenActividad = imagenActividad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadObject that = (ActividadObject) o;
        return costo == that.costo && cupos == that.cupos && reservable == that.reservable && Objects.equals(nombre, that.nombre) && Objects.equals(hora, that.hora) && Objects.equals(dia, that.dia) && Objects.equals(centroMail, that.centroMail) && Objects.equals(tipo, that.tipo) && Objects.equals(descripcion, that.descripcion) && Objects.equals(dateCreada, that.dateCreada) && Objects.equals(imagenActividad, that.imagenActividad) && Objects.equals(centroDeportivo, that.centroDeportivo) && Objects.equals(actividadesInscripto, that.actividadesInscripto);
    }

    @Override
    public String toString() {
        return "ActividadObject{" +
                "nombre='" + nombre + '\'' +
                ", hora=" + hora +
                ", dia=" + dia +
                ", centroMail='" + centroMail + '\'' +
                ", tipo=" + tipo +
                ", descripcion='" + descripcion + '\'' +
                ", costo=" + costo +
                ", cupos=" + cupos +
                ", reservable=" + reservable +
                ", dateCreada=" + dateCreada +
                ", imagenActividad=" + imagenActividad +
                ", centroDeportivo=" + centroDeportivo +
                ", actividadesInscripto=" + actividadesInscripto +
                '}';
    }
}
