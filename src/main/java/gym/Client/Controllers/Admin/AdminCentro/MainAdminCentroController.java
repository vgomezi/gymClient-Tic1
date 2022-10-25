package gym.Client.Controllers.Admin.AdminCentro;

import gym.Client.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.Button;
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

    @FXML
    private Button volverBoton;

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
            //stage.getIcons().add(new Image("FitnessIcon.png"));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onActualizarCentroButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/ActualizarCentro.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Actualizar Centro Deportivo");
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
    protected void onBuscarCentroButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/BuscarCentro.fxml"));
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

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/MainAdmin.fxml"));

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