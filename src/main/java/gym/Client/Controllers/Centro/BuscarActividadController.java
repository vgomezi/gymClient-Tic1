package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BuscarActividadController {


    @FXML
    private TextField Id;

    @FXML
    private Label idactividadtext;

    @FXML
    private Button onBuscaButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onBuscarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}