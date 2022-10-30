package gym.Client.Controllers.Usuario.Actividades;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class MainUsuarioTodasActividadesController implements Initializable {

    @FXML
    public Button reservarActividadBoton;

    @FXML
    public TextField busquedaTextField;
    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private HBox actividadesRecienteLayout;

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

    @FXML
    private Circle imagenUsuarioCirculo;

    @FXML
    private Label nombreUsuarioLabel;

    @FXML
    private Label apellidoUsuarioLabel;

    @FXML
    private ScrollPane todasLasActividadesScroll;

    @FXML
    private ChoiceBox<String> tiposPantallaPrincipalChoiceBox;

    public String mailUsuarioIngreso;

    private MyListener myListener;

    private List<ActividadObject> anadidosRecienteLista = new ArrayList<>();
    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();

    private List<ActividadObject> anadidosRecientemente() {
        List<ActividadObject> listaActividadesNuevas = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/nuevasActividades").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Logro get");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            System.out.println("Hago object mapper");
            //mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaActividadesNuevas = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista actividades AÑADIDAS RECIENTEMENTE");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividadesNuevas;
    }

    private List<ActividadObject> todasLasActividades() {
        List<ActividadObject> listaActividades = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/allActividades/").header("Content-Type", "application/json").asObject(String.class);
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

    private List<ActividadObject> similarActividades(String similar) {
        List<ActividadObject> listaActividades = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/similarActividad/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            //System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista actividades similares "/* + listaActividades*/);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividades;
    }



    public EmpleadoObject empleado;

    public void datosUsuario(String correoElectronico) {
        EmpleadoObject empleadoObject = null;
        try {
            System.out.println("try obtener usuario");
            String empleado = "";
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/empleadoMail/" + correoElectronico).asObject(String.class);
            empleado = apiResponse.getBody();
            System.out.println("Imprimo empleado");
            System.out.println(empleado);


            if (!empleado.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if usuario");
                empleadoObject = mapper.readValue(apiResponse.getBody(), EmpleadoObject.class);
                this.empleado = empleadoObject;
                System.out.println(empleadoObject);
            }
            System.out.println("Try obtener usuario hecho");
        } catch (Exception e) {
            System.out.println("Try obtener usuario error");
        }

        if(empleadoObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(empleadoObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenUsuarioCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/usuariodefault.png");
            imagenUsuarioCirculo.setFill(new ImagePattern(imageView));
        }

        nombreUsuarioLabel.setText(empleadoObject.getNombre());
        apellidoUsuarioLabel.setText(empleadoObject.getApellido());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Entro initialize");
        //System.out.println(anadidosRecientemente());
        if (anadidosRecienteLista.isEmpty()) {
            System.out.println("entro anadidosreciente if");
            anadidosRecienteLista.addAll(anadidosRecientemente());
        }
        if (todasLasActividades.isEmpty()) {
            todasLasActividades.addAll(todasLasActividades());
        }

        if(todasLasActividades.size() > 0) {
            desplegarInfoActividadSeleccionada(todasLasActividades.get(0));
            this.myListener = new MyListener() {
                @Override
                public void onClickActividad(ActividadObject actividadObject) {
                    desplegarInfoActividadSeleccionada(actividadObject);
                }

                @Override
                public void onClickUsuario(EmpleadoObject empleadoObject) {

                }
            };
        }

        //System.out.println(anadidosRecienteLista + "anadidos reciente lista");
        System.out.println("entro initialize actividadRecienteScrollController");

        int column = 0;
        int row = 1;
        try{
            for (int i = 0; i < anadidosRecienteLista.size(); i++) {
                //System.out.println("tamaño i = " + anadidosRecienteLista.size());
                System.out.println("Entro try");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/formularios/OpcionesUsuario/Actividades/ActividadReciente.fxml"));
                System.out.println("Carga FXMLLoader");

                HBox anadidaRecienteBox = fxmlLoader.load();
                ActividadRecienteController actividadRecienteController = fxmlLoader.getController();

                actividadRecienteController.obtenerDatos(anadidosRecienteLista.get(i), myListener);

                this.actividadesRecienteLayout.getChildren().add(anadidaRecienteBox);
            }

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
        inicializoChoiceBox();
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
                    listaItems.add(tipoActividad.getTipo());
                }
                tiposPantallaPrincipalChoiceBox.setItems(listaItems);

                tiposPantallaPrincipalChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error cargando choicebox :" + e);
        }
    }

    public void desplegarInfoActividadSeleccionada(ActividadObject actividadObject) {
        cuposActividadDisplay.setText("CUPOS: " + String.valueOf(actividadObject.getCupos()));
        costoActividadDisplay.setText("$" + String.valueOf(actividadObject.getCosto()));
        horaActividadDisplay.setText(actividadObject.getHora().toString());
        diaActividadDisplay.setText(actividadObject.getDia().toString());
        tipoActividadDisplay.setText(actividadObject.getTipo().getTipo());
        nombreActividadDisplay.setText(actividadObject.getNombre().toUpperCase());
        descripcionActividadDisplay.setText(actividadObject.getDescripcion());
        duracionActividadDisplay.setText(String.valueOf(actividadObject.getDuracion()) + " min");

        if(actividadObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(actividadObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenActividadDisplay.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagen/actividaddefault.png");
            imagenActividadDisplay.setImage(imageView);
        }

        actividadSeleccionadaVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        centroActividadDisplay.setText(actividadObject.getCentroDeportivo().getNombre());
    }

    public void onMisActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainUsuarioTodasActividadesController.class.getResourceAsStream("/gym/Client/nuevo/UsuarioMisActividades.fxml"));

            UsuarioMisActividadesController usuarioMisActividadesController = fxmlLoader.getController();
            System.out.println(empleado.getMail());
            usuarioMisActividadesController.datosUsuario(empleado);

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

    public void onAdministrarUsuarioLabelClick(MouseEvent mouseEvent) {

    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {

    }

    /*private void setActividadSeleccionada(ActividadObject actividad) {
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
    }*/

    public void onMouseClickedLogOut(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage1 = (Stage) source.getScene().getWindow();
        stage1.close();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream("/gym/Client/Login.fxml"));

            Stage stage = new Stage();

            //stage.initModality(Modality.APPLICATION_MODAL);

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

    public void onReservarActividadClick(ActionEvent event) {

    }

    /*public void onEnterPressed(KeyEvent keyEvent) {
        //Desplegar pantalla con los resultados encontrados

        //Si no se encuentra ninguno desplegar "No se encontraron actividades relacionadas con esa busqueda"
    }*/

    public void filtrarPorTipo() {}

    public void onBusquedaKeyReleased(KeyEvent keyEvent) {
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {
                desplegarInfoActividadSeleccionada(actividadObject);
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };
        System.out.println(busquedaTextField.getText());
        if (busquedaTextField.getText().isEmpty()) {
            todasLasActividadesGridPane = new GridPane();
            todasLasActividadesScroll.setContent(todasLasActividadesGridPane);
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
        } else {
            todasLasActividadesGridPane = new GridPane();
            todasLasActividadesScroll.setContent(todasLasActividadesGridPane);
            similarActividades = similarActividades(busquedaTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(ActividadObject actividad : similarActividades) {
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
                    System.out.println(similarActividades);
                    GridPane.setMargin(todaActividadbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }

        }
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

    public String getMailUsuarioIngreso() {
        return mailUsuarioIngreso;
    }

    public void setMailUsuarioIngreso(String mailUsuarioIngreso) {
        this.mailUsuarioIngreso = mailUsuarioIngreso;
    }
}
