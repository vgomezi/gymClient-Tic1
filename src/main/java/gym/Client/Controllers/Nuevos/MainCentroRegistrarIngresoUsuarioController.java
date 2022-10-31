package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.ActividadObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainCentroRegistrarIngresoUsuarioController {

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
