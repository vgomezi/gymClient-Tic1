package gym.Client.Classes;

public class PagoObject {
    private String empresaMailPago;


    private String centroMailPago;

    private Integer monto;

    private EmpresaObject empresa;

    private CentroDeportivoObject centroDeportivo;

    public PagoObject() {
        }

    public PagoObject(String empresaMailPago, String centroMailPago, Integer monto) {
            this.empresaMailPago = empresaMailPago;
            this.centroMailPago = centroMailPago;
            this.monto = monto;
            }

    public String getEmpresaMailPago() {
        return empresaMailPago;
    }

    public void setEmpresaMailPago(String empresaMailPago) {
        this.empresaMailPago = empresaMailPago;
    }

    public String getCentroMailPago() {
        return centroMailPago;
    }

    public void setCentroMailPago(String centroMailPago) {
        this.centroMailPago = centroMailPago;
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

    public CentroDeportivoObject getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivoObject centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
