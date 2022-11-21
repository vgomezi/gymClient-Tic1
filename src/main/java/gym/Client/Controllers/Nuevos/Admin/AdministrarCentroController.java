package gym.Client.Controllers.Nuevos.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Controllers.LoginController;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class AdministrarCentroController implements Initializable {

    @FXML
    private VBox centroRegistroVBox;

    @FXML
    private Button actualizarCentroBoton;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private Label emailCentroLabel;

    @FXML
    private Button eliminarCentroBoton;

    @FXML
    private ImageView imagenActividadDisplay;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private TextField nombreCentroDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane todosLosCentrosGridPane;

    @FXML
    private Label todasLosCentrosTitleLabel;

    @FXML
    private ScrollPane todosLosCentrosScroll;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private Label administrarEmpresaLabel;

    private File fileImagen;

    private List<CentroDeportivoObject> centrosDeportivosList = new ArrayList<>();

    private List<CentroDeportivoObject> similarCentro = new ArrayList<>();

    private MyListenerCentro myListenerCentro;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagenes/imagenesDefault/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
        desplegarCentroSeleccionado(null);
    }

    private List<CentroDeportivoObject> todosLosCentros() {
        List<CentroDeportivoObject> listaCentrosDeportivos = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centros").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaCentrosDeportivos = mapper.readValue(json, new TypeReference<List<CentroDeportivoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaCentrosDeportivos;
    }

    private List<CentroDeportivoObject> similarCentros(String similar) {
        List<CentroDeportivoObject> listaCentrosSimilares = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/similarCentroDeportivo/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaCentrosSimilares = mapper.readValue(json, new TypeReference<List<CentroDeportivoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaCentrosSimilares;
    }

    public void todosCentros() {
        todosLosCentrosGridPane = new GridPane();
        todosLosCentrosScroll.setContent(todosLosCentrosGridPane);
        centrosDeportivosList.clear();
        centrosDeportivosList.addAll(todosLosCentros());

        this.myListenerCentro = new MyListenerCentro() {
            @Override
            public void onClickCentro(CentroDeportivoObject centroDeportivoObject) {
                desplegarCentroSeleccionado(centroDeportivoObject);
            }
        };

        int column = 0;
        int row = 1;
        try{
            for(CentroDeportivoObject centro : centrosDeportivosList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));

                VBox centroVBox = fxmlLoader.load();
                CentroTodoController centroTodoController = fxmlLoader.getController();

                centroTodoController.setearDatos(centro, myListenerCentro);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todosLosCentrosGridPane.add(centroVBox, column++, row);
                GridPane.setMargin(centroVBox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    public CentroDeportivoObject centroEnDisplay;

    public void desplegarCentroSeleccionado(@Nullable CentroDeportivoObject centroDeportivoObject) {
        centroEnDisplay = centroDeportivoObject;
        if (centroDeportivoObject != null) {
            if (centroDeportivoObject.getImagen() != null) {
                byte[] imageDecoded = Base64.getDecoder().decode(centroDeportivoObject.getImagen());
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
                Image imageView = new Image("/imagenes/imagenesDefault/centrodefault.png");
                imagenActividadDisplay.setImage(imageView);
            }
            nombreCentroDisplay.setText(centroDeportivoObject.getNombre());
            emailCentroLabel.setText(centroDeportivoObject.getMail());
            actualizarCentroBoton.setVisible(true);
            eliminarCentroBoton.setVisible(true);
            nombreCentroDisplay.setVisible(true);
        } else {
            nombreCentroDisplay.clear();
            emailCentroLabel.setText("");
            imagenActividadDisplay.setImage(null);
            actualizarCentroBoton.setVisible(false);
            eliminarCentroBoton.setVisible(false);
            nombreCentroDisplay.setVisible(false);
        }
        centroRegistroVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onBusquedaKeyReleased(KeyEvent event) {
        this.myListenerCentro = new MyListenerCentro() {
            @Override
            public void onClickCentro(CentroDeportivoObject centroDeportivoObject) {
                desplegarCentroSeleccionado(centroDeportivoObject);
                centroRegistroVBox.setStyle("-fx-background-color : #9AC8F5;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        };
        if (busquedaTextField.getText().isEmpty()) {
            todosLosCentrosGridPane = new GridPane();
            todosLosCentrosScroll.setContent(todosLosCentrosGridPane);
            int column = 0;
            int row = 1;
            try{
                for(CentroDeportivoObject centro : centrosDeportivosList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));

                    VBox centroVBox = fxmlLoader.load();
                    CentroTodoController centroTodoController = fxmlLoader.getController();

                    centroTodoController.setearDatos(centro, myListenerCentro);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todosLosCentrosGridPane.add(centroVBox, column++, row);
                    GridPane.setMargin(centroVBox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        } else {
            todosLosCentrosGridPane = new GridPane();
            todosLosCentrosScroll.setContent(todosLosCentrosGridPane);
            similarCentro = similarCentros(busquedaTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(CentroDeportivoObject centro : similarCentro) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));

                    VBox centroVBox = fxmlLoader.load();
                    CentroTodoController centroTodoController = fxmlLoader.getController();

                    centroTodoController.setearDatos(centro, myListenerCentro);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todosLosCentrosGridPane.add(centroVBox, column++, row);
                    GridPane.setMargin(centroVBox, new Insets(10));

                }
            } catch (Exception e){
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

    public CentroDeportivoObject centro;



    @FXML
    void onActualizarCentroButtonClick(ActionEvent event) {
        String nombre = nombreCentroDisplay.getText();
        String imagen = null;
        try {
            imagen = codificarImagenRegistroUsuario(fileImagen);
        } catch (Exception ignored) {

        }

        if (!nombre.isEmpty()) {
            try {
                centroEnDisplay.setNombre(nombre);
                if (imagen != null) {
                    centroEnDisplay.setImagen(imagen);
                }
                String json = "";
                try {
                    ObjectMapper mapperEmpleado = new ObjectMapper();
                    json = mapperEmpleado.writeValueAsString(centroEnDisplay);
                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.put("http://localhost:8987/api/centroDeportivo/actualizar/" + centroEnDisplay.getMail()).header("Content-Type", "application/json").body(json).asJson();


                } catch (Exception e) {
                    System.out.println("Error actualizando put: " + e.getMessage());

                }
            } catch (Exception e) {

            }
        } else {

        }
        centrosDeportivosList.clear();
        todosCentros();
        desplegarCentroSeleccionado(null);
        fileImagen = null;
    }

    @FXML
    void onEliminarCentroButtonClick(ActionEvent event) {
        String mailCentro = emailCentroLabel.getText();

        List<ActividadObject> listaActividades = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/actividadesCentro/" + mailCentro).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        if (listaActividades.size() == 0) {
            try {
                HttpResponse<JsonNode> apiResponse = null;
                apiResponse = Unirest.delete("http://localhost:8987/api/centroDeportivo/delete/" + mailCentro).asJson();

            } catch (Exception e) {
                System.out.println("Error borrando inscripcion: " + e);
            }
            centrosDeportivosList.clear();
            todosCentros();
            desplegarCentroSeleccionado(null);
            centroRegistroVBox.setStyle("-fx-background-color : #1FDB5E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        } else {
            centroRegistroVBox.setStyle("-fx-background-color : #E3350E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
    }

    @FXML
    void onImagenDisplayMouseClick (MouseEvent event) {
        File file = tomarImagen(event);
        String base64 = codificarImagenRegistroUsuario(file);
        Image imagen = decodificarImagen(base64);
        imagenActividadDisplay.setImage(imagen);
    }

    public File tomarImagen (MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elegir imagen centro");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
        fileImagen = file;
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


    @FXML
    void onAdministrarCentroLabelClick(MouseEvent event) { }

    @FXML
    void onAdministrarEmpresaLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(AdministrarCentroController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarEmpresa.fxml"));

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
            Parent root1 = (Parent) fxmlLoader.load(AdministrarCentroController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarEmpresa.fxml"));

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
            Parent root1 = (Parent) fxmlLoader.load(AdministrarCentroController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarCentro.fxml"));

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imageView2 = new Image("/imagenes/imagenesDefault/centrodefault.png");
        imagenActividadDisplay.setImage(imageView2);
    }
}
