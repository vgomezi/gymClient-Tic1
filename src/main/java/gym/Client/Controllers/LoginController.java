package gym.Client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    protected void onCancelarButtonClick(ActionEvent event) {
        if (emailText.getText() == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Login Centro");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Error");
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            emailText.clear();
            contrasenaText.clear();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }




}