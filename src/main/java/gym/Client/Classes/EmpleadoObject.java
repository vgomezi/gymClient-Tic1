package gym.Client.Classes;

public class EmpleadoObject {

    private UserLoginObject userLoginReference;

    private String nombre;

    private String apellido;

    private String mail;

    private String telefono;

    private EmpresaObject empresa;

    private int saldoDisponible;

    private int saldoOriginal;

    private int deuda;

    public EmpleadoObject(UserLoginObject userLoginReference, String nombre, String apellido, String mail, String telefono, EmpresaObject empresa, int saldoDisponible, int saldoOriginal, int deuda) {
        this.userLoginReference = userLoginReference;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.telefono = telefono;
        this.empresa = empresa;
        this.saldoDisponible = saldoDisponible;
        this.saldoOriginal = saldoOriginal;
        this.deuda = deuda;
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

    public int getSaldoOriginal() {
        return saldoOriginal;
    }

    public void setSaldoOriginal(int saldoOriginal) {
        this.saldoOriginal = saldoOriginal;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }
}
