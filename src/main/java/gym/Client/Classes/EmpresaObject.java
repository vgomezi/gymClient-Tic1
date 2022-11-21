package gym.Client.Classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EmpresaObject {

    private UserLoginObject userLoginReference;

    private String nombre;

    private String bono;

    private String mail;

    private String imagen;

    private Date dateCreada;

    public EmpresaObject() {
    }

    public EmpresaObject(UserLoginObject userLoginReference, String nombre, String bono, String mail, String imagen, Date dateCreada) {
        this.userLoginReference = userLoginReference;
        this.nombre = nombre;
        this.bono = bono;
        this.mail = mail;
        this.imagen = imagen;
        this.dateCreada = dateCreada;
    }

    public UserLoginObject getUserLoginReference() {
        return userLoginReference;
    }

    public void setUserLoginReference(UserLoginObject userLoginReference) {
        this.userLoginReference = userLoginReference;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBono() {return bono;}

    public void setBono(String bono) {this.bono = bono;}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getDateCreada() {
        return dateCreada;
    }

    public void setDateCreada(Date dateCreada) {
        this.dateCreada = dateCreada;
    }

    @Override
    public String toString() {
        return "EmpresaObject{" +
                "userLoginReference=" + userLoginReference +
                ", nombre='" + nombre + '\'' +
                ", bono='" + bono + '\'' +
                ", mail='" + mail + '\'' +
                ", imagen='" + imagen + '\'' +
                ", dateCreada=" + dateCreada +
                '}';
    }
}
