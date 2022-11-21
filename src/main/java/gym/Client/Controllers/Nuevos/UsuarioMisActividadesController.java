package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.ActividadTodaController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class UsuarioMisActividadesController implements Initializable {

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

    @FXML
    private Circle imagenUsuarioCirculo;

    @FXML
    private Label nombreUsuarioLabel;

    @FXML
    private Label apellidoUsuarioLabel;;

    @FXML
    private Button cancelarActividadBoton;

    @FXML
    private ScrollPane misActividadesScroll;

    @FXML
    private ChoiceBox<String> filtroNuevasTodasActividades;

    private MyListener myListener;

    private List<ActividadObject> misActividades = new ArrayList<>();

    EmpleadoObject empleado;

    public void datosUsuario(EmpleadoObject empleadoObject) {
        this.empleado = empleadoObject;
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
            Image imageView = new Image("/imagenes/imagenesDefault/usuariodefault.png");
            imagenUsuarioCirculo.setFill(new ImagePattern(imageView));
        }

        nombreUsuarioLabel.setText(empleadoObject.getNombre());
        apellidoUsuarioLabel.setText(empleadoObject.getApellido());
    }

    private List<ActividadObject> todasMisActividades() {
        List<ActividadObject> listaMisActividades = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcionUsuario/" + empleado.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisActividades;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializoChoiceBox();
    }

    public void inicializoChoiceBox() {
        ObservableList<String> listaItems = FXCollections.observableArrayList();
        listaItems.add("TODAS");
        listaItems.add("NUEVAS");
        filtroNuevasTodasActividades.setItems(listaItems);
        filtroNuevasTodasActividades.setValue("NUEVAS");
        filtroNuevasTodasActividades.setStyle("-fx-background-color : #FFFFFF;");
        DropShadow dropShadow = new DropShadow(10, Color.valueOf("#c7c9c9"));
        filtroNuevasTodasActividades.setEffect(dropShadow);


        filtroNuevasTodasActividades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrarNuevas(observable.getValue());
            }
        });
    }

    public void filtrarNuevas (String filtro) {
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {
                desplegarInfoActividadSeleccionada(actividadObject);
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };

        if (filtro.equals("TODAS")) {
            todasLasActividadesGridPane = new GridPane();
            misActividadesScroll.setContent(todasLasActividadesGridPane);
            int column = 0;
            int row = 1;
            try{
                for(ActividadObject actividad : misActividades) {
                    if(row == 1 && column == 0) {
                        desplegarInfoActividadSeleccionada(actividad);
                    }
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
                System.out.println("Error creando panel PARA FILTRO TODAS " + e);

            }
        } else {
            todasLasActividadesGridPane = new GridPane();
            misActividadesScroll.setContent(todasLasActividadesGridPane);
            int column = 0;
            int row = 1;
            try{
                int count = 0;
                for(ActividadObject actividad : misActividades) {
                    Period period = Period.between(LocalDate.now(), actividad.getDia());
                    if (period.getDays() >= 0) {
                        count++;
                        if(count == 1) {
                            desplegarInfoActividadSeleccionada(actividad);
                        }
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
                }
                if (count == 0) {
                    desplegarInfoActividadSeleccionada(null);
                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);
            }
        }
    }

    public void actividadesUsuario() {
        todasLasActividadesGridPane = new GridPane();
        misActividadesScroll.setContent(todasLasActividadesGridPane);
        misActividades = new ArrayList<>();
        misActividades.addAll(todasMisActividades());

        if(misActividades.size() > 0) {
            desplegarInfoActividadSeleccionada(misActividades.get(0));
            this.myListener = new MyListener() {


                @Override
                public void onClickActividad(ActividadObject actividadObject) {
                    desplegarInfoActividadSeleccionada(actividadObject);
                }

                @Override
                public void onClickUsuario(EmpleadoObject empleadoObject) {

                }
            };
        } else {
            cuposActividadDisplay.setText("");
            costoActividadDisplay.setText("");
            horaActividadDisplay.setText("");
            diaActividadDisplay.setText("");
            tipoActividadDisplay.setText("");
            nombreActividadDisplay.setText("");
            descripcionActividadDisplay.setText("");
            duracionActividadDisplay.setText("");
            imagenActividadDisplay.setImage(null);
            centroActividadDisplay.setText("");
            cancelarActividadBoton.setVisible(false);
        }

        int column = 0;
        int row = 1;
        try{
            for(ActividadObject actividad : misActividades) {
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

    public ActividadObject actividadEnDisplay;

    public void desplegarInfoActividadSeleccionada(@Nullable ActividadObject actividadObject) {
        actividadEnDisplay = actividadObject;
        if (actividadObject == null) {
            cuposActividadDisplay.setText("");
            costoActividadDisplay.setText("");
            horaActividadDisplay.setText("");
            diaActividadDisplay.setText("");
            tipoActividadDisplay.setText("");
            nombreActividadDisplay.setText("");
            descripcionActividadDisplay.setText("");
            duracionActividadDisplay.setText("");
            imagenActividadDisplay.setImage(null);
            centroActividadDisplay.setText("");
            cancelarActividadBoton.setVisible(false);
        } else {
            cuposActividadDisplay.setText("CUPOS: " + String.valueOf(actividadObject.getCupos()));
            costoActividadDisplay.setText("$" + String.valueOf(actividadObject.getCosto()));
            horaActividadDisplay.setText(actividadObject.getHora().toString());
            diaActividadDisplay.setText(actividadObject.getDia().toString());
            tipoActividadDisplay.setText(actividadObject.getTipo().getTipo());
            nombreActividadDisplay.setText(actividadObject.getNombre().toUpperCase());
            descripcionActividadDisplay.setText(actividadObject.getDescripcion());
            duracionActividadDisplay.setText(String.valueOf(actividadObject.getDuracion()) + " min");

            if (actividadObject.getImagen() != null) {
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
                Image imageView = new Image("/imagenes/imagenesDefault/actividaddefault.png");
                imagenActividadDisplay.setImage(imageView);
            }

            actividadSeleccionadaVBox.setStyle("-fx-background-color : #9AC8F5;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            centroActividadDisplay.setText(actividadObject.getCentroDeportivo().getNombre());
            Period period = Period.between(LocalDate.now(), actividadEnDisplay.getDia());
            if (period.getDays() <= 0) {
                cancelarActividadBoton.setVisible(false);
            } else {
                cancelarActividadBoton.setVisible(true);
            }
        }
    }

    public void onMisActividadesLabelClick(MouseEvent mouseEvent) {

    }

    public void onCancelarActividadBoton(ActionEvent event) {
        String nombre = actividadEnDisplay.getNombre();
        String dia = diaActividadDisplay.getText();
        String hora = horaActividadDisplay.getText();
        String centromail = actividadEnDisplay.getCentroMail();

        try {
            try {
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.delete("http://localhost:8987/inscripciones/delete/" + empleado.getMail() + "/" + nombre + "/" + dia + "/" + hora + "/" + centromail).asJson();

            } catch (Exception e) {
                System.out.println("Error borrando inscripcion: " + e);
            }

            if (actividadEnDisplay.isReservable()) {
                String json = "";
                try {
                    actividadEnDisplay.setCupos(actividadEnDisplay.getCupos() + 1);
                    ObjectMapper mapperActividad = new JsonMapper().builder()
                            .findAndAddModules()
                            .build();
                    mapperActividad.registerModule(new JavaTimeModule());
                    json = mapperActividad.writeValueAsString(actividadEnDisplay);
                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.put("http://localhost:8987/api/actividades/actualizar/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").body(json).asJson();
                } catch (Exception e) {
                    System.out.println("Error actualizando cupos: " + e.getMessage());
                }
            }

            misActividades = new ArrayList<>();
            misActividades.addAll(todasMisActividades());
            filtrarNuevas("NUEVAS");
        } catch (Exception e) {
            System.out.println("Error boton cancelar: " + e.getMessage());
        }
    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(UsuarioMisActividadesController.class.getResourceAsStream("/gym/Client/nuevo/MainUsuarioTodasActividades.fxml"));

            MainUsuarioTodasActividadesController mainUsuarioTodasActividadesController = fxmlLoader.getController();
            mainUsuarioTodasActividadesController.datosUsuario(empleado.getMail());
            mainUsuarioTodasActividadesController.actividadesDisponibles();
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
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
