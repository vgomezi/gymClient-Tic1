package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.ActividadObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

public class EmpresaAdministrarUsuariosController {
    // implements Initializable

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Label nombreUsuaioDisplay;

    @FXML
    public Label apellidoUsuarioDisplay;

    @FXML
    public Label emailUsuarioDisplay;

    @FXML
    public Label saldoUsuarioDisplay;

    @FXML
    public Label deudaUsuarioDisplay;

    @FXML
    public Label telefonoUsuarioDisplay;

    @FXML
    public Label contrasenaUsuarioDisplay;

    @FXML
    public ChoiceBox tipoActividadChoiceBox;

    ObservableList<String> tipoActividadChoiceBoxList = FXCollections.
            observableArrayList("Canchas", "Gimnasio/Sala", "Náuticas");

    @FXML
    private void initialize() {
        tipoActividadChoiceBox.setItems(tipoActividadChoiceBoxList);
        tipoActividadChoiceBox.setValue("Categoria");
    }

    @FXML
    public ImageView imagenUsuarioDisplay;

    @FXML
    public Button actualizarUsuarioBoton;

    @FXML
    public Button eliminarUsuarioBoton;





    public void onEnterPressed(KeyEvent keyEvent) {
    }

    public void onMouseClickedLogOut(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage1 = (Stage) source.getScene().getWindow();
        stage1.close();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Login.fxml"));

            Stage stage = new Stage();

            stage.setTitle("Login");
            stage.setIconified(false);
            stage.setResizable(false);
            stage.getIcons().add(new Image("FitnessIcon.png"));
            stage.setScene(new Scene(root1));
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    public void onTodosLosUsuariosLabelClick(MouseEvent mouseEvent) {

    }

    public void onAdministrarUsuariosLabelClick(MouseEvent mouseEvent) {

    }

    public void onActualizarUsuarioButtonClick(MouseEvent mouseEvent) {

    }

    public void onEliminarUsuarioButtonClick(MouseEvent mouseEvent) {

    }
}
