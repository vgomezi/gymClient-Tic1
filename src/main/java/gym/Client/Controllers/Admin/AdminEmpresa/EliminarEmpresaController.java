package gym.Client.Controllers.Admin.AdminEmpresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.EmpresaObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class EliminarEmpresaController {

    private String usuarioAdminEmpresaEliminar;

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

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) {

        String email = emailText.getText();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + email).asObject(String.class);

            System.out.println(apiResponse.getBody());

            ObjectMapper mapper = new ObjectMapper();

            EmpresaObject empresaObject = mapper.readValue(apiResponse.getBody(), EmpresaObject.class);

            if (apiResponse.getBody().isBlank()) {
                //Agregar Panel Error
                System.out.println("Empresa " + email + " no existe");
            } else {

                String nombreEmpresa = empresaObject.getNombre();
                FXMLLoader fxmlLoader = new FXMLLoader();
                System.out.println("Entro confirmar eliminar centro");
                Parent root1 = (Parent) fxmlLoader.load(EliminarEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/ConfirmarBorrarEmpresa.fxml"));

                ConfirmarBorrarEmpresaController confirmarBorrarEmpresaController = fxmlLoader.getController();
                confirmarBorrarEmpresaController.setUsuarioAdminConfirmar(this.usuarioAdminEmpresaEliminar);
                confirmarBorrarEmpresaController.setCorreoEmpresa(empresaObject.getMail());
                confirmarBorrarEmpresaController.displayNombreEmpresa(empresaObject.getNombre());

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





        System.out.println("Se elimino la empresa");
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
            Parent root1 = (Parent) fxmlLoader.load(EliminarEmpresaController.class.getResourceAsStream("/formularios/OpcionesAdministrador/AdminEmpresa/MainAdminEmpresa.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Main Administrador Empresa");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public String getUsuarioAdminEmpresaEliminar() {
        return usuarioAdminEmpresaEliminar;
    }

    public void setUsuarioAdminEmpresaEliminar(String usuarioAdminEmpresaEliminar) {
        this.usuarioAdminEmpresaEliminar = usuarioAdminEmpresaEliminar;
    }
}