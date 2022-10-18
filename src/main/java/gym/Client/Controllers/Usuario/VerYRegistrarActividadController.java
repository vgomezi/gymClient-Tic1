package gym.Client.Controllers.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class VerYRegistrarActividadController {


    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label tipoLabel;

    @FXML
    private TextField tipoText;

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
    private Button filtrarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onFiltrarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}