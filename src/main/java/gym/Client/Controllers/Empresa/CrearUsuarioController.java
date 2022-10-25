package gym.Client.Controllers.Empresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Classes.UserLoginObject;
import gym.Client.Controllers.Admin.AdminCentro.BuscarCentroController;
import gym.Client.Controllers.Admin.AdminEmpresa.DatosBuscarEmpresaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class CrearUsuarioController {

    private String usuarioEmpresaCrearUsuario;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Label nombreLabel;

    @FXML
    private TextField nombreText;

    @FXML
    private Label apellidoLabel;

    @FXML
    private TextField apellidoText;

    @FXML
    private Label telefonoLabel;

    @FXML
    private TextField telefonoText;

    @FXML
    private Label contrasenaLabel;

    @FXML
    private PasswordField contrasenaText;

    @FXML
    private Button crearBoton;

    @FXML
    private Button cancelarBoton;

    public String getUsuarioEmpresaCrearUsuario() {
        return usuarioEmpresaCrearUsuario;
    }

    public void setUsuarioEmpresaCrearUsuario(String usuarioEmpresaCrearUsuario) {
        this.usuarioEmpresaCrearUsuario = usuarioEmpresaCrearUsuario;
    }

    @FXML
    protected void onCrearButtonClick(ActionEvent event) {
        System.out.println(usuarioEmpresaCrearUsuario);
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String email = emailText.getText();
        String telefono = telefonoText.getText();
        String contrasena = contrasenaText.getText();

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

                            EmpleadoObject empleadoObject = new EmpleadoObject(userLoginObject, nombre, apellido, email, telefono, empresaObject, Integer.parseInt(empresaObject.getBono()), Integer.parseInt(empresaObject.getBono()), 0);
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
                System.out.println("Hecho Empresa");

                System.out.println("Hecho");
                nombreText.clear();
                apellidoText.clear();
                emailText.clear();
                telefonoText.clear();
                contrasenaText.clear();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }

            /*
            try {
                String json1 = "";
                String json2 = "";

                try {
                    ObjectMapper mapper1 = new ObjectMapper();
                    ObjectMapper mapper2 = new ObjectMapper();
                    ObjectNode rest1 = mapper1.createObjectNode();
                    ObjectNode rest2 = mapper2.createObjectNode();
                    rest1.put("contrasena", contrasena);
                    rest1.put("mail", mail);
                    rest1.put("tipoDeUsuario", "Usuario");
                    rest2.put("apellido", apellido);
                    rest2.put("nombre", nombre);
                    rest2.put("mail", mail);
                    rest2.put("telefono", telefono);
                    //rest2.put("empresa", "b");
                    rest2.put("saldoDisponible", "500");
                    json1 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(rest1);
                    json2 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(rest2);
                } catch (Exception ignored) {
                }
                HttpResponse<JsonNode> apiResponse1 = null;
                HttpResponse<JsonNode> apiResponse2 = null;
                try {
                    apiResponse1 = Unirest.post("http://localhost:8987/api/login").header("Content-Type", "application/json").body(json1).asJson();
                    System.out.println("Hecho login");
                    System.out.println(json2);
                    apiResponse2 = Unirest.post("http://localhost:8987/api/usuarios").header("Content-Type", "application/json").body(json2).asJson();
                    System.out.println("Hecho usuario");

                } catch (UnirestException el) {
                    throw new RuntimeException(el);
                }
                System.out.println("Hecho");
            }catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error");

            }*/


        } else {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error");
            window.setMinWidth(250);

            Label label = new Label();
            label.setText("Los datos no han sido completados correctamente");
            Button closeButton = new Button("Aceptar");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();


        }

        /*String nR = nombre.getText();
        String tR = telefono.getText();
        String dR = direccion.getText();

        if (!nR.isEmpty() && !dR.isEmpty() && !tR.isEmpty()) {
            try {
                Integer tR2 = Integer.parseInt(telefono.getText());
                try {
                    String json = "";

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        ObjectNode rest = mapper.createObjectNode();
                        rest.put("nombre", nR);
                        rest.put("telefono", tR);
                        rest.put("direccion", dR);
                        json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rest);
                    } catch (Exception ignored) {
                    }
                    HttpResponse<JsonNode> apiResponse = null;
                    try {
                        apiResponse = Unirest.post("http://localhost:8987/api/restaurant").header("Content-Type", "application/json").body(json).asJson();

                    } catch (UnirestException el) {
                        throw new RuntimeException(el);
                    }

                    welcomeText.setText("Se creó el restaurante!");
                } catch (Exception e) {
                    e.printStackTrace();
                    welcomeText.setText("Restaurante ya registrado");
                }
                nombre.clear();
                telefono.clear();
                direccion.clear();
            } catch (Exception E) {
                welcomeText.setText("Teléfono no válido");
            }
        } else {
            nombre.clear();
            telefono.clear();
            direccion.clear();
            welcomeText.setText("Error en la entrada de datos");
        }*/
    }

    @FXML
    protected void onCancelarButtonClick(ActionEvent event) {
        apellidoText.clear();
        telefonoText.clear();
        contrasenaText.clear();
        emailText.clear();
        nombreText.clear();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}