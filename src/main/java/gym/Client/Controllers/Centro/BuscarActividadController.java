package gym.Client.Controllers.Centro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class BuscarActividadController {


    @FXML
    private Label idActividadLabel;

    @FXML
    private TextField idActividadText;

    @FXML
    private Button buscarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onBuscarButtonClick() {}

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        idActividadText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}