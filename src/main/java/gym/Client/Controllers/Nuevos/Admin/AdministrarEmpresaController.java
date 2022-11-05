package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdministrarEmpresaController {

    @FXML
    private VBox actividadSeleccionadaVBox;

    @FXML
    private Button actualizarEmpresaBoton;

    @FXML
    private Label administrarCentrosLabel;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private Label contrasenaEmpresaDisplay;

    @FXML
    private Button eliminarEmpresaBoton;

    @FXML
    private Label emailEmpresaDisplay;

    @FXML
    private ImageView imagenActividadDisplay;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private Label nombreEmpresaDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private ScrollPane todasLasEmpresasScroll;

    @FXML
    private Label todasLasEmpresasTitleLabel;

    @FXML
    void onBusquedaKeyReleased(KeyEvent event) {

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
    void onAdministrarEmpresaLabelClick(MouseEvent event) {

    }

    @FXML
    void onTodasLasEmpresasLabelClick(MouseEvent event) {

    }

    @FXML
    void onActualizarEmpresaButtonClick(MouseEvent event) {

    }

    @FXML
    void onEliminarEmpresaButtonClick(MouseEvent event) {

    }

}