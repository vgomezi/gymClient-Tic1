package gym.Client.Controllers.Admin.AdminCentro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ActualizarCentroController {

    @FXML
    public Label emailLabel;

    @FXML
    public Label emailDatoLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public TextField nombreText;

    @FXML
    private Button volverBoton;


    //Poner Pantalla Intermedia Para Obtener Mail y castear datos
    public void onActualizarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();

    }


    public void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(ActualizarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}
