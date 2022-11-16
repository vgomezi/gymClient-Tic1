package gym.Client.Controllers.Nuevos.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.TipoActividadObject;
import gym.Client.Controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

public class MainAdminController {

    @FXML
    private Label cantidadCentrosLabel;

    @FXML
    private Label cantidadEmpresasLabel;

    @FXML
    private Button crearTipoButton;

    @FXML
    private TextField crearTipoTextField;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane tiposActividadGridPane;

    @FXML
    private ScrollPane tiposActividadScroll;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private Label administrarEmpresaLabel;


    @FXML
    private Label cantidadUsuariosLabel;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagen/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
        setNumeroEmpresas();
        setNumeroCentros();
        setNumeroUsuarios();
        tiposActividades();
    }

    public void setNumeroEmpresas() {
        HttpResponse<String> apiResponse = null;
        try {
            apiResponse = Unirest.get("http://localhost:8987/api/empresas/numberEmpresas").asObject(String.class);
            System.out.println("Logre get tipos actividades");
            cantidadEmpresasLabel.setText(apiResponse.getBody());
        } catch (Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    public void setNumeroCentros() {
        HttpResponse<String> apiResponse = null;
        try {
            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/numberCentros").asObject(String.class);
            System.out.println("Logre get tipos actividades");
            cantidadCentrosLabel.setText(apiResponse.getBody());
        } catch (Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    public void setNumeroUsuarios() {
        HttpResponse<String> apiResponse = null;
        try {
            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/cantidadEmpleados").asObject(String.class);
            System.out.println("Logre get tipos actividades");
            cantidadUsuariosLabel.setText(apiResponse.getBody());
        } catch (Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    public void tiposActividades() {
        tiposActividadGridPane = new GridPane();
        tiposActividadScroll.setContent(tiposActividadGridPane);
        List<TipoActividadObject> tipos = new ArrayList<>();
        try {
            HttpResponse<String> apiResponse = null;
            apiResponse = Unirest.get("http://localhost:8987/api/tipoactividad/allTipoActividad").asObject(String.class);
            System.out.println("Logre get tipos actividades");

            if (!apiResponse.getBody().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if inicializoChoiceBox");
                tipos = mapper.readValue(apiResponse.getBody(), new TypeReference<List<TipoActividadObject>>() {
                });
            }
        } catch (Exception e) {
            System.out.println("Error cargando tipos");
        }

        int column = 0;
        int row = 1;
        try{
            for(TipoActividadObject tipoActividad : tipos) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/TiposActividad.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox tipoActividadVBox = fxmlLoader.load();
                TiposActividadController tiposActividadController = fxmlLoader.getController();

                tiposActividadController.setearDatos(tipoActividad);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                tiposActividadGridPane.add(tipoActividadVBox, column++, row);
                GridPane.setMargin(tipoActividadVBox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel PARA FILTRO TODAS " + e);

        }
    }

    @FXML
    void onCrearTipoButtonClick(ActionEvent event) {
        String tipo = crearTipoTextField.getText();

        if (!tipo.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TipoActividadObject tipoActividadObject = new TipoActividadObject(tipo);
                String json = mapper.writeValueAsString(tipoActividadObject);
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.post("http://localhost:8987/api/tipoactividad").header("Content-Type", "application/json").body(json).asJson();

            } catch (Exception e) {
                System.out.println("Error creando tipo");
            }
        }
        crearTipoTextField.clear();
        tiposActividades();
    }

    @FXML
    void onAdministrarCentroLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarCentro.fxml"));

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
            Parent root1 = (Parent) fxmlLoader.load(MainAdminController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarEmpresa.fxml"));

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
    void onTodasLasEmpresasLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarEmpresa.fxml"));

            MainAdminRegistrarEmpresaController mainAdminRegistrarEmpresaController = fxmlLoader.getController();
            mainAdminRegistrarEmpresaController.datosAdmin();
            mainAdminRegistrarEmpresaController.todasEmpresas();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    void onTodosLosCentrosLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarCentro.fxml"));

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

    @FXML
    void onMouseClickedLogOut(MouseEvent event) {
        Node source = (Node) event.getSource();
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
}
