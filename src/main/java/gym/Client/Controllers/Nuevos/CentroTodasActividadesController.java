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
import gym.Client.Classes.TipoActividadObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.ActividadTodaController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CentroTodasActividadesController implements Initializable {

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Circle centroImagenCirculo;

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

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    public ScrollPane todasLasActividadesScroll;

    private MyListener myListener;

    private File fileImagen;

    @FXML
    private VBox actividadSeleccionadaVBox;



    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    //Poner en lugar de anadidas recientemente las proximas actividades que estan por ocurrir, de forma que sea más
    //fácil encontrarlas
    private List<ActividadObject> proximasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();



    public ActividadObject actividadEnDisplay;

    public void desplegarActividadSeleccionada(@Nullable ActividadObject actividadObject) {
        actividadEnDisplay = actividadObject;
        if (actividadObject != null) {
            if (actividadEnDisplay.getImagen() != null) {
                byte[] imageDecoded = Base64.getDecoder().decode(actividadEnDisplay.getImagen());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
                BufferedImage bImage = null;
                try {
                    bImage = ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image toAdd = SwingFXUtils.toFXImage(bImage, null);
                imagenActividadRegistroDisplay.setImage(toAdd);
            } else {
                Image imageView = new Image("/imagen/usuariodefault.png");
                imagenActividadRegistroDisplay.setImage(imageView);
            }
            nombreActividadRegistroDisplay.setText(actividadObject.getNombre());
            if (actividadObject.isReservable()) {
                reservableCheckBoxRegistroDisplay.setSelected(true);
            } else {
                reservableCheckBoxRegistroDisplay.setSelected(false);
            }
            tipoActividadChoiceBoxRegistroDisplay.setValue(actividadObject.getTipo().getTipo());
            diaDatePickerRegistroDisplay.setValue(actividadObject.getDia());
            horaActividadRegistroDisplay.setText(String.valueOf(actividadObject.getHora()));
            descripcionActividadRegistroDisplay.setText(actividadObject.getDescripcion());
            cuposActividadRegistroDisplay.setText(String.valueOf(actividadObject.getCupos()));
            duracionActividadRegistroDisplay.setText(String.valueOf(actividadObject.getDuracion()));
            costoActividadRegistroDisplay.setText(String.valueOf(actividadObject.getCosto()));

            registrarActividadBoton.setVisible(true);
            actualizarActividadBoton.setVisible(true);
            eliminarActividadBoton.setVisible(true);
        } else {
            nombreActividadRegistroDisplay.clear();
            reservableCheckBoxRegistroDisplay.setSelected(false);
            tipoActividadChoiceBoxRegistroDisplay.setValue(null);
            diaDatePickerRegistroDisplay.getEditor().clear();
            horaActividadRegistroDisplay.clear();
            descripcionActividadRegistroDisplay.clear();
            cuposActividadRegistroDisplay.clear();
            duracionActividadRegistroDisplay.clear();
            costoActividadRegistroDisplay.clear();

            registrarActividadBoton.setVisible(false);
            actualizarActividadBoton.setVisible(false);
            eliminarActividadBoton.setVisible(false);


        }
        actividadSeleccionadaVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    private List<ActividadObject> todasLasActividadesCentro() {
        List<ActividadObject> listaActividades = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/actividadesCentro/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Logro json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista actividades Todas Actividades " /*+ listaActividades*/);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividades;
    }

    public void actividadesCentro() {
        todasLasActividadesGridPane = new GridPane();
        todasLasActividadesScroll.setContent(todasLasActividadesGridPane);
        todasLasActividades = new ArrayList<>();
        todasLasActividades.addAll(todasLasActividadesCentro());

        if(todasLasActividades.size() > 0) {
            //desplegarInfoActividadSeleccionada(todasLasActividades.get(0));
            this.myListener = new MyListener() {


                @Override
                public void onClickActividad(ActividadObject actividadObject) {
                    desplegarActividadSeleccionada(actividadObject);
                }

                @Override
                public void onClickUsuario(EmpleadoObject empleadoObject) {

                }
            };
        } else {

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

    public void inicializoChoiceBox() {
        try {
            HttpResponse<String> apiResponse = null;
            apiResponse = Unirest.get("http://localhost:8987/api/tipoactividad/allTipoActividad").asObject(String.class);
            System.out.println("Logre get tipos actividades");

            if (!apiResponse.getBody().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if inicializoChoiceBox");
                List<TipoActividadObject> listaTipos = mapper.readValue(apiResponse.getBody(), new TypeReference<List<TipoActividadObject>>() {});
                ObservableList<String> listaItems = FXCollections.observableArrayList();
                for (TipoActividadObject tipoActividad: listaTipos) {
                    listaItems.add(tipoActividad.getTipo()); /*.toUpperCase()*/
                }
                tipoActividadChoiceBoxRegistroDisplay.setItems(listaItems);
            }
        } catch (Exception e) {
            System.out.println("Error cargando choicebox :" + e);
        }
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

    public void datosCentro(CentroDeportivoObject centroDeportivoObject) {
        this.centro = centroDeportivoObject;
        if(centroDeportivoObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(centroDeportivoObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            centroImagenCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/centrodefault.png");
            centroImagenCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(centroDeportivoObject.getNombre());
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
            mainCentroRegistrarIngresoUsuarioController.actividadesProximasCentro();
            mainCentroRegistrarIngresoUsuarioController.actividadesCentro();


            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    public void onRegistrarActividadButtonClick(MouseEvent mouseEvent) {
        String nombre = nombreActividadRegistroDisplay.getText();
        String descripcion = descripcionActividadRegistroDisplay.getText();
        String hora = horaActividadRegistroDisplay.getText();
        String dia = diaDatePickerRegistroDisplay.getValue().toString();
        String cupos = cuposActividadRegistroDisplay.getText();
        String costo = costoActividadRegistroDisplay.getText();
        String duracion = duracionActividadRegistroDisplay.getText();
        Boolean reservable = reservableCheckBoxRegistroDisplay.isSelected();
        String tipo = tipoActividadChoiceBoxRegistroDisplay.getValue().toString();
        String imagen = null;
        try {
            imagen = codificarImagenRegistroActividad(fileImagen);
        } catch (Exception ignored) {

        }

        if (!nombre.isEmpty() && !descripcion.isEmpty() && !hora.isEmpty() && !dia.isEmpty() && !cupos.isEmpty() && !costo.isEmpty() && !duracion.isEmpty() && !tipo.isEmpty()) {
            try {
                LocalTime timeLT = LocalTime.parse(hora);
                LocalDate dateDT = LocalDate.parse(dia);
                Integer cuposInt = Integer.parseInt(cupos);
                Integer costoInt = Integer.parseInt(costo);
                int duracionInt = Integer.parseInt(duracion);
                CentroDeportivoObject centroDeportivoObject = centro;


                String json = "";

                try {
                    TipoActividadObject tipoActividadObject = new TipoActividadObject(tipo);
                    ObjectMapper mapperActividad = new JsonMapper().builder()
                            .findAndAddModules()
                            .build();
                    mapperActividad.registerModule(new JavaTimeModule());
                    ActividadObject actividadObject = new ActividadObject(nombre, timeLT, dateDT, centroDeportivoObject.getMail(), tipoActividadObject, descripcion, duracionInt, costoInt, cuposInt, reservable, new Date(), imagen, centroDeportivoObject);
                    System.out.println("Imagen actividad");
                    System.out.println(actividadObject.getImagen());
                    json = mapperActividad.writeValueAsString(actividadObject);

                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.post("http://localhost:8987/api/actividades").header("Content-Type", "application/json").body(json).asJson();
                    System.out.println("Registro actividad hecho");

                } catch (Exception e) {

                }
            } catch (Exception e) {

            }
        }


    }

    public void onActualizarActividadButtonClick(MouseEvent mouseEvent) {

    }

    private List<ActividadObject> misActividades;




    public void onEliminarActividadButtonClick(MouseEvent mouseEvent) {
        String nombreActividad = nombreActividadRegistroDisplay.getText();
        String diaActividad = diaDatePickerRegistroDisplay.getValue().toString();
        String horaActividad = horaActividadRegistroDisplay.getText();

        try {
            HttpResponse<JsonNode> apiResponse = null;
            System.out.println(nombreActividad);
            //chequear camino borrar actividad
            apiResponse = Unirest.delete("http://localhost:8987/api/actividades/deleteActividad/" + nombreActividad + diaActividad + horaActividad + centro.getMail()).asJson();
            System.out.println("Actividad borrada");

        } catch (Exception e) {
            System.out.println("Error borrando inscripcion: " + e);
        }
        misActividades.clear();
        //actividadesCentro();
        //desplegarActividadSeleccionada(null);
    }

    @FXML
    void onImagenRegistroMouseClick(MouseEvent mouseEvent) {
        File file = tomarImagen(mouseEvent);
        String base64 = codificarImagenRegistroActividad(file);
        Image imagen = decodificarImagen(base64);
        imagenActividadRegistroDisplay.setImage(imagen);
    }

    public File tomarImagen (MouseEvent mouseEvent) {
        File file = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Elegir imagen centro");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            file = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
            if (file != null) {
                fileImagen = file;
            }
        } catch (Exception e) {
            System.out.println("No se selecciono ningun archivo");
        }
        return file;
    }

    public String codificarImagenRegistroActividad(File file) {
        String base64String = null;
        try {
            System.out.println(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            System.out.println("Convertí file en bytes");
            base64String = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
            System.out.println("Converti bytes en string");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return base64String;
    }

    public Image decodificarImagen(String imagen) {
        byte[] imageDecoded = org.apache.commons.codec.binary.Base64.decodeBase64(imagen);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image toAdd = SwingFXUtils.toFXImage(bImage, null);
        return toAdd;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imagen = new Image("/imagen/actividaddefault.png");
        imagenActividadRegistroDisplay.setImage(imagen);
        inicializoChoiceBox();
    }

    public void onBusquedaActividadKeyReleased(KeyEvent keyEvent) {
    }
}
