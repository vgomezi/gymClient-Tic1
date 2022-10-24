package gym.Client.Controllers.Admin.AdminEmpresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpresaObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class BuscarEmpresaController {

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button buscarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onBuscarButtonClick() {
        String correo = emailText.getText();
        String empresa  = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + correo).asObject(String.class);
            empresa = apiResponse.getBody();
            System.out.println(empresa);

            if (!empresa.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if");

                System.out.println("Json hecho");

                EmpresaObject empresaObject = mapper.readValue(apiResponse.getBody(), EmpresaObject.class);

                //System.out.println(centroDeportivo.toString());
                System.out.println(empresaObject.getNombre());
                System.out.println(empresaObject.getBono());
            } else {
                System.out.println("centro " + correo + " no existe");
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}