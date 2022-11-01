package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Classes.UserLoginObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
import java.util.ResourceBundle;


public class MainEmpresaTodosUsuariosController implements Initializable {

    @FXML
    private HBox actividadesRecienteLayout;

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

    private String empresaLogInMail;

    private File fileImagen;

    @FXML
    void onAdministrarUsuariosLabelClick(MouseEvent event) {

    }

    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent event) {

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

    @FXML
    void onTodosLosUsuariosLabelClick(MouseEvent event) {

    }

    @FXML
    void onRegistrarUsuarioBotonClick(ActionEvent event) {
        System.out.println("Apreto Boton");
        String nombre = nombreRegistroEmpleado.getText()/*.toLowerCase()*/;
        String apellido = apellidoRegistroEmpleado.getText()/*.toLowerCase()*/;
        String email = emailRegistroEmpleado.getText();
        String telefono = telefonoRegistroEmpleado.getText();
        String contrasena = contrasenaRegistroEmpleado.getText();
        String imagen = codificarImagenRegistroUsuario(fileImagen);

        if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !contrasena.isEmpty()) {
            try {
                String json = "";
                String json2 = "";

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Usuario");

                    String empresa = "";
                    try {
                        HttpResponse<String> apiResponse = null;

                        apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + empresaLogInMail).asObject(String.class);
                        empresa = apiResponse.getBody();
                        System.out.println(empresa);

                        if (!empresa.isBlank()) {
                            ObjectMapper mapper1 = new ObjectMapper();
                            EmpresaObject empresaObject = mapper1.readValue(apiResponse.getBody(), EmpresaObject.class);

                            EmpleadoObject empleadoObject = new EmpleadoObject(userLoginObject, nombre, apellido, email, telefono, empresaObject, Integer.parseInt(empresaObject.getBono()), Integer.parseInt(empresaObject.getBono()), 0, imagen);
                            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                            json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(empleadoObject);
                            System.out.println(json);
                        }
                    } catch (Exception e) {

                    }
                    //Cambiar definiciones obteniendo la empresa y sus datos
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                apiResponse = Unirest.post("http://localhost:8987/api/usuarios").header("Content-Type", "application/json").body(json2).asJson();
                System.out.println("Hecho Usuario");

                System.out.println("Hecho");
                nombreRegistroEmpleado.clear();
                apellidoRegistroEmpleado.clear();
                emailRegistroEmpleado.clear();
                telefonoRegistroEmpleado.clear();
                contrasenaRegistroEmpleado.clear();
                Image image = new Image("/imagen/usuariodefault.png");
                imagenUsuarioRegistro.setImage(image);
            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
        } else {
            //Agregar label
        }



    }

    public String getEmpresaLogInMail() {
        return empresaLogInMail;
    }

    public void setEmpresaLogInMail(String empresaLogInMail) {
        this.empresaLogInMail = empresaLogInMail;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imagen = new Image("/imagen/usuariodefault.png");
        imagenUsuarioRegistro.setImage(imagen);

    }

    @FXML
    void onImagenRegistroMouseClick(MouseEvent event) {
        File file = tomarImagen(event);
        String base64 = codificarImagenRegistroUsuario(file);
        Image imagen = decodificarImagen(base64);
        imagenUsuarioRegistro.setImage(imagen);
    }

    public File tomarImagen (MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen centro");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        fileImagen = file;
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


    /*
    * System.out.println(usuarioEmpresaCrearUsuario);
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String email = emailText.getText();
        String telefono = telefonoText.getText();
        String contrasena = contrasenaText.getText();
        String imagen = registrarEmpleadoAction(event);

        if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !contrasena.isEmpty()) {

            try {
                String json = "";
                String json2 = "";

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    UserLoginObject userLoginObject = new UserLoginObject(email, contrasena, "Usuario");

                    String empresa  = "";
                    try {
                        HttpResponse<String> apiResponse = null;

                        apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + usuarioEmpresaCrearUsuario).asObject(String.class);
                        empresa = apiResponse.getBody();
                        System.out.println(empresa);

                        if (!empresa.isBlank()) {
                            ObjectMapper mapper1 = new ObjectMapper();
                            EmpresaObject empresaObject = mapper.readValue(apiResponse.getBody(), EmpresaObject.class);

                            //Corregir imagen al fondo
                            EmpleadoObject empleadoObject = new EmpleadoObject(userLoginObject, nombre, apellido, email, telefono, empresaObject, Integer.parseInt(empresaObject.getBono()), Integer.parseInt(empresaObject.getBono()), 0, imagen);
                            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginObject);
                            json2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(empleadoObject);
                            System.out.println(json);
                        }
                    } catch (Exception e) {

                    }
                    //Cambiar definiciones obteniendo la empresa y sus datos
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json).asJson();
                apiResponse = Unirest.post("http://localhost:8987/api/usuarios").header("Content-Type", "application/json").body(json2).asJson();
                System.out.println("Hecho Usuario");

                System.out.println("Hecho");
                nombreText.clear();
                apellidoText.clear();
                emailText.clear();
                telefonoText.clear();
                contrasenaText.clear();
                /*Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

} catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error");

        }
    * */

}
