package gym.Client.Controllers.Admin.AdminEmpresa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class BuscarEmpresaController {

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button buscarEmpresaBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onBuscarButtonClick() {
        System.out.println("Se buscará la empresa");
    }

    @FXML
    protected void onCancelarButtonClick() {}


}