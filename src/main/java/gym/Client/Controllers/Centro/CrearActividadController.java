package gym.Client.Controllers.Centro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CrearActividadController {



    @FXML
    private TextField Nombre;

    @FXML
    private TextField nombretext;

    @FXML
    private TextField Tipo;

    @FXML
    private TextField tipotext;

    @FXML
    private TextField Descripcion;

    @FXML
    private TextField descripciontext;

    @FXML
    private TextField Hora;

    @FXML
    private TextField horatext;

    @FXML
    private TextField Dia;

    @FXML
    private TextField diatext;

    @FXML
    private TextField Reservable;

    @FXML
    private TextField reservabletext;

    @FXML
    private TextField Cupos;

    @FXML
    private TextField cupostext;

    @FXML
    protected void onCrearButtonClick() {}

    @FXML
    protected void onCancelarButtonClick() {}


}