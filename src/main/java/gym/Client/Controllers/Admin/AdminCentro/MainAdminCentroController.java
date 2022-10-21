package gym.Client.Controllers.Admin.AdminCentro;

import gym.Client.Controllers.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class MainAdminCentroController {

    public String usuarioAdminCentroMain;
    @FXML
    private Button registrarCentroBoton;

    @FXML
    private Button actualizarCentroBoton;

    @FXML
    private Button borrarCentroBoton;

    @FXML
    private Button buscarCentroBoton;

    public String getUsuarioAdminCentroMain() {
        return usuarioAdminCentroMain;
    }

    public void setUsuarioAdminCentroMain(String usuarioAdminCentroMain) {
        this.usuarioAdminCentroMain = usuarioAdminCentroMain;
    }

    @FXML
    protected void onRegistrarCentroButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/RegistrarCentro.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Registrar Centro Deportivo");
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
    protected void onActualizarCentroButtonClick() {}

    @FXML
    protected void onBuscarCentroButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/BuscarCentro.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Buscar Centro Deportivo");
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
    protected void onEliminarCentroButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/EliminarCentro.fxml"));

            EliminarCentroController eliminarCentroController = fxmlLoader.getController();
            eliminarCentroController.setUsuarioAdminCentroEliminar(this.usuarioAdminCentroMain);

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Eliminar Centro Deportivo");
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