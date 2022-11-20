package gym.Client.Controllers.Nuevos.Admin;


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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainAdminRegistrarEmpresaController implements Initializable {

    @FXML
    private VBox empresaRegistroVBox;

    @FXML
    private Label anadidasRecientementeTitleLabel;

    @FXML
    private TextField bonoEmpleadosRegistro;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private TextField contrasenaEmpresaRegistro;

    @FXML
    private TextField emailEmpresaRegistro;

    @FXML
    private HBox empresasRecientesLayout;

    @FXML
    private ImageView imagenEmpresaRegistro;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private TextField nombreEmpresaRegistro;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private Button registrarEmpresaBoton;

    @FXML
    private GridPane todasLasEmpresasGridPane;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private Label administrarEmpresaLabel;

    @FXML
    private ScrollPane todasLasEmpresasScroll;

    @FXML
    private Label todasLasEmpresasTitleLabel;

    private List<EmpresaObject> empresasList = new ArrayList<>();

    private List<EmpresaObject> nuevasEmpresas = new ArrayList<>();

    private List<EmpresaObject> similarEmpresa = new ArrayList<>();

    private MyListenerEmpresa myListenerEmpresa;

    private File fileImagen;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagen/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
    }

    private List<EmpresaObject> todasLasEmpresas() {
        List<EmpresaObject> listaEmpresas = new ArrayList<>();
        EmpresaObject empresaObject;

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/allEmpresas").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaEmpresas = mapper.readValue(json, new TypeReference<List<EmpresaObject>>() {
            });

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpresas;
    }

    private List<EmpresaObject> empresasNuevas() {
        List<EmpresaObject> listaNuevasEmpresas = new ArrayList<>();
        EmpresaObject empresaObject;

        String empresa = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/nuevasEmpresas").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaNuevasEmpresas = mapper.readValue(json, new TypeReference<List<EmpresaObject>>() {});

            //System.out.println("Lista mis empleados " + listaMisEmpleados);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaNuevasEmpresas;
    }

    public void todasEmpresas() {
        todasLasEmpresasGridPane = new GridPane();
        todasLasEmpresasScroll.setContent(todasLasEmpresasGridPane);
        this.empresasRecientesLayout.getChildren().clear();
        empresasList.clear();
        empresasList.addAll(todasLasEmpresas());
        nuevasEmpresas.clear();
        nuevasEmpresas.addAll(empresasNuevas());

        this.myListenerEmpresa = new MyListenerEmpresa() {
            @Override
            public void onClickEmpresa(EmpresaObject empresaObject) {
                empresaRegistroVBox.setStyle("-fx-background-color : #9AC8F5;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        };

        System.out.println("entro datos MainAdminRegistrarCentro");

        int column = 0;
        int row = 1;
        try {
            for (EmpresaObject nuevaEmpresa : nuevasEmpresas) {
                System.out.println("Entro try");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/EmpresaTodaNuevo.fxml"));
                System.out.println("Carga FXMLLoader");

                HBox anadidaRecienteBox = fxmlLoader.load();
                EmpresaTodaNuevoController empresaTodaNuevoController = fxmlLoader.getController();

                empresaTodaNuevoController.obtenerDatos(nuevaEmpresa, myListenerEmpresa);

                this.empresasRecientesLayout.getChildren().add(anadidaRecienteBox);
            }

            for (EmpresaObject empresa : empresasList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/EmpresaToda.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox empresaVBox = fxmlLoader.load();
                EmpresaTodaController empresaTodaController = fxmlLoader.getController();

                empresaTodaController.setearDatos(empresa, myListenerEmpresa);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todasLasEmpresasGridPane.add(empresaVBox, column++, row);
                GridPane.setMargin(empresaVBox, new Insets(10));

            }
        } catch (Exception e) {
            System.out.println("Error creando panel " + e);

        }
    }


    private List<EmpresaObject> similarEmpresas(String similar) {
        List<EmpresaObject> listaEmpresasSimilares = new ArrayList<>();
        EmpresaObject empresaObject;

        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/similarEmpresa/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            //System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaEmpresasSimilares = mapper.readValue(json, new TypeReference<List<EmpresaObject>>() {
            });

            //System.out.println(actividad);
            System.out.println("Lista empresas similares "/* + listaActividades*/);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpresasSimilares;
    }


    public void onBusquedaKeyReleased(KeyEvent keyEvent) {
        this.myListenerEmpresa = new MyListenerEmpresa() {
            @Override
            public void onClickEmpresa(EmpresaObject empresaObject) {

            }
        };

        //System.out.println(busquedaTextField.getText());
        if (busquedaTextField.getText().isEmpty()) {
            todasLasEmpresasGridPane = new GridPane();
            todasLasEmpresasScroll.setContent(todasLasEmpresasGridPane);
            int column = 0;
            int row = 1;
            try {
                for (EmpresaObject empresa : empresasList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/EmpresaToda.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox empresaVbox = fxmlLoader.load();
                    EmpresaTodaController empresaTodaController = fxmlLoader.getController();

                    empresaTodaController.setearDatos(empresa, myListenerEmpresa);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasEmpresasGridPane.add(empresaVbox, column++, row);
                    GridPane.setMargin(empresaVbox, new Insets(10));

                }
            } catch (Exception e) {
                System.out.println("Error creando panel " + e);

            }
        } else {
            todasLasEmpresasGridPane = new GridPane();
            todasLasEmpresasScroll.setContent(todasLasEmpresasGridPane);
            similarEmpresa = similarEmpresas(busquedaTextField.getText());
            int column = 0;
            int row = 1;
            try {
                for (EmpresaObject empresa : similarEmpresa) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/EmpresaToda.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox empresaVbox = fxmlLoader.load();
                    EmpresaTodaController empresaTodaController = fxmlLoader.getController();

                    empresaTodaController.setearDatos(empresa, myListenerEmpresa);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasEmpresasGridPane.add(empresaVbox, column++, row);
                    GridPane.setMargin(empresaVbox, new Insets(10));

                }
            } catch (Exception e) {
                System.out.println("Error creando panel " + e);

            }

        }
    }




    @FXML
    void onMouseClickedLogOut(MouseEvent mouseEvent) {
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

    public EmpresaObject empresa;


    @FXML
    void onRegistrarEmpresaButtonClick(MouseEvent event) {
        String nombre = nombreEmpresaRegistro.getText();
        String email = emailEmpresaRegistro.getText();
        String contrasena = contrasenaEmpresaRegistro.getText();
        String bono = bonoEmpleadosRegistro.getText();
        String imagen = null;
        try {
            imagen = codificarImagenRegistroUsuario(fileImagen);
        } catch (Exception ignored) {

        }

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasena.isEmpty() && !bono.isEmpty()) {
            try {
                HttpResponse<String> apiResponse1 = null;

                apiResponse1 = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + email).header("Content-Type", "application/json").asObject(String.class);
                String existeEmpresaMail = apiResponse1.getBody();

                if (existeEmpresaMail.isEmpty()) {
                    System.out.println("No existe");
                    try {
                        String json = "";
                        String json2 = "";

                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            ObjectMapper mapper2 = new ObjectMapper();
                            UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Empresa");
                            EmpresaObject empresaObject = new EmpresaObject(userLoginObject, nombre, bono, email, imagen, new Date());
                            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                            json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(empresaObject);

                        } catch (Exception e) {

                        }
                        HttpResponse<JsonNode> apiResponse = null;
                        apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                        apiResponse = Unirest.post("http://localhost:8987/api/empresas").header("Content-Type", "application/json").body(json2).asJson();

                        System.out.println("Hecho Empresa");

                        System.out.println("Hecho");

                        nombreEmpresaRegistro.clear();
                        bonoEmpleadosRegistro.clear();
                        emailEmpresaRegistro.clear();
                        contrasenaEmpresaRegistro.clear();
                        fileImagen = null;
                        Image image = new Image("/imagen/empresadefault.png");
                        imagenEmpresaRegistro.setImage(image);
                        todasEmpresas();
                        empresaRegistroVBox.setStyle("-fx-background-color : #1FDB5E;" +
                                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    } catch (Exception e) {

                    }
                } else {
                    empresaRegistroVBox.setStyle("-fx-background-color : #f4f723;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    emailEmpresaRegistro.clear();

                }
            } catch (Exception e) {

            }
        } else {
            empresaRegistroVBox.setStyle("-fx-background-color : #E3350E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }

    }





    public void onImagenEmpresaRegistroMouseClick(MouseEvent mouseEvent) {
        File file = tomarImagen(mouseEvent);
        String base64 = codificarImagenRegistroUsuario(file);
        Image imagen = decodificarImagen(base64);
        imagenEmpresaRegistro.setImage(imagen);
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

    public String codificarImagenRegistroUsuario(File file) {
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
        byte[] imageDecoded = Base64.decodeBase64(imagen);
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
        Image imagen = new Image("/imagen/empresadefault.png");
        imagenEmpresaRegistro.setImage(imagen);
        empresaRegistroVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }


    @FXML
    void onAdministrarCentroLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminRegistrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarCentro.fxml"));

            AdministrarCentroController administrarCentroController = fxmlLoader.getController();
            administrarCentroController.datosAdmin();
            administrarCentroController.todosCentros();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    void onAdministrarEmpresaLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminRegistrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarEmpresa.fxml"));

            AdministrarEmpresaController administrarEmpresaController = fxmlLoader.getController();
            administrarEmpresaController.datosAdmin();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    @FXML
    void onTodasLasEmpresasLabelClick(MouseEvent event) { }

    @FXML
    void onTodosLosCentrosLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminRegistrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarCentro.fxml"));

            MainAdminRegistrarCentroController mainAdminRegistrarCentroController = fxmlLoader.getController();
            mainAdminRegistrarCentroController.datosAdmin();
            mainAdminRegistrarCentroController.todosCentros();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }
}

