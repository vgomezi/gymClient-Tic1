package gym.Client.Classes;

public class PagoUsuEmpObject {
    private String empresaMailPago;

    private String empleadoMailPago;

    private Integer monto;

    private EmpresaObject empresa;

    private EmpleadoObject empleado;

    public PagoUsuEmpObject() {
        }

    public PagoUsuEmpObject(String empresaMailPago, String empleadoMailPago, Integer monto) {
            this.empresaMailPago = empresaMailPago;
            this.empleadoMailPago = empleadoMailPago;
            this.monto = monto;
            }

    public String getEmpresaMailPago() {
        return empresaMailPago;
    }

    public void setEmpresaMailPago(String empresaMailPago) {
        this.empresaMailPago = empresaMailPago;
    }

    public String getEmpleadoMailPago() {
        return empleadoMailPago;
    }

    public void setEmpleadoMailPago(String empleadoMailPago) {
        this.empleadoMailPago = empleadoMailPago;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public EmpresaObject getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaObject empresa) {
        this.empresa = empresa;
    }

    public EmpleadoObject getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoObject empleado) {
        this.empleado = empleado;
    }
}
