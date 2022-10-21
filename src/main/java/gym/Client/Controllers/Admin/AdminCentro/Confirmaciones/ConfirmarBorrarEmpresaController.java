package gym.Client.Controllers.Admin.AdminCentro.Confirmaciones;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class ConfirmarBorrarEmpresaController {

    public String usuarioAdminConfirmar;
    @FXML
    public Button confirmarBoton;

    @FXML
    public Button cancelarBoton;

    @FXML
    private Label contrasenaAdminLabel;

    @FXML
    public PasswordField contrasenaAdminText;

    @FXML
    private Label errorLabel;

    public void displayNombreEmpresa(String nombreEmpresa) {
        errorLabel.setText("Confirme que desea eliminar la empresa " + nombreEmpresa);
    }

    @FXML
    protected void onConfirmarButtonClick() {
        String contrasenaAdmin = contrasenaAdminText.getText();
        String contrasenaCorrecta = "false";
        System.out.println(usuarioAdminConfirmar);
        try {
            HttpResponse<String> apiResponseP = Unirest.get("http://localhost:8987/api/login/password/" + usuarioAdminConfirmar + "/" + contrasenaAdmin).asString();
            contrasenaCorrecta = apiResponseP.getBody();
            System.out.println(contrasenaCorrecta);
            System.out.println("Contraseña admin confirmarbuttonclick");;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }
        System.out.println(contrasenaCorrecta);
        if (contrasenaCorrecta.equals("false")) {
            errorLabel.setText("Contraseña incorrecta");
        } else {
            if (contrasenaCorrecta.equals("true")) {
                System.out.println("Se borrará la empresa");
            } else {
                System.out.println("Error contraseña admin");
            }
        }

    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public String getUsuarioAdminConfirmar() {
        return usuarioAdminConfirmar;
    }

    public void setUsuarioAdminConfirmar(String usuarioAdminConfirmar) {
        this.usuarioAdminConfirmar = usuarioAdminConfirmar;
    }



}


