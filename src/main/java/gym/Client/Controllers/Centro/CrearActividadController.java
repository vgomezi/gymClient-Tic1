package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CrearActividadController {


    @FXML
    private TextField Nombre;

    @FXML
    private Label nombretext;

    @FXML
    private TextField Tipo;

    @FXML
    private Label tipotext;

    @FXML
    private TextField Descripcion;

    @FXML
    private Label descripciontext;

    @FXML
    private TextField Hora;

    @FXML
    private Label horatext;

    @FXML
    private TextField Dia;

    @FXML
    private Label diatext;

    @FXML
    private TextField Reservable;

    @FXML
    private Label reservabletext;

    @FXML
    private TextField Cupos;

    @FXML
    private Label cupostext;

    @FXML
    private Button onCrearButtonClick;

    @FXML
    private Button onCancelarButtonClick;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}