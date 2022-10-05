package gym.Client.Controllers.Admin.AdminCentro;

import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class BuscarCentroController {

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button buscarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onBuscarButtonClick() {
        System.out.println("Se buscará el centro");
    }

    @FXML
    protected void onCancelarButtonClick() {}


}