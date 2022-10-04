package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CrearActividadController {


    @FXML
    private Label Nombre;

    @FXML
    private TextField nombretext;

    @FXML
    private Label Tipo;

    @FXML
    private TextField tipotext;

    @FXML
    private Label Descripcion;

    @FXML
    private TextField descripciontext;

    @FXML
    private Label Hora;

    @FXML
    private TextField horatext;

    @FXML
    private Label Dia;

    @FXML
    private TextField diatext;

    @FXML
    private Label Reservable;

    @FXML
    private TextField reservabletext;

    @FXML
    private Label Cupos;

    @FXML
    private TextField cupostext;

    @FXML
    private Button onCrearButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}