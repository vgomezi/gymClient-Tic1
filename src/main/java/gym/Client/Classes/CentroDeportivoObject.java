package gym.Client.Classes;

import java.util.List;

public class CentroDeportivoObject {

    private UserLoginObject userLogin;

    private String nombre;


    private String mail;


    private List<ActividadObject> listaActividades;


    private List<PagoObject> pagos;

    public CentroDeportivoObject() {
    }

    public CentroDeportivoObject(UserLoginObject userLogin, String nombre, String mail) {
        this.userLogin = userLogin;
        this.nombre = nombre;
        this.mail = mail;
    }

    public CentroDeportivoObject(UserLoginObject userLogin, String nombre, String mail, List<ActividadObject> listaActividades, List<PagoObject> pagos) {
        this.userLogin = userLogin;
        this.nombre = nombre;
        this.mail = mail;
        this.listaActividades = listaActividades;
        this.pagos = pagos;
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

    public List<ActividadObject> getListaActividades() {
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
    }

    @Override
    public String toString() {
        return "CentroDeportivoObject{" +
                "userLogin=" + userLogin +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", listaActividades=" + listaActividades +
                ", pagos=" + pagos +
                '}';
    }
}
