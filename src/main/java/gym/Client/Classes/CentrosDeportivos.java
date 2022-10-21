package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

public class CentrosDeportivos {

    private String nombre;

    private String contrasena;

    private String mail;

    private List<Actividades> listaActividades;

    private List<Integer> listaSaldo;

    public CentrosDeportivos() {
    }

    public CentrosDeportivos(String nombre, String contrasena, String mail, List<Actividades> listaActividades, List<Integer> listaSaldo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.mail = mail;
        this.listaActividades = listaActividades;
        this.listaSaldo = listaSaldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Actividades> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<Integer> getListaSaldo() {
        return listaSaldo;
    }

    public void setListaSaldo(List<Integer> listaSaldo) {
        this.listaSaldo = listaSaldo;
    }
}
