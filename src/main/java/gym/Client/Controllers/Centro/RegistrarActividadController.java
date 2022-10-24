package gym.Client.Controllers.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Classes.UserLoginObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class RegistrarActividadController {

    private String usuarioCentroRegistrarActividad;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label tipoLabel;

    @FXML
    private ChoiceBox tipoChoiceBox;

    @FXML
    private Label descripcionLabel;

    @FXML
    private TextField descripcionText;

    @FXML
    private Label horaLabel;

    @FXML
    private TextField horaText;

    @FXML
    private Label diaLabel;

    @FXML
    private DatePicker diaText;

    @FXML
    private Label reservableLabel;

    @FXML
    private CheckBox reservableCheckBox;

    @FXML
    private Label cuposLabel;

    @FXML
    private TextField cuposText;

    @FXML
    private Button registrarBoton;

    @FXML
    private Button cancelarBoton;

    @FXML
    protected void onRegistrarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();
        String tipo = tipoChoiceBox.toString();
        String descripcion = descripcionText.getText();
        String hora = horaText.getText();

        //Arreglar porque no toma el dia
        String dia = diaText.toString();
        String cupos = cuposText.getText();
        Boolean reservable = reservableCheckBox.isSelected();

        if (!nombre.isEmpty() && !tipo.isEmpty() && !descripcion.isEmpty() && !hora.isEmpty() && !dia.isEmpty() && !cupos.isEmpty()) {
            try {
                LocalTime timeLT = LocalTime.parse(hora);
                LocalDate diaLD = LocalDate.parse(dia);
                Integer cuposInt = Integer.parseInt(cupos);


                String json = "";
                String jsonCentro = "";

                try {
                    HttpResponse<String> apiResponse = null;

                    apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centrosMail/" + usuarioCentroRegistrarActividad).asObject(String.class);
                    jsonCentro = apiResponse.getBody();
                    System.out.println(jsonCentro);
                    CentroDeportivoObject centroDeportivo = null;

                    if (!jsonCentro.isBlank()) {
                        ObjectMapper mapper = new ObjectMapper();
                        centroDeportivo = mapper.readValue(apiResponse.getBody(), CentroDeportivoObject.class);
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    ActividadObject actividadObject = new ActividadObject(nombre, timeLT, diaLD, tipo, descripcion, cuposInt, reservable, centroDeportivo);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actividadObject);
                    System.out.println(json);
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/actividades").header("Content-Type", "application/json").body(json).asJson();
                System.out.println("Hecho Actividad");

                System.out.println("Hecho");

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }

        } else {
            System.out.println("Pantalla error datos incorrectos");
        }
    }

    ObservableList<String> tipoChoiceBoxList = FXCollections.
            observableArrayList("Canchas","Gimnasio/Sala", "Naúticas");

    @FXML
    private void initialize(){
        tipoChoiceBox.setItems(tipoChoiceBoxList);
        tipoChoiceBox.setValue("Categoria");
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        nombreText.clear();
        descripcionText.clear();
        horaText.clear();
        //borrar dia seleccionado
        //borrar seleccion checkbox
        cuposText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public String getUsuarioCentroRegistrarActividad() {
        return usuarioCentroRegistrarActividad;
    }

    public void setUsuarioCentroRegistrarActividad(String usuarioCentroRegistrarActividad) {
        this.usuarioCentroRegistrarActividad = usuarioCentroRegistrarActividad;
    }
}