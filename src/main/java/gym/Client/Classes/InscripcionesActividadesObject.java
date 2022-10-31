package gym.Client.Classes;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class InscripcionesActividadesObject {

    private String empleadoMailInscripcion;

    private String actividadNombreInscripcion;

    private LocalDate actividadDiaInscripcion;

    private LocalTime actividadHoraInscripcion;

    private String actividadCentroInscripcion;

    private boolean asistencia;

    private EmpleadoObject empleado;

    private ActividadObject actividad;

    private String tipoReserva;

    public InscripcionesActividadesObject() {
    }

    public InscripcionesActividadesObject(String empleadoMailInscripcion, String actividadNombreInscripcion, LocalDate actividadDiaInscripcion, LocalTime actividadHoraInscripcion, String actividadCentroInscripcion, boolean asistencia, EmpleadoObject empleado, ActividadObject actividad, String tipoReserva) {
        this.empleadoMailInscripcion = empleadoMailInscripcion;
        this.actividadNombreInscripcion = actividadNombreInscripcion;
        this.actividadDiaInscripcion = actividadDiaInscripcion;
        this.actividadHoraInscripcion = actividadHoraInscripcion;
        this.actividadCentroInscripcion = actividadCentroInscripcion;
        this.asistencia = asistencia;
        this.empleado = empleado;
        this.actividad = actividad;
        this.tipoReserva = tipoReserva;
    }

    public InscripcionesActividadesObject(String empleadoMailInscripciones, String actividadNombreInscripcion, LocalDate actividadDiaInscripcion, LocalTime actividadHoraInscripcion, boolean asistencia, EmpleadoObject empleado, ActividadObject actividad) {
        this.empleadoMailInscripcion = empleadoMailInscripciones;
        this.actividadNombreInscripcion = actividadNombreInscripcion;
        this.actividadDiaInscripcion = actividadDiaInscripcion;
        this.actividadHoraInscripcion = actividadHoraInscripcion;
        this.asistencia = asistencia;
        this.empleado = empleado;
        this.actividad = actividad;
    }

    public String getEmpleadoMailInscripciones() {
        return empleadoMailInscripcion;
    }

    public void setEmpleadoMailInscripciones(String empleadoMailInscripciones) {
        this.empleadoMailInscripcion = empleadoMailInscripciones;
    }

    public String getActividadNombreInscripcion() {
        return actividadNombreInscripcion;
    }

    public void setActividadNombreInscripcion(String actividadNombreInscripcion) {
        this.actividadNombreInscripcion = actividadNombreInscripcion;
    }

    public LocalDate getActividadDiaInscripcion() {
        return actividadDiaInscripcion;
    }

    public void setActividadDiaInscripcion(LocalDate actividadDiaInscripcion) {
        this.actividadDiaInscripcion = actividadDiaInscripcion;
    }

    public LocalTime getActividadHoraInscripcion() {
        return actividadHoraInscripcion;
    }

    public void setActividadHoraInscripcion(LocalTime actividadHoraInscripcion) {
        this.actividadHoraInscripcion = actividadHoraInscripcion;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getEmpleadoMailInscripcion() {
        return empleadoMailInscripcion;
    }

    public void setEmpleadoMailInscripcion(String empleadoMailInscripcion) {
        this.empleadoMailInscripcion = empleadoMailInscripcion;
    }

    public String getActividadCentroInscripcion() {
        return actividadCentroInscripcion;
    }

    public void setActividadCentroInscripcion(String actividadCentroInscripcion) {
        this.actividadCentroInscripcion = actividadCentroInscripcion;
    }

    public EmpleadoObject getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoObject empleado) {
        this.empleado = empleado;
    }

    public ActividadObject getActividad() {
        return actividad;
    }

    public void setActividad(ActividadObject actividad) {
        this.actividad = actividad;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionesActividadesObject that = (InscripcionesActividadesObject) o;
        return asistencia == that.asistencia && Objects.equals(empleadoMailInscripcion, that.empleadoMailInscripcion) && Objects.equals(actividadNombreInscripcion, that.actividadNombreInscripcion) && Objects.equals(actividadDiaInscripcion, that.actividadDiaInscripcion) && Objects.equals(actividadHoraInscripcion, that.actividadHoraInscripcion) && Objects.equals(actividadCentroInscripcion, that.actividadCentroInscripcion) && Objects.equals(empleado, that.empleado) && Objects.equals(actividad, that.actividad) && Objects.equals(tipoReserva, that.tipoReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empleadoMailInscripcion, actividadNombreInscripcion, actividadDiaInscripcion, actividadHoraInscripcion, actividadCentroInscripcion, asistencia, empleado, actividad, tipoReserva);
    }

    @Override
    public String toString() {
        return "InscripcionesActividadesObject{" +
                "empleadoMailInscripcion='" + empleadoMailInscripcion + '\'' +
                ", actividadNombreInscripcion='" + actividadNombreInscripcion + '\'' +
                ", actividadDiaInscripcion=" + actividadDiaInscripcion +
                ", actividadHoraInscripcion=" + actividadHoraInscripcion +
                ", actividadCentroInscripcion='" + actividadCentroInscripcion + '\'' +
                ", asistencia=" + asistencia +
                ", empleado=" + empleado +
                ", actividad=" + actividad +
                ", tipoReserva='" + tipoReserva + '\'' +
                '}';
    }
}
