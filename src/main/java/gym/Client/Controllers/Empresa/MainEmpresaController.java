package gym.Client.Controllers.Empresa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;

@Controller
public class MainEmpresaController {

    public String usuarioEmpresaMain;

    @FXML
    private Button crearUsuarioBoton;

    @FXML
    private Button buscarUsuarioBoton;

    @FXML
    private Button actualizarUsuarioBoton;

    @FXML
    private Button verSaldosBoton;

    @FXML
    private Button eliminarUsuarioBoton;

    public String getUsuarioEmpresaMain() {
        return usuarioEmpresaMain;
    }

    public void setUsuarioEmpresaMain(String usuarioEmpresaMain) {
        this.usuarioEmpresaMain = usuarioEmpresaMain;
    }


    @FXML
    protected void onCrearUsuarioButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesEmpresa/CrearUsuario.fxml"));

            CrearUsuarioController crearUsuarioController = fxmlLoader.getController();
            crearUsuarioController.setUsuarioEmpresaCrearUsuario(usuarioEmpresaMain);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Crear Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    protected void onBuscarUsuarioButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesEmpresa/BuscarUsuario.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    protected void onActualizarUsuarioButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesEmpresa/ActualizarUsuario.fxml"));


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Actualizar Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onVerSaldosButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesEmpresa/VerSaldos.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Ver Saldos");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onEliminarUsuarioButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesEmpresa/EliminarUsuario.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Eliminar Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}