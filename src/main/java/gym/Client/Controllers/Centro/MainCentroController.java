package gym.Client.Controllers.Centro;

import gym.Client.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class MainCentroController {


    @FXML
    private Button registrarIngresoUsuarioBoton;

    @FXML
    private Button actualizarActividadBoton;

    @FXML
    private Button registrarActividadBoton;

    @FXML
    private Button buscarActividadBoton;

    @FXML
    private Button eliminarActividadBoton;


    @FXML
    protected void onActualizarActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesCentroPrueba/ActualizarActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Actualizar Actividad");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onRegistrarActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesCentroPrueba/RegistrarActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Registrar Actividad");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onBuscarActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesCentroPrueba/BuscarActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Buscar Actividad");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onEliminarActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesCentroPrueba/EliminarActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Eliminar Actividad");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onRegistrarIngresoUsuarioButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesCentroPrueba/RegistrarIngresoUsuario.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Registrar Ingreso de Usuario");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}