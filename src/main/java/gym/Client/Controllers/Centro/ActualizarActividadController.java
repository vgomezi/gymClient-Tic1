package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class ActualizarActividadController {


    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label tipoLabel;

    @FXML
    private TextField tipoText;

    @FXML
    private Label descripcionLabel;

    @FXML
    private TextField descripcionText;

    @FXML
    private Label horaLabel;

    @FXML
    private TextField horaText;

    @FXML
    private Label diaLabel;

    @FXML
    private TextField diaText;

    @FXML
    private Label reservableLabel;

    @FXML
    private TextField reservableText;

    @FXML
    private Label cuposLabel;

    @FXML
    private TextField cuposText;

    @FXML
    private Button actualizarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onActualizarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}