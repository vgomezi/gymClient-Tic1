package gym.Client.Controllers.Nuevos;

import gym.Client.Controllers.LoginController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdministrarCentroDeudaController {

    @FXML
    private Label liquidacionTitleLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private Label registrarIngresoUsuarioLabel;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasActividadesLabel;

    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent keyEvent) {

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
    void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {

    }

    @FXML
    void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {

    }

}
