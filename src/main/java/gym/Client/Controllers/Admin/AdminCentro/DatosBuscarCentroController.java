package gym.Client.Controllers.Admin.AdminCentro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class DatosBuscarCentroController {

    @FXML
    private Label emailLabel;

    @FXML
    private Label emailDatoLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nombreDatoLabel;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(DatosBuscarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/BuscarCentro.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Centro");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public Label getEmailDatoLabel() {
        return emailDatoLabel;
    }

    public void setEmailDatoLabel(Label emailDatoLabel) {
        this.emailDatoLabel = emailDatoLabel;
    }

    public Label getNombreDatoLabel() {
        return nombreDatoLabel;
    }

    public void setNombreDatoLabel(Label nombreDatoLabel) {
        this.nombreDatoLabel = nombreDatoLabel;
    }
}