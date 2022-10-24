package gym.Client.Classes;

import javax.persistence.*;
import java.util.List;

public class EmpresaObject {

    private UserLoginObject userLoginReference;

    private String nombre;

    private String mail;

    private String bono;

    private List<EmpleadoObject> listaEmpleados;

    private List<PagoObject> pagos;

    public EmpresaObject(UserLoginObject userLoginReference, String nombre, String mail, String bono, List<EmpleadoObject> listaEmpleados, List<PagoObject> pagos) {
        this.userLoginReference = userLoginReference;
        this.nombre = nombre;
        this.mail = mail;
        this.bono = bono;
        this.listaEmpleados = listaEmpleados;
        this.pagos = pagos;

    }

    public EmpresaObject(UserLoginObject userLoginReference, String nombre, String mail) {
        this.userLoginReference = userLoginReference;
        this.nombre = nombre;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBono() {return bono;}

    public void setBono(String bono) {this.bono = bono;}

    public List<EmpleadoObject> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<EmpleadoObject> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<PagoObject> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoObject> pagos) {
        this.pagos = pagos;
    }
}
