package gym.Client.Controllers.Empresa;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BuscarUsuarioController {


    @FXML
    private Label Email;

    @FXML
    private TextField emailtext;

    @FXML
    private Button onBuscarButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onBuscarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}