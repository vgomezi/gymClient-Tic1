package gym.Client.Controllers.Admin.AdminEmpresa;

import gym.Client.Controllers.Empresa.MainEmpresaController;
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
public class DatosBuscarEmpresaController {

    @FXML
    private Label emailLabel;

    @FXML
    private Label emailDatoLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nombreDatoLabel;

    @FXML
    private Label bonoLabel;

    @FXML
    private Label bonoDatoLabel;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(DatosBuscarEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/BuscarEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}