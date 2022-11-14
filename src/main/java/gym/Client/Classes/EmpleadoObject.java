package gym.Client.Classes;

import java.util.Date;

public class EmpleadoObject {

    private UserLoginObject userLoginReference;

    private String nombre;

    private String apellido;

    private String mail;

    private String telefono;

    private EmpresaObject empresa;

    private int saldoDisponible;

    private Date dateCreado;

    private int deuda;

    private String imagen;

    public EmpleadoObject(UserLoginObject userLoginReference, String nombre, String apellido, String mail, String telefono, EmpresaObject empresa, int saldoDisponible, Date dateCreado, int deuda, String imagen) {
        this.userLoginReference = userLoginReference;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.empresa = empresa;
        this.saldoDisponible = saldoDisponible;
        this.dateCreado = dateCreado;
        this.deuda = deuda;
        this.imagen = imagen;
    }

    public EmpleadoObject() {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EmpresaObject getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaObject empresa) {
        this.empresa = empresa;
    }

    public int getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(int saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Date getDateCreado() {
        return dateCreado;
    }

    public void setDateCreado(Date dateCreado) {
        this.dateCreado = dateCreado;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "EmpleadoObject{" +
                "userLoginReference=" + userLoginReference +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono='" + telefono + '\'' +
                ", empresa=" + empresa +
                ", saldoDisponible=" + saldoDisponible +
                ", dateCreado=" + dateCreado +
                ", deuda=" + deuda +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
