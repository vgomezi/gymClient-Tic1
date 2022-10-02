package gym.Client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private TextField Email;

    @FXML
    private TextField Contraseña;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField contrasenatext;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}

    @FXML
    protected void onEntrarEmpresaButtonClick() {}

    @FXML
    protected void onEntrarCDButtonClick() {}

    @FXML
    protected void onAButtonClick() {}


}