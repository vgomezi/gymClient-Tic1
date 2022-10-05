package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class MainEmpresaController {


    @FXML
    private Button crearUsuarioBoton;

    @FXML
    private Button buscarUsuarioBoton;

    @FXML
    private Button eliminarUsuarioBoton;

    @FXML
    private Button actualizarUsuarioBoton;

    @FXML
    protected void onCrearUsuarioButtonClick() {}

    @FXML
    protected void onBuscarUsuarioButtonClick() {}

    @FXML
    protected void onEliminarUsuarioButtonClick() {
        System.out.println("Belen");
    }

    @FXML
    protected void onActualizarUsuarioButtonClick() {}


}