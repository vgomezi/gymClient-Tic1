package gym.Client.Controllers.Usuario;

import gym.Client.Controllers.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TipoActividadController {

    @FXML
    private Button canchasBoton;

    @FXML
    private Button nauticasBoton;

    @FXML
    private Button gimnasioSalaBoton;

    @FXML
    private Button volverBoton;

    @FXML
    protected void onCanchasButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/Actividades/Canchas.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Canchas");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    protected void onNauticasButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/Actividades/Nauticas.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Nauticas");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    protected void onGimnasioSalaButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/Actividades/GimnasioSala.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("GimnasioSala");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    protected void onVolverButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesUsuarioPrueba/MainUsuario.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Volver");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}