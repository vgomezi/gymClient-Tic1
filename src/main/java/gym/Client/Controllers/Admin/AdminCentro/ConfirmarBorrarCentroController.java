package gym.Client.Controllers.Admin.AdminCentro;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class ConfirmarBorrarCentroController {

    public String usuarioAdminConfirmarBorrar;

    public String correoCentro;

    @FXML
    public Button confirmarBoton;

    @FXML
    public Button cancelarBoton;

    @FXML
    private Label contrasenaAdminLabel;

    @FXML
    public PasswordField contrasenaAdminText;

    @FXML
    private Label nombreCentroLabel;


    public void displayNombreCentro(String nombreCentro) {
        nombreCentroLabel.setText("Confirme que desea eliminar el centro " + nombreCentro);
    }

    @FXML
    protected void onConfirmarButtonClick() {
        String contrasenaAdmin = contrasenaAdminText.getText();
        String contrasenaCorrecta = "false";
        System.out.println(usuarioAdminConfirmarBorrar);
        try {
            HttpResponse<String> apiResponseP = Unirest.get("http://localhost:8987/api/login/password/" + usuarioAdminConfirmarBorrar + "/" + contrasenaAdmin).asString();
            contrasenaCorrecta = apiResponseP.getBody();
            System.out.println(contrasenaCorrecta);
            System.out.println("Contraseña admin confirmarbuttonclick");;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }
        System.out.println(contrasenaCorrecta);
        if (contrasenaCorrecta.equals("false")) {
            //nombreCentroLabel.setText("Contraseña incorrecta");
        } else {
            if (contrasenaCorrecta.equals("true")) {
                try {
                    HttpResponse<JsonNode> apiResponse = null;

                    apiResponse = Unirest.delete("http://localhost:8987/api/centroDeportivo//delete/" + correoCentro).asJson();
                    System.out.println("Centro borrado");
                } catch (Exception e) {
                    System.out.println("Error borrar Centro Deportivo");

                }

                System.out.println("Se borrará el centro");
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



    public String getUsuarioAdminConfirmarBorrar() {
        return usuarioAdminConfirmarBorrar;
    }

    public void setUsuarioAdminConfirmarBorrar(String usuarioAdminConfirmarBorrar) {
        this.usuarioAdminConfirmarBorrar = usuarioAdminConfirmarBorrar;
    }

    public String getCorreoCentro() {
        return correoCentro;
    }

    public void setCorreoCentro(String correoCentro) {
        this.correoCentro = correoCentro;
    }
}


