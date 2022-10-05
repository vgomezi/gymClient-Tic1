package gym.Client.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private TextField contrasenaText;

    @FXML
    private Button ingresarBoton;

    @FXML
    private Button cancelarBoton;


    @FXML
    protected void onIngresarButtonClick() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));
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
    protected void onCancelarButtonClick() {}
    /*

        try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesEmpresa/MainEmpresa.fxml"));
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

     */




}