package gym.Client.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ContrasenaAdminController {


    @FXML
    private TextField Contraseña;

    @FXML
    private Label contrasenatext;

    @FXML
    private Button onIngresarButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onIngresarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}