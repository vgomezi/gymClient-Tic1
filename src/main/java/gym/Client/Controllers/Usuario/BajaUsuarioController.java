package gym.Client.Controllers.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BajaUsuarioController {


    @FXML
    private TextField Email;

    @FXML
    private Label emailtext;

    @FXML
    private TextField Contraseña;

    @FXML
    private Label contrasenatext;

    @FXML
    private Button onBajaButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onBajaButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}