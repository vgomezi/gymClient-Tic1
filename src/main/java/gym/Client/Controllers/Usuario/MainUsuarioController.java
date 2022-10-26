package gym.Client.Controllers.Usuario;

import gym.Client.Classes.ActividadObject;
import gym.Client.Controllers.Empresa.MainEmpresaController;
import gym.Client.Controllers.Usuario.Actividades.ActividadesPaneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainUsuarioController {


    @FXML
    private Button darseBajaBoton;

    @FXML
    private Button bajarseActividadBoton;

    @FXML
    private Button tipoActividadBoton;

    @FXML
    private Button verSaldoDisponibleBoton;

    @FXML
    protected void onDarseBajaButtonClick(ActionEvent event) {
        try {
        Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuario/DarseBajaUsuario.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Darse de Baja");
        stage.setScene(new Scene(root1));
        stage.show();

    } catch (Exception ex) {
        System.out.println(ex.toString());
        System.out.println("Error");
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    protected void onBajarseActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuario/BajarseUsuarioActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Darse de Baja");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onTipoActividadButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuario/TipoActividad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Tipo Actividad");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void onVerSaldoDisponibleClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/formularios/OpcionesUsuario/VerSaldoDisponible.fxml"));

            Integer verSaldoDispo = 90;

            VerSaldoDisponibleController verSaldoDisponibleController = fxmlLoader.getController();
            verSaldoDisponibleController.getSaldoDisponibleLabel().setText(verSaldoDispo.toString());

            if(verSaldoDispo < 0) {
                verSaldoDisponibleController.getSaldoDisponibleLabel().setTextFill(Color.color(255, 0, 0));
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Ver Saldo Dispoible");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}