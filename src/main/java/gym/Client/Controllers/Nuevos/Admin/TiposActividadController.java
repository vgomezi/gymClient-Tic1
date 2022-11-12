package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.TipoActividadObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TiposActividadController {

    @FXML
    private VBox tipoActividadVBox;

    @FXML
    private Label tipoLabel;

    public void setearDatos(TipoActividadObject tipoActividadObject) {
        tipoLabel.setText(tipoActividadObject.getTipo());
        tipoActividadVBox.setStyle("-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }
}
