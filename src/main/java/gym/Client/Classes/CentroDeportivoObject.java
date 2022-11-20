package gym.Client.Classes;

import java.util.Date;
import java.util.List;

public class CentroDeportivoObject {

    private UserLoginObject userLogin;

    private String nombre;


    private String mail;

    private String imagen;

    private Date dateCreado;


    //private List<ActividadObject> listaActividades;


    //private List<PagoObject> pagos;

    public CentroDeportivoObject() {
    }

    public CentroDeportivoObject(UserLoginObject userLogin, String nombre, String mail, String imagen, Date dateCreado) {
        this.userLogin = userLogin;
        this.nombre = nombre;
        this.mail = mail;
        this.imagen = imagen;
        this.dateCreado = dateCreado;
    }

    public UserLoginObject getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLoginObject userLogin) {
        this.userLogin = userLogin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public Date getDateCreado() {
        return dateCreado;
    }

    public void setDateCreado(Date dateCreado) {
        this.dateCreado = dateCreado;
    }

    /*public List<ActividadObject> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<ActividadObject> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<PagoObject> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoObject> pagos) {
        this.pagos = pagos;
    }*/

    @Override
    public String toString() {
        return "CentroDeportivoObject{" +
                "userLogin=" + userLogin +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", imagen='" + imagen + '\'' +
                ", dateCreado=" + dateCreado +
                '}';
    }
}
