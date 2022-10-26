package gym.Client.Classes;

import javax.persistence.Id;
import java.util.Objects;

public class TipoActividadObject {

    private String tipo;

    public TipoActividadObject(String tipo) {
        this.tipo = tipo;
    }

    public TipoActividadObject() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoActividadObject that = (TipoActividadObject) o;
        return Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo);
    }

    @Override
    public String toString() {
        return "TipoActividad{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
