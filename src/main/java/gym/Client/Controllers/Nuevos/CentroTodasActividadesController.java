package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Empresa.Pane.UsuarioEmpresaController;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CentroTodasActividadesController {
    // implements Initializable

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Label todasLasActividadesLabel;

    @FXML
    public Label registrarIngresoUsuarioLabel;

    @FXML
    public TextField nombreActividadRegistroDisplay;

    @FXML
    public ImageView imagenActividadRegistroDisplay;

    @FXML
    public CheckBox reservableCheckBoxRegistroDisplay;

    @FXML
    public ChoiceBox tipoActividadChoiceBoxRegistroDisplay;

    @FXML
    public DatePicker diaDatePickerRegistroDisplay;

    @FXML
    public TextField horaActividadRegistroDisplay;

    @FXML
    public TextField descripcionActividadRegistroDisplay;

    @FXML
    public TextField cuposActividadRegistroDisplay;

    @FXML
    public TextField duracionActividadRegistroDisplay;

    @FXML
    public TextField costoActividadRegistroDisplay;


    @FXML
    public Button registrarActividadBoton;

    @FXML
    public Button actualizarActividadBoton;

    @FXML
    public Button eliminarActividadBoton;


    private MyListener myListener;

    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    //Poner en lugar de anadidas recientemente las proximas actividades que estan por ocurrir, de forma que sea más
    //fácil encontrarlas
    private List<ActividadObject> proximasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();

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

    public CentroDeportivoObject centro;

    public void datosCentro(String correoElectronico) {
        //fijarse main usuarios todas actividades

    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {

    }

    public void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(CentroTodasActividadesController.class.getResourceAsStream("/gym/Client/nuevo/MainCentroRegistrarIngresoUsuario.fxml"));

            MainCentroRegistrarIngresoUsuarioController mainCentroRegistrarIngresoUsuarioController = fxmlLoader.getController();
            System.out.println(centro.getMail());
            mainCentroRegistrarIngresoUsuarioController.datosCentro(centro.getMail());

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    public void onRegistrarActividadButtonClick(MouseEvent mouseEvent) {

    }

    public void onActualizarActividadButtonClick(MouseEvent mouseEvent) {

    }

    private List<ActividadObject> misActividades = new ArrayList<>();

    public EmpleadoObject empleadoEnDisplay;


    public void onEliminarActividadButtonClick(MouseEvent mouseEvent) {
        String nombreActividad = nombreActividadRegistroDisplay.getText();
        String diaActividad = diaDatePickerRegistroDisplay.getValue().toString();
        String horaActividad = horaActividadRegistroDisplay.getText();

        try {
            HttpResponse<JsonNode> apiResponse = null;
            System.out.println(nombreActividad);
            apiResponse = Unirest.delete("http://localhost:8987/api/actividades/deleteActividad/" + nombreActividad + diaActividad + horaActividad).asJson();
            System.out.println("Actividad borrada");

        } catch (Exception e) {
            System.out.println("Error borrando inscripcion: " + e);
        }
        misActividades.clear();
        //actividadesCentro();
        //desplegarActividadSeleccionada(null);
    }
}
