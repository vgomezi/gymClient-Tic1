package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class RegistrarActividadController {


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
    private TextField diatext;

    @FXML
    private Label reservableLabel;

    @FXML
    private TextField reservableText;

    @FXML
    private Label cuposLabel;

    @FXML
    private TextField cuposText;

    @FXML
    private Button registrarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onRegistrarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}