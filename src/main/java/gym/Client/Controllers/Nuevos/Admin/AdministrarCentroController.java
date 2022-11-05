package gym.Client.Controllers.Nuevos.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

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
    void onMisActividadesLabelClick(MouseEvent event) {

    }

    @FXML
    void onMouseClickedLogOut(MouseEvent event) {

    }

    @FXML
    void onReservarActividadClick(ActionEvent event) {

    }

    @FXML
    void onTodasLasActividadesLabelClick(MouseEvent event) {

    }

}
