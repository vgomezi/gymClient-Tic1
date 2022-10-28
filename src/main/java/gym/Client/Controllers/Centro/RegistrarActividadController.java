package gym.Client.Controllers.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

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
    private DatePicker diaTextPicker;

    @FXML
    private Label reservableLabel;

    @FXML
    private CheckBox reservableCheckBox;

    @FXML
    private Label cuposLabel;

    @FXML
    private TextField cuposText;

    @FXML
    private Label costoLabel;

    @FXML
    private TextField costoText;

    @FXML
    private Button registrarActividadFoto;

    @FXML
    private Button registrarBoton;

    @FXML
    private Button cancelarBoton;

    public Image imagen;

    @FXML
    protected void onRegistrarButtonClick(ActionEvent event) {
        String nombre = nombreText.getText();
        String tipo = tipoChoiceBox.getValue().toString();
        String descripcion = descripcionText.getText();
        String hora = horaText.getText();
        String dia = diaTextPicker.getValue().toString();
        String cupos = cuposText.getText();
        String costo = costoText.getText();
        //byte[] imagen = registrarActividadAction(event);
        String imagen = registrarActividadAction(event);
        //System.out.println(imagen);
        Boolean reservable = reservableCheckBox.isSelected();
        System.out.println(reservable);
        System.out.println(tipo);

        if (!nombre.isEmpty() && !tipo.isEmpty() && !descripcion.isEmpty() && !hora.isEmpty() && !dia.isEmpty() && !costo.isEmpty()) {
            try {
                LocalTime timeLT = LocalTime.parse(hora);
                LocalDate dateDT = LocalDate.parse(dia);
                Integer cuposInt = Integer.parseInt(cupos);
                Integer costoInt = Integer.parseInt(costo);


                String json = "";
                String jsonCentro = "";

                try {
                    HttpResponse<String> apiResponse = null;
                    apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centrosMail/" + usuarioCentroRegistrarActividad).asObject(String.class);
                    jsonCentro = apiResponse.getBody();
                    System.out.println(jsonCentro);
                    CentroDeportivoObject centroDeportivo = null;
                    System.out.println(jsonCentro);

                    if (!jsonCentro.isBlank()) {
                        System.out.println("Entro if");
                        ObjectMapper mapper = new JsonMapper().builder()
                                .findAndAddModules()
                                .build();
                        centroDeportivo = mapper.readValue(jsonCentro, CentroDeportivoObject.class);
                        System.out.println("centro mapper Hecho ");
                    }
                    TipoActividadObject tipoActividadObject = new TipoActividadObject(tipo);
                    ObjectMapper mapperActividad = new JsonMapper().builder()
                            .findAndAddModules()
                            .build();
                    mapperActividad.registerModule(new JavaTimeModule());
                    ActividadObject actividadObject = new ActividadObject(nombre, timeLT, dateDT, centroDeportivo.getMail(), tipoActividadObject, descripcion, costoInt, cuposInt, reservable, new Date(), imagen, centroDeportivo);
                    System.out.println("Imagen actividad");
                    System.out.println(actividadObject.getImagenActividad());
                    json = mapperActividad.writeValueAsString(actividadObject);
                    System.out.println(json);
                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
                System.out.println("Http respones luego de catch");
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/actividades").header("Content-Type", "application/json").body(json).asJson();

                System.out.println("Hecho Actividad");

                System.out.println("Hecho");

                /*Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();*/

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }

        } else {
            System.out.println("Pantalla error datos incorrectos");
        }
    }

    ObservableList<String> tipoChoiceBoxList = FXCollections.
            observableArrayList("Canchas","Gimnasio/Sala", "Náuticas");

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

    public String registrarActividadAction(ActionEvent event) {
        String base64String = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen actividad");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        try {
            //FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            System.out.println(bytes);
            base64String = Base64.getEncoder().encodeToString(bytes);
            System.out.println(base64String);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return base64String;
    }

    /*public byte[] registrarActividadAction(ActionEvent event) {
        byte[] bytes = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen actividad");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        try {
            //FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(file);
            bytes = Files.readAllBytes(file.toPath());
            System.out.println(bytes);
            //base64String = Base64.getEncoder().encodeToString(bytes);
            //System.out.println(base64String);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return bytes;
        //return base64String;
    }*/

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}