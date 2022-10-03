package gym.Client.Controllers.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class BajaUsuarioController {


    @FXML
    private TextField Email;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField Contraseña;

    @FXML
    private TextField contrasenatext;

    @FXML
    protected void onBajaButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}