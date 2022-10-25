package gym.Client.Controllers.Admin.AdminCentro;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;


@Controller
public class EliminarCentroController {

    public String usuarioAdminCentroEliminar;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Button eliminarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    private Button volverBoton;

    public String getUsuarioAdminCentroEliminar() {
        return usuarioAdminCentroEliminar;
    }

    public void setUsuarioAdminCentroEliminar(String usuarioAdminCentroEliminar) {
        this.usuarioAdminCentroEliminar = usuarioAdminCentroEliminar;
    }

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) {
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

                String nombreCentro = apiResponse.getBody();
                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println("Entro confirmar eliminar centro");
                Parent root1 = (Parent) fxmlLoader.load(ConfirmarBorrarCentroController.class.getResourceAsStream("/gym/Client/ConfirmarBorrarCentro.fxml"));

                ConfirmarBorrarCentroController confirmarBorrarCentroController = fxmlLoader.getController();
                confirmarBorrarCentroController.setUsuarioAdminConfirmarBorrar(this.usuarioAdminCentroEliminar);
                confirmarBorrarCentroController.setCorreoCentro(email);
                confirmarBorrarCentroController.displayNombreCentro(nombreCentro);

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

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(EliminarCentroController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminCentro/MainAdminCentro.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador Centro");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public TextField getEmailText() {
        return emailText;
    }

    public void setEmailText(TextField emailText) {
        this.emailText = emailText;
    }
}