package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.*;

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

    @FXML
    protected void onBorrarActividadButtonClick() {}


}