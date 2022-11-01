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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;




public class MainEmpresaTodosUsuariosController {

    @FXML
    private HBox actividadesRecienteLayout;

    @FXML
    private Button administrarEmpresaBoton;

    @FXML
    private Label administrarUsuariosLabel;

    @FXML
    private Label anadidasRecientementeTitleLabel;

    @FXML
    private TextField apellidoRegistroEmpleado;

    @FXML
    private Label apellidoUsuarioDisplay;

    @FXML
    private TextField contrasenaRegistroEmpleado1;

    @FXML
    private Label contraseñaUsuarioDisplay;

    @FXML
    private TextField emailRegistroEmpleado;

    @FXML
    private Label emailUsuarioDisplay;

    @FXML
    private ImageView imagenUsuarioDisplay;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreRegistroEmpleado;

    @FXML
    private Label nombreUsuarioDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private Button registrarUsuarioBoton;

    @FXML
    private TextField telefonoRegistroEmpleado;

    @FXML
    private Label telefonoUsuarioDisplay;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasActividadesTitleLabel;

    @FXML
    private Label todosLosUsuariosLabel;

    @FXML
    private VBox usuarioSeleccionadoVBox;

    @FXML
    void onAdministrarUsuariosLabelClick(MouseEvent event) {

    }

    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent event) {

    }

    @FXML
    void onMouseClickedLogOut(MouseEvent mouseEvent) {
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

    @FXML
    void onTodosLosUsuariosLabelClick(MouseEvent event) {

    }

}
