package gym.Client.Controllers.Admin;

import gym.Client.Controllers.Admin.AdminCentro.MainAdminCentroController;
import gym.Client.Controllers.Admin.AdminEmpresa.MainAdminEmpresaController;
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
public class MainAdministradorController {

    public String usuarioAdminMain;

    @FXML
    private Button administrarEmpresasBoton;

    @FXML
    private Button administrarCentrosBoton;

    public String getUsuarioAdminMain() {
        return usuarioAdminMain;
    }

    public void setUsuarioAdminMain(String usuarioAdminMain) {
        this.usuarioAdminMain = usuarioAdminMain;
    }

    @FXML
    protected void onAdministrarEmpresasButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));

            MainAdminEmpresaController mainAdminEmpresaController = fxmlLoader.getController();
            mainAdminEmpresaController.setUsuarioAdminEmpresaMain(usuarioAdminMain);

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Administrar Empresa");
            stage.getIcons().add(new Image("FitnessIcon.png"));
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

            MainAdminCentroController mainAdminCentroController = fxmlLoader.getController();
            mainAdminCentroController.setUsuarioAdminCentroMain(this.usuarioAdminMain);

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Administrar Centro");
            stage.getIcons().add(new Image("FitnessIcon.png"));
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}