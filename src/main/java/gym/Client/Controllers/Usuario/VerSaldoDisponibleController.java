package gym.Client.Controllers.Usuario;

import gym.Client.Controllers.Empresa.CrearUsuarioController;
import gym.Client.Controllers.Empresa.MainEmpresaController;
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
import org.springframework.stereotype.Component;

@Component
public class VerSaldoDisponibleController {

    @FXML
    private Label saldoDisponibleLabel;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainUsuarioController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/MainUsuario.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Label getSaldoDisponibleLabel() {
        return saldoDisponibleLabel;
    }

    public void setSaldoDisponibleLabel(Label saldoDisponibleLabel) {
        this.saldoDisponibleLabel = saldoDisponibleLabel;
    }

}