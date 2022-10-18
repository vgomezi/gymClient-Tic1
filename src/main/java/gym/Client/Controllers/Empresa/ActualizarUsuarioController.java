package gym.Client.Controllers.Empresa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class ActualizarUsuarioController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label apellidoLabel;

    @FXML
    private TextField apellidoText;

    @FXML
    private Label telefonoLabel;

    @FXML
    private TextField telefonoText;

    @FXML
    private Button actualizarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onActualizarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        nombreText.clear();
        apellidoText.clear();
        telefonoText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}