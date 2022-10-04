package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RegistroIngresoController {


    @FXML
    private TextField Email;

    @FXML
    private Label emailtext;

    @FXML
    private TextField Id;

    @FXML
    private Label idactividadtext;

    @FXML
    private Button onRegistrarButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onRegistrarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}



}