package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.*;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.ActividadTodaController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
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
    public Button limpiarActividadBoton;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    public ScrollPane todasLasActividadesScroll;

    private MyListener myListener;

    private File fileImagen;

    @FXML
    private VBox actividadSeleccionadaVBox;

    @FXML
    public TextField busquedaActividadTextfield;



    private List<ActividadObject> todasLasActividades = new ArrayList<>();

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
                Image imageView = new Image("/imagenes/imagenesDefault/actividaddefault.png");
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
            diaDatePickerRegistroDisplay.setValue(null);
            horaActividadRegistroDisplay.clear();
            descripcionActividadRegistroDisplay.clear();
            cuposActividadRegistroDisplay.clear();
            duracionActividadRegistroDisplay.clear();
            costoActividadRegistroDisplay.clear();
            Image imageView = new Image("/imagenes/imagenesDefault/actividaddefault.png");
            imagenActividadRegistroDisplay.setImage(imageView);
            fileImagen = null;

            actualizarActividadBoton.setVisible(false);
            eliminarActividadBoton.setVisible(false);
        }
        actividadSeleccionadaVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    private List<ActividadObject> todasLasActividadesCentro() {
        List<ActividadObject> listaActividades = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/actividadesCentro/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

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

        this.myListener = new MyListener() {


            @Override
            public void onClickActividad(ActividadObject actividadObject) {
                desplegarActividadSeleccionada(actividadObject);
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };

        int column = 0;
        int row = 1;
        try{
            for(ActividadObject actividad : todasLasActividades) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/ActividadToda.fxml"));

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

            if (!apiResponse.getBody().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                List<TipoActividadObject> listaTipos = mapper.readValue(apiResponse.getBody(), new TypeReference<List<TipoActividadObject>>() {});
                ObservableList<String> listaItems = FXCollections.observableArrayList();
                for (TipoActividadObject tipoActividad: listaTipos) {
                    listaItems.add(tipoActividad.getTipo());
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
            Image imageView = new Image("/imagenes/imagenesDefault/centrodefault.png");
            centroImagenCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(centroDeportivoObject.getNombre());
        desplegarActividadSeleccionada(null);
    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {

    }

    public void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(CentroTodasActividadesController.class.getResourceAsStream("/gym/Client/nuevo/MainCentroRegistrarIngresoUsuario.fxml"));

            MainCentroRegistrarIngresoUsuarioController mainCentroRegistrarIngresoUsuarioController = fxmlLoader.getController();
            mainCentroRegistrarIngresoUsuarioController.datosCentro(centro.getMail());
            mainCentroRegistrarIngresoUsuarioController.actividadesProximasCentro();
            mainCentroRegistrarIngresoUsuarioController.actividadesCentro();
            mainCentroRegistrarIngresoUsuarioController.desplegarInfoActividadSeleccionada(null);


            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    public void onRegistrarActividadButtonClick(ActionEvent actionEvent) {
        if (!nombreActividadRegistroDisplay.getText().isEmpty() && !descripcionActividadRegistroDisplay.getText().isEmpty() && !horaActividadRegistroDisplay.getText().isEmpty() && !diaDatePickerRegistroDisplay.getValue().toString().isEmpty() && !cuposActividadRegistroDisplay.getText().isEmpty() && !costoActividadRegistroDisplay.getText().isEmpty() && !duracionActividadRegistroDisplay.getText().isEmpty() && !tipoActividadChoiceBoxRegistroDisplay.getValue().toString().isEmpty()) {
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
                    json = mapperActividad.writeValueAsString(actividadObject);

                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.post("http://localhost:8987/api/actividades").header("Content-Type", "application/json").body(json).asJson();
                    todasLasActividades.clear();
                    actividadesCentro();
                    desplegarActividadSeleccionada(null);
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #1FDB5E;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    fileImagen = null;


                } catch (Exception e) {
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                }
            } catch (Exception e) {
                horaActividadRegistroDisplay.clear();
                diaDatePickerRegistroDisplay.setValue(null);
                cuposActividadRegistroDisplay.clear();
                costoActividadRegistroDisplay.clear();
                duracionActividadRegistroDisplay.clear();
                actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        } else {
            actividadSeleccionadaVBox.setStyle("-fx-background-color : #f4f723;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
    }

    public void onActualizarActividadButtonClick(ActionEvent actionEvent) {
        String descripcion = descripcionActividadRegistroDisplay.getText();
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

        if (!descripcion.isEmpty() && !cupos.isEmpty() && !costo.isEmpty() && !duracion.isEmpty() && !tipo.isEmpty()) {
            try {
                Integer cuposInt = Integer.parseInt(cupos);
                Integer costoInt = Integer.parseInt(costo);
                int duracionInt = Integer.parseInt(duracion);

                actividadEnDisplay.setCosto(costoInt);
                actividadEnDisplay.setCupos(cuposInt);
                actividadEnDisplay.setDuracion(duracionInt);
                actividadEnDisplay.setReservable(reservable);
                actividadEnDisplay.setDescripcion(descripcion);
                TipoActividadObject tipoActividadObject = new TipoActividadObject(tipo);
                actividadEnDisplay.setTipo(tipoActividadObject);
                if (imagen != null) {
                    actividadEnDisplay.setImagen(imagen);
                }


                String json = "";

                try {
                    ObjectMapper mapperActividad = new JsonMapper().builder()
                            .findAndAddModules()
                            .build();
                    mapperActividad.registerModule(new JavaTimeModule());
                    json = mapperActividad.writeValueAsString(actividadEnDisplay);
                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.put("http://localhost:8987/api/actividades/actualizar/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").body(json).asJson();
                    todasLasActividades.clear();
                    actividadesCentro();
                    desplegarActividadSeleccionada(null);
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #1FDB5E;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    fileImagen = null;

                } catch (Exception e) {
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                }
            } catch (Exception e) {
                actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

            }
        } else {
            actividadSeleccionadaVBox.setStyle("-fx-background-color : #f4f723;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
    }

    public void onLimpiarActividadButtonClick(ActionEvent actionEvent) {
        desplegarActividadSeleccionada(null);
    }

    public void onEliminarActividadButtonClick(ActionEvent actionEvent) {
        String nombreActividad = nombreActividadRegistroDisplay.getText();
        String diaActividad = diaDatePickerRegistroDisplay.getValue().toString();
        String horaActividad = horaActividadRegistroDisplay.getText();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcionesActividad/" + nombreActividad + "/" + diaActividad + "/" + horaActividad + "/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            Integer num = Integer.valueOf(apiResponse.getBody());

            if (num == 0) {

                //if no hay inscripciones
                try {
                    HttpResponse<JsonNode> apiResponse1 = null;
                    apiResponse1 = Unirest.delete("http://localhost:8987/api/actividades/deleteActividad/" + nombreActividad + "/" + diaActividad + "/" + horaActividad + "/" + centro.getMail()).asJson();
                    todasLasActividades.clear();
                    actividadesCentro();
                    desplegarActividadSeleccionada(null);
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #1FDB5E;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

                } catch (Exception e) {
                    System.out.println("Error borrando inscripcion: " + e);
                }
            } else {
                actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        } catch (Exception e) {
            actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
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
            fileChooser.setTitle("Elegir imagen actividad");
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
            byte[] bytes = Files.readAllBytes(file.toPath());
            base64String = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        } catch (Exception ignored) {

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
        Image imagen = new Image("/imagenes/imagenesDefault/actividaddefault.png");
        imagenActividadRegistroDisplay.setImage(imagen);
        inicializoChoiceBox();
    }

    private List<ActividadObject> similarActividades(String similar) {
        List<ActividadObject> listaActividades = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/similarActividadCentro/" + similar + "/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividades;
    }



    public void onBusquedaActividadKeyReleased(KeyEvent keyEvent) {
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {
                desplegarActividadSeleccionada(actividadObject);
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };

        if (busquedaActividadTextfield.getText().isEmpty()) {
            todasLasActividadesGridPane = new GridPane();
            todasLasActividadesScroll.setContent(todasLasActividadesGridPane);
            int column = 0;
            int row = 1;
            try{
                for(ActividadObject actividad : todasLasActividades) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/ActividadToda.fxml"));

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
        } else {
            todasLasActividadesGridPane = new GridPane();
            todasLasActividadesScroll.setContent(todasLasActividadesGridPane);
            similarActividades = similarActividades(busquedaActividadTextfield.getText());
            int column = 0;
            int row = 1;
            try{
                for(ActividadObject actividad : similarActividades) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/ActividadToda.fxml"));

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
    }

}
