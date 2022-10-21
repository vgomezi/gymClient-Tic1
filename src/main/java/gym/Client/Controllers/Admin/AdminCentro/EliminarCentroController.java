package gym.Client.Controllers.Admin.AdminCentro;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Controllers.Admin.AdminCentro.Confirmaciones.ConfirmarBorrarCentroController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


@Component
public class EliminarCentroController {


    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onEliminarButtonClick() {
        //Verificar que se borren en todas las tablas

        String email = emailText.getText();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/nombreCentro/" + email).asObject(String.class);

            System.out.println(apiResponse.getBody());

            if (apiResponse.getBody().isBlank()) {
                //Agregar Panel Error
                System.out.println("Centro " + email + " no existe");
            } else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println("Entro confirmar eliminar centro");
                Parent root1 = (Parent) fxmlLoader.load(ConfirmarBorrarCentroController.class.getResourceAsStream("/gym/Client/ConfirmarBorrar.fxml"));

                ConfirmarBorrarCentroController confirmarBorrarCentroController = fxmlLoader.getController();
                confirmarBorrarCentroController.displayNombreCentro(email);
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setTitle("Confirmar accion");
                stage.getIcons().add(new Image("GymIcon.png"));
                stage.setScene(new Scene(root1));
                stage.show();
            }
        } catch(Exception e){
            System.out.println(e);

        }

        /*

         */


        System.out.println("Se elimino el centro");
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        emailText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }


}