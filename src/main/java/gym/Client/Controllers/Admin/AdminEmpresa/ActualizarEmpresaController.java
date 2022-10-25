package gym.Client.Controllers.Admin.AdminEmpresa;

import gym.Client.Controllers.Admin.AdminCentro.BuscarCentroController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarEmpresaController {

    @FXML
    public Label emailLabel;

    @FXML
    public Label emailDatoLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public TextField nombreText;

    @FXML
    public Label bonoLabel;

    @FXML
    public TextField bonoText;


    @FXML
    private Button volverBoton;



    //Poner Pantalla Intermedia Para Obtener Mail y castear datos
    public void onActualizarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();
        String bono = bonoText.getText();


    }

    public void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        bonoText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(ActualizarEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }
}
