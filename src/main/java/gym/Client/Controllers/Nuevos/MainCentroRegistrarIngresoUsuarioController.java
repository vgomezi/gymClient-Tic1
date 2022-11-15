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
import gym.Client.Classes.InscripcionesActividadesObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.ActividadRecienteController;
import gym.Client.Controllers.Usuario.Actividades.ActividadTodaController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.lang.Nullable;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class MainCentroRegistrarIngresoUsuarioController {
    // implements Initializable

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public TextField busquedaActividadTextfield;

    @FXML
    public Label todasLasActividadesLabel;

    @FXML
    public Label registrarIngresoUsuarioLabel;

    @FXML
    public Button administrarCentroBoton;

    @FXML
    public Label nombreActividadDisplay;

    @FXML
    public Label reservableActividadDisplay;

    @FXML
    public VBox actividadSeleccionadaVBox;

    @FXML
    public ImageView imagenActividadDisplay;

    @FXML
    public Label diaActividadDisplay;

    @FXML
    public Label horaActividadDisplay;

    @FXML
    public Label tipoActividadDisplay;

    @FXML
    public Label cuposActividadDisplay;

    @FXML
    public Label duracionActividadDisplay;

    @FXML
    public Label costoActividadDisplay;

    @FXML
    public Circle centroUsuarioCirculo;

    @FXML
    public TextField mailUsuarioDisplay;

    @FXML
    private HBox proximasActividadesLayout;

    @FXML
    public Button registrarIngresoUsuarioBoton;

    @FXML
    public ScrollPane todasLasActividadesScroll;

    @FXML
    public GridPane todasLasActividadesGridPane;


    public String mailUsuarioIngreso;

    private MyListener myListener;

    private List<ActividadObject> todasLasActividades;

    //Poner en lugar de anadidas recientemente las proximas actividades que estan por ocurrir, de forma que sea más
    //fácil encontrarlas
    private List<ActividadObject> proximasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();

    public void onEnterPressed(KeyEvent keyEvent) {
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

    public void datosCentro(String correoElectronico) {
        CentroDeportivoObject centroDeportivoObject = null;
        try {
            System.out.println("try obtener empresa");
            String centroD = "";
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centrosMail/" + correoElectronico).asObject(String.class);
            centroD = apiResponse.getBody();
            if (!centroD.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if empresa");
                centroDeportivoObject = mapper.readValue(apiResponse.getBody(), CentroDeportivoObject.class);
                this.centro = centroDeportivoObject;
                System.out.println(centroDeportivoObject.getMail());
            }
        } catch (Exception e) {
            System.out.println("Try obtener empresa error");
        }

        if(centroDeportivoObject.getImagen() != null) {
            byte[] imageDecoded = java.util.Base64.getDecoder().decode(centroDeportivoObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            centroUsuarioCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/centrodefault.png");
            centroUsuarioCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(centroDeportivoObject.getNombre());
    }

    public ActividadObject actividadEnDisplay;

    public void desplegarInfoActividadSeleccionada(@Nullable ActividadObject actividadObject) {
        actividadEnDisplay = actividadObject;
        if (actividadObject != null) {
            cuposActividadDisplay.setText("CUPOS: " + String.valueOf(actividadObject.getCupos()));
            costoActividadDisplay.setText("$" + String.valueOf(actividadObject.getCosto()));
            horaActividadDisplay.setText(actividadObject.getHora().toString());
            diaActividadDisplay.setText(actividadObject.getDia().toString());
            tipoActividadDisplay.setText(actividadObject.getTipo().getTipo());
            nombreActividadDisplay.setText(actividadObject.getNombre().toUpperCase());
            duracionActividadDisplay.setText(String.valueOf(actividadObject.getDuracion()) + " min");
            if (actividadObject.isReservable()) {
                reservableActividadDisplay.setText("Reservable");
            } else {
                reservableActividadDisplay.setText("Sin reserva");
            }
            mailUsuarioDisplay.setVisible(true);
            registrarIngresoUsuarioBoton.setVisible(true);


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
                Image imageView = new Image("/imagen/usuariodefault.png");
                imagenActividadDisplay.setImage(imageView);
            }
        } else {
            cuposActividadDisplay.setText("");
            costoActividadDisplay.setText("");
            horaActividadDisplay.setText("");
            diaActividadDisplay.setText("");
            tipoActividadDisplay.setText("");
            nombreActividadDisplay.setText("");
            duracionActividadDisplay.setText("");
            imagenActividadDisplay.setImage(null);
            reservableActividadDisplay.setText("");
            mailUsuarioDisplay.setVisible(false);
            registrarIngresoUsuarioBoton.setVisible(false);
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

    private List<ActividadObject> proximasActividadesCentro() {
        List<ActividadObject> listaActividades = new ArrayList<>();
        ActividadObject actividadObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/proximasActividadesCentro/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Logro json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista actividades Proximas Actividades " /*+ listaActividades*/);
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

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/similarActividadCentro/" + similar + "/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
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

    public void actividadesProximasCentro() {
        if (proximasActividades.isEmpty()) {
            System.out.println("entro anadidosreciente if");
            proximasActividades.addAll(proximasActividadesCentro());
        }

        if(proximasActividades.size() > 0) {
            //desplegarInfoActividadSeleccionada(misActividades.get(0));
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

        try {
            for (ActividadObject proximasActividade : proximasActividades) {
                //System.out.println("tamaño i = " + anadidosRecienteLista.size());
                System.out.println("Entro try");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/formularios/OpcionesUsuario/Actividades/ActividadReciente.fxml"));
                System.out.println("Carga FXMLLoader");

                HBox anadidaRecienteBox = fxmlLoader.load();
                ActividadRecienteController actividadRecienteController = fxmlLoader.getController();

                actividadRecienteController.obtenerDatos(proximasActividade, myListener);

                this.proximasActividadesLayout.getChildren().add(anadidaRecienteBox);
            }
        } catch (Exception e) {
            System.out.println("Error cargando actividades proximas centro");
        }
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
                    desplegarInfoActividadSeleccionada(actividadObject);
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

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainCentroRegistrarIngresoUsuarioController.class.getResourceAsStream("/gym/Client/nuevo/CentroTodasActividades.fxml"));

            CentroTodasActividadesController centroRegistrarActividadController = fxmlLoader.getController();
            System.out.println(centro.getMail());
            centroRegistrarActividadController.datosCentro(centro);

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public void onRegistrarIngresoUsuarioButtonClick(MouseEvent mouseEvent) {
        if (!mailUsuarioDisplay.getText().isEmpty()) {
            String mailUsuario = mailUsuarioDisplay.getText();

            try {
                HttpResponse<String> apiResponse1 = null;

                apiResponse1 = Unirest.get("http://localhost:8987/api/usuarios/empleadoMail/" + mailUsuario).header("Content-Type", "application/json").asObject(String.class);
                String existeEmpleadoMail = apiResponse1.getBody();

                if (!existeEmpleadoMail.isEmpty()) {

                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println("Entro if usuario");
                    EmpleadoObject empleadoObject = mapper.readValue(existeEmpleadoMail, EmpleadoObject.class);

                    if (actividadEnDisplay.isReservable()) {
                        String json = "";

                        try {
                            HttpResponse<String> apiResponse = null;
                            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcion/" + mailUsuario + "/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).asObject(String.class);
                            json = apiResponse.getBody();
                        } catch (Exception e) {
                            System.out.println("Error obteniendo actividad");
                        }
                        System.out.println(json);

                        if (!json.isEmpty()) {
                            HttpResponse<JsonNode> apiResponse = null;
                            apiResponse = Unirest.put("http://localhost:8987/inscripciones/actualizar/" + mailUsuario + "/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").body(json).asJson();
                            System.out.println("Put Hecho");

                            System.out.println("Actualizo la inscripcion");

                            actividadSeleccionadaVBox.setStyle("-fx-background-color : #1FDB5E;" +
                                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

                        } else {
                            actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                            System.out.println("No reservó");
                        }
                        mailUsuarioDisplay.clear();

                        // verificar que existe la reserva
                    } else if (actividadEnDisplay.getCupos() > 0) {
                        String json = "";

                        try {
                            HttpResponse<String> apiResponse = null;
                            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcion/" + mailUsuario + "/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").asObject(String.class);
                            System.out.println("Obtener la inscripcion hecha");
                            json = apiResponse.getBody();
                            System.out.println(json);
                        } catch (Exception e) {
                            System.out.println("Error obteniendo actividad cupos mayor a '0");
                        }

                        if (!json.isEmpty()) {
                            HttpResponse<JsonNode> apiResponse = null;
                            apiResponse = Unirest.put("http://localhost:8987/inscripciones/actualizar/" + mailUsuario + "/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").body(json).asJson();
                            System.out.println("Put Hecho");
                            System.out.println("Actualizo la inscripcion");

                        } else {

                            String json1 = "";

                            try {
                                ObjectMapper mapper1 = new JsonMapper().builder().findAndAddModules().build();
                                mapper1.registerModule(new JavaTimeModule());
                                InscripcionesActividadesObject inscripcionesActividadesObject = new InscripcionesActividadesObject(mailUsuario, actividadEnDisplay.getNombre(), actividadEnDisplay.getDia(), actividadEnDisplay.getHora(), actividadEnDisplay.getCentroMail(), true, empleadoObject, actividadEnDisplay, "GUARDAR", new Date());
                                json1 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(inscripcionesActividadesObject);
                                System.out.println("json hecho");

                                HttpResponse<JsonNode> apiResponse = null;
                                apiResponse = Unirest.post("http://localhost:8987/inscripciones").header("Content-Type", "application/json").body(json1).asJson();
                                System.out.println("Inscripciones actividades reserva hecho");
                            } catch (Exception e) {
                                System.out.println("Error ingresando reserva");
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Creo la inscripcion");
                            System.out.println("Pintar de verde");

                        }

                        String json3 = "";
                        try {
                            actividadEnDisplay.setCupos(actividadEnDisplay.getCupos()-1);
                            ObjectMapper mapperActividad = new JsonMapper().builder()
                                    .findAndAddModules()
                                    .build();
                            mapperActividad.registerModule(new JavaTimeModule());
                            json3 = mapperActividad.writeValueAsString(actividadEnDisplay);
                            HttpResponse<JsonNode> apiResponse = null;
                            apiResponse = Unirest.put("http://localhost:8987/api/actividades/actualizar/" + actividadEnDisplay.getNombre() + "/" + actividadEnDisplay.getDia() + "/" + actividadEnDisplay.getHora() + "/" + actividadEnDisplay.getCentroMail()).header("Content-Type", "application/json").body(json3).asJson();
                            System.out.println("Put Hecho");
                            System.out.println("actualizacion cupos actividad hecha");


                        } catch (Exception e) {
                            System.out.println("Error actualizando put: " + e.getMessage());

                        }
                        mailUsuarioDisplay.clear();
                        actividadSeleccionadaVBox.setStyle("-fx-background-color : #1FDB5E;" +
                                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

                        System.out.println("Pintar en verde");

                    } else {
                        mailUsuarioDisplay.clear();
                        actividadSeleccionadaVBox.setStyle("-fx-background-color : #E3350E;" +
                                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                        System.out.println("No quedan cupos");
                    }
                } else {
                    mailUsuarioDisplay.clear();
                    actividadSeleccionadaVBox.setStyle("-fx-background-color : #f4f723;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    System.out.println("No existe el usuario con ese mail");
                }

            } catch (Exception e){
                System.out.println("Error obteniendo usuario ingreso");
            }

        } else {
            actividadSeleccionadaVBox.setStyle("-fx-background-color : #f4f723;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            System.out.println("Pinto de color que el mail está vacío");
        }

    }

    public void onAdministrarCentroButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainCentroRegistrarIngresoUsuarioController.class.getResourceAsStream("/gym/Client/nuevo/AdministrarCentroDeuda.fxml"));

            AdministrarCentroDeudaController administrarCentroDeudaController = fxmlLoader.getController();
            administrarCentroDeudaController.setCentro(centro);
            administrarCentroDeudaController.datosCentro(centro);
            administrarCentroDeudaController.deudaEmpresa();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


    public void onBusquedaActividadKeyReleased(KeyEvent keyEvent) {
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {
                desplegarInfoActividadSeleccionada(actividadObject);
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };
        //System.out.println(busquedaTextField.getText());
        if (busquedaActividadTextfield.getText().isEmpty()) {
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
            similarActividades = similarActividades(busquedaActividadTextfield.getText());
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
                    System.out.println(similarActividades.size());
                    GridPane.setMargin(todaActividadbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }

        }
    }
}
