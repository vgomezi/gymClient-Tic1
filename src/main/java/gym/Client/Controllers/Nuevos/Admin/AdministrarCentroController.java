package gym.Client.Controllers.Nuevos.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Controllers.LoginController;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AdministrarCentroController {

    @FXML
    private VBox centroRegistroVBox;

    @FXML
    private Button actualizarCentroBoton;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private TextField contrasenaCentroDisplay;

    @FXML
    private Button eliminarCentroBoton;

    @FXML
    private TextField emailCentroDisplay;

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
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLosCentrosTitleLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private ScrollPane todosLosCentrosScroll;

    private File fileImagen;

    private List<CentroDeportivoObject> centrosDeportivosList = new ArrayList<>();

    private List<CentroDeportivoObject> similarCentro = new ArrayList<>();

    private MyListenerCentro myListenerCentro;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagen/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
    }

    private List<CentroDeportivoObject> todosLosCentros() {
        List<CentroDeportivoObject> listaCentrosDeportivos = new ArrayList<>();
        CentroDeportivoObject centroDeportivoObject;

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/centros").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaCentrosDeportivos = mapper.readValue(json, new TypeReference<List<CentroDeportivoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaCentrosDeportivos;
    }

    private List<CentroDeportivoObject> similarCentros(String similar) {
        List<CentroDeportivoObject> listaCentrosSimilares = new ArrayList<>();
        CentroDeportivoObject centroDeportivoObject;

        String centro = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/centroDeportivo/similarCentroDeportivo/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            //System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaCentrosSimilares = mapper.readValue(json, new TypeReference<List<CentroDeportivoObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista centro similares "/* + listaActividades*/);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaCentrosSimilares;
    }

    public void todosCentros() {
        todasLasActividadesGridPane = new GridPane();
        todosLosCentrosScroll.setContent(todasLasActividadesGridPane);
        centrosDeportivosList.clear();
        centrosDeportivosList.addAll(todosLosCentros());

        this.myListenerCentro = new MyListenerCentro() {
            @Override
            public void onClickCentro(CentroDeportivoObject centroDeportivoObject) {

            }
        };

        System.out.println("entro datos MainAdminRegistrarCentro");

        int column = 0;
        int row = 1;
        try{
            for(CentroDeportivoObject centro : centrosDeportivosList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox centroVBox = fxmlLoader.load();
                CentroTodoController centroTodoController = fxmlLoader.getController();

                centroTodoController.setearDatos(centro, myListenerCentro);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todasLasActividadesGridPane.add(centroVBox, column++, row);
                GridPane.setMargin(centroVBox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    @FXML
    void onBusquedaKeyReleased(KeyEvent event) {
        this.myListenerCentro = new MyListenerCentro() {
            @Override
            public void onClickCentro(CentroDeportivoObject centroDeportivoObject) {
                centroRegistroVBox.setStyle("-fx-background-color : #9AC8F5;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
            }
        };
        //System.out.println(busquedaTextField.getText());
        if (busquedaTextField.getText().isEmpty()) {
            todasLasActividadesGridPane = new GridPane();
            todosLosCentrosScroll.setContent(todasLasActividadesGridPane);
            int column = 0;
            int row = 1;
            try{
                for(CentroDeportivoObject centro : centrosDeportivosList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox centroVBox = fxmlLoader.load();
                    CentroTodoController centroTodoController = fxmlLoader.getController();

                    centroTodoController.setearDatos(centro, myListenerCentro);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasActividadesGridPane.add(centroVBox, column++, row);
                    GridPane.setMargin(centroVBox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        } else {
            todasLasActividadesGridPane = new GridPane();
            todosLosCentrosScroll.setContent(todasLasActividadesGridPane);
            similarCentro = similarCentros(busquedaTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(CentroDeportivoObject centro : similarCentro) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/CentroTodo.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox centroVBox = fxmlLoader.load();
                    CentroTodoController centroTodoController = fxmlLoader.getController();

                    centroTodoController.setearDatos(centro, myListenerCentro);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todasLasActividadesGridPane.add(centroVBox, column++, row);
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
    void onAdministrarCentroLabelClick(MouseEvent event) {

    }

    @FXML
    void onTodosLosCentrosLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(AdministrarCentroController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarCentro.fxml"));

            MainAdminRegistrarCentroController mainAdminRegistrarCentroController = fxmlLoader.getController();
            mainAdminRegistrarCentroController.datosAdmin();
            mainAdminRegistrarCentroController.todosCentros();

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    void onActualizarCentroButtonClick(ActionEvent event) {

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
        fileChooser.setTitle("Elegir imagen usuario");
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
    void onEliminarCentroButtonClick(ActionEvent event) {

    }


    public void onTodasLasEmpresasLabelClick(MouseEvent mouseEvent) {
    }

    public void onAdministrarEmpresasLabelClick(MouseEvent mouseEvent) {
    }
}
