package gym.Client.Controllers.Admin.AdminEmpresa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class EliminarEmpresaController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onEliminarButtonClick() {
        System.out.println("Se elimino la empresa");
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}