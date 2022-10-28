package gym.Client.Controllers.Usuario.Actividades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Controllers.Empresa.MainEmpresaController;
import gym.Client.Controllers.LoginController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsuarioMisActividadesContrller implements Initializable {

    @FXML
    private BorderPane pantallaMainUsuario;


    @FXML
    private Label centroActividadDisplay;

    @FXML
    private Label costoActividadDisplay;

    @FXML
    private Label cuposActividadDisplay;

    @FXML
    private Label descripcionActividadDisplay;

    @FXML
    private Label diaActividadDisplay;

    @FXML
    public Label tipoActividadDisplay;

    @FXML
    private Label duracionActividadDisplay;

    @FXML
    private Label horaActividadDisplay;

    @FXML
    private ImageView imagenActividadDisplay;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label misActividadesLabel;

    @FXML
    private Label nombreActividadDisplay;


    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private VBox actividadSeleccionadaVBox;

    private MyListener myListener;

    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    private List<ActividadObject> todasMisActividades() {
        List<ActividadObject> listaActividadesNuevas = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/allActividades/").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaActividadesNuevas = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            System.out.println(actividad);
            System.out.println("Lista actividades AÑADIDAS RECIENTEMENTE " + listaActividadesNuevas);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividadesNuevas;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Entro initialize");
        todasLasActividades.addAll(todasMisActividades());

        if(todasLasActividades.size() > 0) {
            desplegarInfoActividadSeleccionada(todasLasActividades.get(0));
            this.myListener = new MyListener() {
                @Override
                public void onClickActividad(ActividadObject actividadObject) {
                    desplegarInfoActividadSeleccionada(actividadObject);
                }
            };
        }

        System.out.println("entro initialize UsuarioMisActividadesController");

        int column = 0;
        int row = 1;
        try{
            for(ActividadObject actividad : todasLasActividades) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/formularios/OpcionesUsuario/Actividades/ActividadToda.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox todaActividadbox = fxmlLoader.load();
                ActividadTodaController actividadTodaController = fxmlLoader.getController();

                actividadTodaController.setearDatos(actividad, myListener);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todasLasActividadesGridPane.add(todaActividadbox, column++, row);
                GridPane.setMargin(todaActividadbox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    public void desplegarInfoActividadSeleccionada(ActividadObject actividadObject) {
        cuposActividadDisplay.setText(String.valueOf(actividadObject.getCupos()));
        costoActividadDisplay.setText(String.valueOf(actividadObject.getCosto()));
        horaActividadDisplay.setText(actividadObject.getHora().toString());
        diaActividadDisplay.setText(actividadObject.getDia().toString());
        tipoActividadDisplay.setText(actividadObject.getTipo().getTipo());
        nombreActividadDisplay.setText(actividadObject.getNombre());
        descripcionActividadDisplay.setText(actividadObject.getDescripcion());
        Image image = new Image("/centro.jpg");
        imagenActividadDisplay.setImage(image);
        actividadSeleccionadaVBox.setStyle("-fx-background-color : #dbae1a;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        centroActividadDisplay.setText(actividadObject.getCentroDeportivo().getNombre());
    }

    public void onMisActividadesLabelClick(MouseEvent mouseEvent) {

    }

    public void onAdministrarUsuarioLabelClick(MouseEvent mouseEvent) {

    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/MainUsuarioTodasActividades.fxml"));

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            //stage.setTitle("SENSE FIT");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setActividadSeleccionada(ActividadObject actividad) {
        //centroActividadDisplay.setText(actividad.getCentroDeportivo().getNombre());
        costoActividadDisplay.setText(String.valueOf(actividad.getCosto()));
        cuposActividadDisplay.setText(String.valueOf(actividad.getCupos()));
        tipoActividadDisplay.setText(actividad.getTipo().getTipo());
        descripcionActividadDisplay.setText(actividad.getDescripcion());
        diaActividadDisplay.setText(actividad.getDia().toString());
        horaActividadDisplay.setText(actividad.getHora().toString());
        nombreActividadDisplay.setText(actividad.getNombre());
        Image image = new Image("/centro.jpg");
        imagenActividadDisplay.setImage(image);
    }

    public void onMouseClickedLogOut(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage1 = (Stage) source.getScene().getWindow();
        stage1.close();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Login.fxml"));

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login");
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

    public void onEnterPressed(KeyEvent keyEvent) {
        //Desplegar pantalla con los resultados encontrados

        //Si no se encuentra ninguno desplegar "No se encontraron actividades relacionadas con esa busqueda"
    }

    public Label getCentroActividadDisplay() {
        return centroActividadDisplay;
    }

    public void setCentroActividadDisplay(Label centroActividadDisplay) {
        this.centroActividadDisplay = centroActividadDisplay;
    }

    public Label getCostoActividadDisplay() {
        return costoActividadDisplay;
    }

    public void setCostoActividadDisplay(Label costoActividadDisplay) {
        this.costoActividadDisplay = costoActividadDisplay;
    }

    public Label getCuposActividadDisplay() {
        return cuposActividadDisplay;
    }

    public void setCuposActividadDisplay(Label cuposActividadDisplay) {
        this.cuposActividadDisplay = cuposActividadDisplay;
    }

    public Label getDescripcionActividadDisplay() {
        return descripcionActividadDisplay;
    }

    public void setDescripcionActividadDisplay(Label descripcionActividadDisplay) {
        this.descripcionActividadDisplay = descripcionActividadDisplay;
    }

    public Label getDiaActividadDisplay() {
        return diaActividadDisplay;
    }

    public void setDiaActividadDisplay(Label diaActividadDisplay) {
        this.diaActividadDisplay = diaActividadDisplay;
    }

    public Label getDuracionActividadDisplay() {
        return duracionActividadDisplay;
    }

    public void setDuracionActividadDisplay(Label duracionActividadDisplay) {
        this.duracionActividadDisplay = duracionActividadDisplay;
    }

    public Label getHoraActividadDisplay() {
        return horaActividadDisplay;
    }

    public void setHoraActividadDisplay(Label horaActividadDisplay) {
        this.horaActividadDisplay = horaActividadDisplay;
    }

    public ImageView getImagenActividadDisplay() {
        return imagenActividadDisplay;
    }

    public void setImagenActividadDisplay(ImageView imagenActividadDisplay) {
        this.imagenActividadDisplay = imagenActividadDisplay;
    }

    public Label getLogOutLabel() {
        return logOutLabel;
    }

    public void setLogOutLabel(Label logOutLabel) {
        this.logOutLabel = logOutLabel;
    }

    public Label getMisActividadesLabel() {
        return misActividadesLabel;
    }

    public void setMisActividadesLabel(Label misActividadesLabel) {
        this.misActividadesLabel = misActividadesLabel;
    }

    public Label getNombreActividadDisplay() {
        return nombreActividadDisplay;
    }

    public void setNombreActividadDisplay(Label nombreActividadDisplay) {
        this.nombreActividadDisplay = nombreActividadDisplay;
    }
}
