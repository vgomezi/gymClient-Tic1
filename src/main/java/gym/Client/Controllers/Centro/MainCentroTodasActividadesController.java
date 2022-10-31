package gym.Client.Controllers.Centro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.TipoActividadObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.ActividadRecienteController;
import gym.Client.Controllers.Usuario.Actividades.ActividadTodaController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import gym.Client.Controllers.Usuario.Actividades.UsuarioMisActividadesController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class MainCentroTodasActividadesController {

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Label todasLasActividadesLabel;

    @FXML
    public Label registrarIngresoUsuarioLabel;

    @FXML
    public Button administrarCentroBoton;

    @FXML
    public Label nombreActividadDisplay;

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
    public ImageView imagenActividadDisplay;

    @FXML
    public DatePicker diaDatePicker;

    @FXML
    public DatePicker horaDatePicker;


    @FXML
    public Label descripcionActividadDisplay;

    @FXML
    public Label cuposActividadDisplay;

    @FXML
    public Label duracionActividadDisplay;

    @FXML
    public Label costoActividadDisplay;

    @FXML
    public Button registrarActividadBoton;


    public String mailUsuarioIngreso;

    private MyListener myListener;

    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    //Poner en lugar de anadidas recientemente las proximas actividades que estan por ocurrir, de forma que sea más
    //fácil encontrarlas
    private List<ActividadObject> proximasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();

}
