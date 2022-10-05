package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class RegistroIngresoUsuairoController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label idActividadLabel;

    @FXML
    private TextField idActividadText;

    @FXML
    private Button registrarUsuarioBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onRegistrarUsuarioButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}