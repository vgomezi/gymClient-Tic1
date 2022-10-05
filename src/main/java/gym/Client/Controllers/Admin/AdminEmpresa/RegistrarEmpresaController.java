package gym.Client.Controllers.Admin.AdminEmpresa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;


@Component
public class RegistrarEmpresaController {


    @FXML
    private Label nombreEmpresaLabel;

    @FXML
    private Label emailEmpresaLabel;

    @FXML
    private Label contrasenaEmpresaLabel;

    @FXML
    private TextField nombreEmpresaText;

    @FXML
    private TextField emailEmpresaText;

    @FXML
    private TextField contrasenaEmpresaText;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onCrearButtonClick() {
        System.out.println("Belen creo la empresa");
    }

    @FXML
    protected void onCancelarButtonClick() {
        System.out.println("Accion cancelada");
    }
}