package gym.Client.Classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administrador")
@Component
public class Administrador {

    private @Id String contrasena;

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
