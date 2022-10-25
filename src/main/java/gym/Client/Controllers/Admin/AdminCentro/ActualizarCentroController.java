package gym.Client.Controllers.Admin.AdminCentro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ActualizarCentroController {

    @FXML
    public TextField nombreText;

    @FXML
    public PasswordField contrasenaText;

    @FXML
    public Label emailLabelText;


    //Poner Pantalla Intermedia Para Obtener Mail y castear datos
    public void onActualizarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();
        String contrasena = contrasenaText.getText();



    }

    public void onCancelarButtonClick(ActionEvent event) {
    }


}
