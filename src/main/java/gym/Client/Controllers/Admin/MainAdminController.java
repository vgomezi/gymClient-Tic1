package gym.Client.Controllers.Admin;

import gym.Client.Controllers.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;


@Controller
public class MainAdminController {

    //hola equipo

    @FXML
    private Button administrarEmpresasBoton;

    @FXML
    private Button administrarCentrosBoton;

    @FXML
    protected void onAdministrarEmpresasButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Empresa");
            stage.getIcons().add(new Image("GymIcon.png"));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onAdministrarCentrosButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Centro");
            stage.getIcons().add(new Image("GymIcon.png"));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}