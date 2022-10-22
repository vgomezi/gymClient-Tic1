package gym.Client.Controllers.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class MainUsuarioController {


    @FXML
    private Button darseBajaBoton;

    @FXML
    private Button bajarseActividadBoton;

    @FXML
    private Button verRegistrarBoton;

    @FXML
    protected void onDarseBajaButtonClick(ActionEvent event) {
        try {
        Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuarioPrueba/DarseBajaUsuario.fxml"));
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
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuarioPrueba/BajarseUsuarioActividad.fxml"));
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
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuarioPrueba/TipoActividad.fxml"));
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


}