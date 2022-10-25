package gym.Client.Controllers.Admin.AdminEmpresa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class MainAdminEmpresaController {

    public String usuarioAdminEmpresaMain;

    @FXML
    private Button registrarEmpresaBoton;

    @FXML
    private Button actualizarEmpresaBoton;

    @FXML
    private Button eliminarEmpresaBoton;

    @FXML
    private Button buscarEmpresaBoton;

    @FXML
    private Button volverBoton;

    public String getUsuarioAdminEmpresaMain() {
        return usuarioAdminEmpresaMain;
    }

    public void setUsuarioAdminEmpresaMain(String usuarioAdminEmpresaMain) {
        this.usuarioAdminEmpresaMain = usuarioAdminEmpresaMain;
    }

    @FXML
    protected void onRegistrarEmpresaButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/RegistrarEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Registrar Empresa");
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
    protected void onActualizarEmpresaButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/ActualizarEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            EliminarEmpresaController eliminarEmpresaController = fxmlLoader.getController();
            eliminarEmpresaController.setUsuarioAdminEmpresaEliminar(this.usuarioAdminEmpresaMain);

            stage.setTitle("Actualizar Empresa");
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
    protected void onEliminarEmpresaButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/EliminarEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            EliminarEmpresaController eliminarEmpresaController = fxmlLoader.getController();
            eliminarEmpresaController.setUsuarioAdminEmpresaEliminar(this.usuarioAdminEmpresaMain);

            stage.setTitle("Eliminar Empresa");
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
    protected void onBuscarEmpresaButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/BuscarEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Empresa");
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
            Parent root1 = (Parent) fxmlLoader.load(MainAdminEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/MainAdmin.fxml"));

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
