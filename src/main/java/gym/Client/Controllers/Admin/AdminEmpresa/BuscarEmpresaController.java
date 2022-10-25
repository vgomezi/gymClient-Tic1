package gym.Client.Controllers.Admin.AdminEmpresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Controllers.Admin.AdminCentro.BuscarCentroController;
import gym.Client.Controllers.Admin.AdminCentro.DatosBuscarCentroController;
import gym.Client.Controllers.ErrorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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
    protected void onBuscarButtonClick(ActionEvent event) {
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

                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root1 = (Parent) fxmlLoader.load(BuscarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/DatosBuscarEmpresa.fxml"));

                System.out.println("Voy a pasar datos");
                DatosBuscarEmpresaController datosBuscarEmpresaController = fxmlLoader.getController();
                System.out.println("Pase controller");
                datosBuscarEmpresaController.getEmailDatoLabel().setText(empresaObject.getMail());
                datosBuscarEmpresaController.getNombreDatoLabel().setText(empresaObject.getNombre());
                datosBuscarEmpresaController.getBonoDatoLabel().setText(empresaObject.getBono());
                System.out.println("Pase datos");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("Empresa " + empresaObject.getNombre());
                stage.setScene(new Scene(root1));
                stage.show();

                //System.out.println(centroDeportivo.toString());
                System.out.println(empresaObject.getNombre());
                System.out.println(empresaObject.getBono());
            } else {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root1 = (Parent) fxmlLoader.load(BuscarCentroController.class.getResourceAsStream("/gym/Client/Error.fxml"));

                    ErrorController errorController = fxmlLoader.getController();
                    errorController.getErrorLabel().setText("Empresa " + correo + " no existe");

                    Stage stage = new Stage();

                    stage.initModality(Modality.APPLICATION_MODAL);

                    stage.setTitle("Error");
                    stage.getIcons().add(new Image("ErrorIcon.png"));
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    System.out.println("Error");
                }

                System.out.println("empresa " + correo + " no existe");
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