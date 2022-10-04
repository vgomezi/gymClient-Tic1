package gym.Client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class LoginController {


    @FXML
    private TextField Email;

    @FXML
    private Label emailtext;

    @FXML
    private TextField Contraseña;

    @FXML
    private Label contrasenatext;

    @FXML
    private Button onIngresarActividadButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    private Button onEntrarEmpresaButtonClick;

    @FXML
    private Button onEntrarCDButtonClick;

    @FXML
    private Button onAButtonClick;

    @FXML
    protected void onIngresarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}

    @FXML
    protected void onEntrarEmpresaButtonClick() {}

    @FXML
    protected void onEntrarCDButtonClick() {}

    @FXML
    protected void onAButtonClick() {}


}