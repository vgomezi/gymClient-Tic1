package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class MainCentroController {


    @FXML
    private Button registrarIngresoUsuarioBoton;

    @FXML
    private Button actualizarActividadBoton;

    @FXML
    private Button registrarActividadBoton;

    @FXML
    private Button buscarActividadBoton;

    @FXML
    private Button eliminarActividadBoton;

    @FXML
    protected void onRegistrarIngresoUsuarioButtonClick() {}

    @FXML
    protected void onActualizarActividadClick() {}

    @FXML
    protected void onRegistrarActividadButtonClick() {}

    @FXML
    protected void onBuscarActividadButtonClick() {}

    @FXML
    protected void onEliminarActividadButtonClick() {}


}