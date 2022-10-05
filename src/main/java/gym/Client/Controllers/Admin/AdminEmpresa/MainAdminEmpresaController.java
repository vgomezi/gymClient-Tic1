package gym.Client.Controllers.Admin.AdminEmpresa;

import gym.Client.Controllers.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class MainAdminEmpresaController {

    @FXML
    private Button registrarEmpresaBoton;

    @FXML
    private Button actualizarEmpresaBoton;

    @FXML
    private Button eliminarEmpresaBoton;

    @FXML
    private Button buscarEmpresaBoton;

    @FXML
    protected void onRegistrarEmpresaButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/RegistrarEmpresa.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onActualizarEmpresaButtonClick() {}

    @FXML
    protected void onEliminarEmpresaButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/EliminarEmpresa.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onBuscarEmpresaButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/BuscarEmpresa.fxml"));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
