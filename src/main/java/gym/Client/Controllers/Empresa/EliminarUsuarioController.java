package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class EliminarUsuarioController {


    @FXML
    private TextField Email;

    @FXML
    private Label emailtext;

    @FXML
    private Button onEliminarButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onEliminarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}