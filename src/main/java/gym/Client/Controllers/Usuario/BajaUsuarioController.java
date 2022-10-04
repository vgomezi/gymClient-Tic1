package gym.Client.Controllers.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BajaUsuarioController {


    @FXML
    private Label Email;

    @FXML
    private TextField emailtext;

    @FXML
    private Label Contraseña;

    @FXML
    private TextField contrasenatext;

    @FXML
    private Button bajaboton;

    @FXML
    private Button cancelarboton;

    @FXML
    protected void onBajaButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}