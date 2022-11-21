package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.*;
import gym.Client.Controllers.Nuevos.Admin.UsuarioEmpresaController;
import gym.Client.Controllers.Nuevos.Admin.UsuarioEmpresaNuevoController;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
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


public class MainEmpresaTodosUsuariosController implements Initializable {

    @FXML
    private HBox usuariosRecienteLayout;

    @FXML
    private Button administrarEmpresaBoton;

    @FXML
    private Label administrarUsuariosLabel;

    @FXML
    private Label anadidasRecientementeTitleLabel;

    @FXML
    private TextField apellidoRegistroEmpleado;

    @FXML
    private Label apellidoUsuarioDisplay;

    @FXML
    private TextField contrasenaRegistroEmpleado;

    @FXML
    private Label contraseñaUsuarioDisplay;

    @FXML
    private TextField emailRegistroEmpleado;

    @FXML
    private Label emailUsuarioDisplay;

    @FXML
    private ImageView imagenUsuarioRegistro;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreRegistroEmpleado;

    @FXML
    private Label nombreUsuarioDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private TextField busquedaEmpleadoTextField;

    @FXML
    private TextField telefonoRegistroEmpleado;

    @FXML
    private Label telefonoUsuarioDisplay;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasActividadesTitleLabel;

    @FXML
    private Label todosLosUsuariosLabel;

    @FXML
    private VBox usuarioSeleccionadoVBox;

    @FXML
    private Circle imagenEmpresaCirculo;

    @FXML
    private ScrollPane todosLosUsuariosScroll;

    @FXML
    private ScrollPane anadidosRecientementeUsuariosScroll;

    private String empresaLogInMail;

    private MyListener myListener;

    private File fileImagen;

    public EmpresaObject empresa;

    private List<EmpleadoObject> misEmpleados = new ArrayList<>();

    private List<EmpleadoObject> misNuevosEmpleados = new ArrayList<>();

    private List<EmpleadoObject> empleadosLike;



    @FXML
    void onAdministrarUsuariosLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaTodosUsuariosController.class.getResourceAsStream("/gym/Client/nuevo/EmpresaAdministrarUsuarios.fxml"));
            EmpresaAdministrarUsuariosController empresaAdministrarUsuariosController = fxmlLoader.getController();
            empresaAdministrarUsuariosController.setEmpresaLogInMail(empresa.getMail());
            empresaAdministrarUsuariosController.datosEmpresa(empresa.getMail());
            empresaAdministrarUsuariosController.empleadosEmpresa();
            empresaAdministrarUsuariosController.desplegarEmpleadoSeleccionado(null);

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    @FXML
    void onTodosLosUsuariosLabelClick(MouseEvent mouseEvent) {

    }

    private List<EmpleadoObject> misEmpleadosLike(String like) {
        List<EmpleadoObject> listaMisEmpleados = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios//similarEmpleado/" + empresa.getMail() + "/" + like).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpleados;
    }

    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent keyEvent) {

        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {}

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {
                usuarioSeleccionadoVBox.setStyle("-fx-background-color : #9AC8F5;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        };

        if (busquedaEmpleadoTextField.getText().isEmpty()) {
            todasLasActividadesGridPane = new GridPane();
            todosLosUsuariosScroll.setContent(todasLasActividadesGridPane);
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : misEmpleados) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));

                    VBox usuarioEmpresaVbox = fxmlLoader.load();
                    UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                    usuarioEmpresaController.setearDatos(empleado, myListener);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasActividadesGridPane.add(usuarioEmpresaVbox, column++, row);
                    GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        } else {
            todasLasActividadesGridPane = new GridPane();
            todosLosUsuariosScroll.setContent(todasLasActividadesGridPane);
            empleadosLike = misEmpleadosLike(busquedaEmpleadoTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : empleadosLike) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));

                    VBox usuarioEmpresaVbox = fxmlLoader.load();
                    UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                    usuarioEmpresaController.setearDatos(empleado, myListener);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasActividadesGridPane.add(usuarioEmpresaVbox, column++, row);
                    GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        }
    }


    @FXML
    void onRegistrarUsuarioBotonClick(ActionEvent actionEvent) {
        String nombre = nombreRegistroEmpleado.getText();
        String apellido = apellidoRegistroEmpleado.getText();
        String email = emailRegistroEmpleado.getText();
        String telefono = telefonoRegistroEmpleado.getText();
        String contrasena = contrasenaRegistroEmpleado.getText();
        String imagen = null;
        try {
            imagen = codificarImagenRegistroUsuario(fileImagen);
        } catch (Exception ignored) {

        }

        if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !contrasena.isEmpty()) {
            try {
                HttpResponse<String> apiResponse1 = null;

                apiResponse1 = Unirest.get("http://localhost:8987/api/usuarios//empleadoMail/" + email).header("Content-Type", "application/json").asObject(String.class);
                String existeEmpleadoMail = apiResponse1.getBody();

                if (existeEmpleadoMail.isEmpty()) {
                    try {
                        String json = "";
                        String json2 = "";

                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            ObjectMapper mapper2 = new ObjectMapper();
                            UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Usuario");

                            EmpleadoObject empleadoObject = new EmpleadoObject(userLoginObject, nombre, apellido, email, telefono, empresa, Integer.parseInt(empresa.getBono()), new Date(), 0, imagen);
                            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                            json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(empleadoObject);

                        } catch (Exception ignored) {
                        }
                        HttpResponse<JsonNode> apiResponse = null;
                        apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                        apiResponse = Unirest.post("http://localhost:8987/api/usuarios").header("Content-Type", "application/json").body(json2).asJson();

                        nombreRegistroEmpleado.clear();
                        apellidoRegistroEmpleado.clear();
                        emailRegistroEmpleado.clear();
                        telefonoRegistroEmpleado.clear();
                        contrasenaRegistroEmpleado.clear();
                        fileImagen = null;
                        Image image = new Image("/imagenes/imagenesDefault/usuariodefault.png");
                        imagenUsuarioRegistro.setImage(image);
                        empleadosEmpresa();
                        usuarioSeleccionadoVBox.setStyle("-fx-background-color : #1FDB5E;" +
                                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    } catch (UnirestException e) {
                        System.out.println("Error registrando usuario: " + e.getMessage());
                    }
                } else {
                    usuarioSeleccionadoVBox.setStyle("-fx-background-color : #f4f723;" +
                            "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
                    emailRegistroEmpleado.clear();
                }
            } catch (Exception e) {
                System.out.println("Error registrando usuario: " + e.getMessage());

            }

        } else {
            usuarioSeleccionadoVBox.setStyle("-fx-background-color : #E3350E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
    }

     private List<EmpleadoObject> misEmpleadosNuevos() {
        List<EmpleadoObject> listaMisNuevosEmpleados = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/nuevosEmpleadosEmpresa/" + empresa.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisNuevosEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisNuevosEmpleados;
    }

    private List<EmpleadoObject> todosMisEmpleados() {
        List<EmpleadoObject> listaMisEmpleados = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/empleadosEmpresa/" + empresa.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpleados;
    }

    public void empleadosEmpresa() {
        todasLasActividadesGridPane = new GridPane();
        todosLosUsuariosScroll.setContent(todasLasActividadesGridPane);
        this.usuariosRecienteLayout.getChildren().clear();
        misEmpleados.clear();
        misEmpleados.addAll(todosMisEmpleados());
        misNuevosEmpleados.clear();
        misNuevosEmpleados.addAll(misEmpleadosNuevos());

        this.myListener = new MyListener() {

            @Override
            public void onClickActividad(ActividadObject actividadObject) {
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {
                usuarioSeleccionadoVBox.setStyle("-fx-background-color : #9AC8F5;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        };

        int column = 0;
        int row = 1;
        try{
            for (EmpleadoObject misNuevosEmpleado : misNuevosEmpleados) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresaNuevo.fxml"));

                HBox anadidaRecienteBox = fxmlLoader.load();
                UsuarioEmpresaNuevoController usuarioEmpresaNuevoController = fxmlLoader.getController();

                usuarioEmpresaNuevoController.obtenerDatos(misNuevosEmpleado, myListener);

                this.usuariosRecienteLayout.getChildren().add(anadidaRecienteBox);
            }

            for(EmpleadoObject empleado : misEmpleados) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));

                VBox usuarioEmpresaVbox = fxmlLoader.load();
                UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                usuarioEmpresaController.setearDatos(empleado, myListener);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todasLasActividadesGridPane.add(usuarioEmpresaVbox, column++, row);
                GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imagen = new Image("/imagenes/imagenesDefault/usuariodefault.png");
        imagenUsuarioRegistro.setImage(imagen);
        usuarioSeleccionadoVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onImagenRegistroMouseClick(MouseEvent mouseEvent) {
        File file = tomarImagen(mouseEvent);
        String base64 = codificarImagenRegistroUsuario(file);
        Image imagen = decodificarImagen(base64);
        imagenUsuarioRegistro.setImage(imagen);
    }

    public File tomarImagen (MouseEvent mouseEvent) {
        File file = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Elegir imagen usuario");
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
            byte[] bytes = Files.readAllBytes(file.toPath());
            base64String = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
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

    public String getEmpresaLogInMail() {
        return empresaLogInMail;
    }

    public void setEmpresaLogInMail(String empresaLogInMail) {
        this.empresaLogInMail = empresaLogInMail;
    }

    public void onAdministrarEmpresaButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainEmpresaTodosUsuariosController.class.getResourceAsStream("/gym/Client/nuevo/AdministrarEmpresaDeuda.fxml"));

            AdministrarEmpresaDeudaController administrarEmpresaDeudaController = fxmlLoader.getController();
            administrarEmpresaDeudaController.setEmpresa(empresa);
            administrarEmpresaDeudaController.datosEmpresa(empresa);
            administrarEmpresaDeudaController.deudaEmpleado();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public void datosEmpresa(String correoElectronico) {
        EmpresaObject empresaObject = null;
        try {
            String empresa = "";
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + correoElectronico).asObject(String.class);
            empresa = apiResponse.getBody();

            if (!empresa.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                empresaObject = mapper.readValue(apiResponse.getBody(), EmpresaObject.class);
                this.empresa = empresaObject;
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo empresa: " + e.getMessage());
        }

        if(empresaObject.getImagen() != null) {
            byte[] imageDecoded = java.util.Base64.getDecoder().decode(empresaObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenEmpresaCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagenes/imagenesDefault/empresadefault.png");
            imagenEmpresaCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(empresaObject.getNombre());
    }

}
