package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Administrador {

    private String contrasena;

    public Administrador(String contrasena) {
        this.contrasena = contrasena;
    }

    public Administrador() {
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
