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

public class AdministrarCentroController {

    @FXML
    private VBox actividadSeleccionadaVBox;

    @FXML
    private Button actualizarCentroBoton;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private Label contrasenaCentroDisplay;

    @FXML
    private Button eliminarCentroBoton;

    @FXML
    private Label emailCentroDisplay;

    @FXML
    private ImageView imagenActividadDisplay;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private Label nombreCentroDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLosCentrosTitleLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private ScrollPane todosLosCentrosScroll;

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
    void onAdministrarCentroLabelClick(MouseEvent event) {

    }

    @FXML
    void onTodosLosCentrosLabelClick(MouseEvent event) {

    }

    @FXML
    void onActualizarCentroButtonClick(ActionEvent event) {

    }

    @FXML
    void onEliminarCentroButtonClick(ActionEvent event) {

    }


}
