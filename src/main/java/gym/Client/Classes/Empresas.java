package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

public class Empresas {

    private String nombre;

    private String contrasena;

    private String mail;

    private List<Usuarios> listaUsuarios;

    private List<Integer> listaSaldo;

    public Empresas(String nombre, String contrasena, String mail, List<Usuarios> listaUsuarios, List<Integer> listaSaldo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.mail = mail;
        this.listaUsuarios = listaUsuarios;
        this.listaSaldo = listaSaldo;
    }

    public Empresas() {
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

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Integer> getListaSaldo() {
        return listaSaldo;
    }

    public void setListaSaldo(List<Integer> listaSaldo) {
        this.listaSaldo = listaSaldo;
    }
}
