package gym.Client.Classes;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserLoginObject {

    private String mail;

    private String contrasena;

    private String tipoDeUsuario;

    public UserLoginObject() {
    }

    public UserLoginObject(String mail, String contrasena, String tipoDeUsuario) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.tipoDeUsuario = tipoDeUsuario;
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

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
}
