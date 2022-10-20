package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
@Component
public class Usuarios {

    private String nombre;

    private String apellido;

    private @Id String mail;

    private String contrasena;

    private Empresas empresa;

    private int saldoDisponible;

    public Usuarios() {
    }

    public Usuarios(String nombre, String apellido, String mail, String contrasena, Empresas empresa, int saldoDisponible) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasena = contrasena;
        this.empresa = empresa;
        this.saldoDisponible = saldoDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public int getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(int saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}
