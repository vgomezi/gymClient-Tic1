package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class EliminarUsuarioController {


    @FXML
    private Label Email;

    @FXML
    private TextField emailtext;

    @FXML
    private Button eliminarboton;

    @FXML
    private Button cancelarboton;

    @FXML
    protected void onEliminarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}