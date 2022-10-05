package gym.Client.Controllers.Admin.AdminCentro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


@Component
public class EliminarCentroController {


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
    protected void onCancelarButtonClick() {}


}