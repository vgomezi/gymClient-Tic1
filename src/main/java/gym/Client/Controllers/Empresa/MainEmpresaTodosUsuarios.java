package gym.Client.Controllers.Empresa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Nuevos.Admin.UsuarioEmpresaController;
import gym.Client.Controllers.Nuevos.Admin.UsuarioEmpresaNuevoController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainEmpresaTodosUsuarios implements Initializable {

    @FXML
    private VBox usuarioSeleccionadoVBox;

    @FXML
    private HBox actividadesRecienteLayout;

    @FXML
    private Button administrarEmpresaBoton;

    @FXML
    private Label administrarUsuariosLabel;

    @FXML
    private Label anadidasRecientementeTitleLabel;

    @FXML
    private Label apellidoUsuarioDisplay;

    @FXML
    private Label contraseñaUsuarioDisplay;

    @FXML
    private Label emailUsuarioDisplay;

    @FXML
    private ImageView imagenActividadDisplay1;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nombreUsuarioDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private Button registrarUsuarioBoton;

    @FXML
    private Label telefonoUsuarioDisplay;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasActividadesTitleLabel;

    @FXML
    private Label todosLosUsuariosLabel;

    private MyListener myListener;

    private List<EmpleadoObject> anadidosRecienteUsuarioLista = new ArrayList<>();
    private List<EmpleadoObject> todosLosUsuarios = new ArrayList<>();

    private List<EmpleadoObject> anadidosRecientemente() {
        List<EmpleadoObject> listaEmpleadosNuevos = new ArrayList<>();
        EmpleadoObject empleadoObject;

        String empleado = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/allEmpleados").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            System.out.println("Hago object mapper");
            //mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaEmpleadosNuevos = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

            System.out.println(empleado);
            System.out.println("Lista actividades AÑADIDAS RECIENTEMENTE " + listaEmpleadosNuevos);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpleadosNuevos;
    }

    private List<EmpleadoObject> todosLosUsuarios() {
        List<EmpleadoObject> listaEmpleados = new ArrayList<>();
        EmpleadoObject empleadoObject;

        String empleado = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/allEmpleados").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

            System.out.println(empleado);
            System.out.println("Lista actividades AÑADIDAS RECIENTEMENTE " + listaEmpleados);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpleados;
    }

    public void desplegarInfoUsuarioSeleccionado(EmpleadoObject empleadoObject) {
        nombreUsuarioDisplay.setText(empleadoObject.getNombre());
        apellidoUsuarioDisplay.setText(empleadoObject.getApellido());
        emailUsuarioDisplay.setText(empleadoObject.getMail());
        telefonoUsuarioDisplay.setText(empleadoObject.getTelefono());
        usuarioSeleccionadoVBox.setStyle("-fx-background-color : #dbae1a;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Entro initialize");
        System.out.println(anadidosRecientemente());
        anadidosRecienteUsuarioLista.addAll(anadidosRecientemente());
        todosLosUsuarios.addAll(todosLosUsuarios());

        if(todosLosUsuarios.size() > 0) {
            desplegarInfoUsuarioSeleccionado(todosLosUsuarios.get(0));
            this.myListener = new MyListener() {
                @Override
                public void onClickActividad(ActividadObject actividadObject) {

                }

                @Override
                public void onClickUsuario(EmpleadoObject empleadoObject) {
                    desplegarInfoUsuarioSeleccionado(empleadoObject);
                }
            };
        }

        System.out.println(anadidosRecienteUsuarioLista + "anadidos reciente lista");
        System.out.println("entro initialize mainEmpresaTodosUsuarios");

        int column = 0;
        int row = 1;
        try{
            for (int i = 0; i < anadidosRecienteUsuarioLista.size(); i++) {
                System.out.println("tamaño i = " + anadidosRecienteUsuarioLista.size());
                System.out.println("Entro try");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresaNuevo.fxml"));
                System.out.println("Carga FXMLLoader");

                HBox anadidaRecienteBox = fxmlLoader.load();
                UsuarioEmpresaNuevoController usuarioEmpresaNuevoController = fxmlLoader.getController();

                usuarioEmpresaNuevoController.obtenerDatos(anadidosRecienteUsuarioLista.get(i), myListener);

                this.actividadesRecienteLayout.getChildren().add(anadidaRecienteBox);
            }

            for(EmpleadoObject empleado : todosLosUsuarios) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox todaActividadbox = fxmlLoader.load();
                UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                usuarioEmpresaController.setearDatos(empleado, myListener);

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

    @FXML
    void onEnterPressed(KeyEvent event) {

    }

    @FXML
    void onMouseClickedLogOut(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage1 = (Stage) source.getScene().getWindow();
        stage1.close();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            //Parent root1 = (Parent) fxmlLoader.load(LoginController.class.getResourceAsStream());
            Parent root = FXMLLoader.load(getClass().getResource("/gym/Client/Login.fxml"));

            Stage stage = new Stage();

            //stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Login");
            stage.setIconified(false);
            stage.setResizable(false);
            stage.getIcons().add(new Image("FitnessIcon.png"));
            stage.setScene(new Scene(root));
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

    public void onTodosLosUsuariosLabelClick(MouseEvent mouseEvent) {
    }


    public void onAdministrarUsuariosLabelClick(MouseEvent mouseEvent) {
    }
}
