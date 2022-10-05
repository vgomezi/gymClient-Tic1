package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class EliminarActividadController {


    @FXML
    private Label idActividadLabel;

    @FXML
    private TextField idActividadText;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onEliminarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}