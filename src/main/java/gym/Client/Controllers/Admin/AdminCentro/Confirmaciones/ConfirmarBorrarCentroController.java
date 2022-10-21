package gym.Client.Controllers.Admin.AdminCentro.Confirmaciones;

import com.mashape.unirest.http.HttpResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class ConfirmarBorrarCentroController {

    @FXML
    public Button confirmarButton;

    @FXML
    public Button cancelarButton;

    @FXML
    private Label errorLabel;

    public void displayNombreCentro(String nombreCentro) {
        errorLabel.setText("Confirme que desea eliminar el centro " + nombreCentro);
    }

    @FXML
    protected void onConfirmarButtonClick() {
        System.out.println("Se borrará el centro");

    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }




}
