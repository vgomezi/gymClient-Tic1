package gym.Client.Controllers.Nuevos.Admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Controllers.LoginController;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdministrarEmpresaController {

    @FXML
    private VBox empresaSeleccionadaVBox;

    @FXML
    private Button actualizarEmpresaBoton;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private Button eliminarEmpresaBoton;

    @FXML
    private Label emailEmpresaLabel;

    @FXML
    private ImageView imagenEmpresaDisplay;

    @FXML
    private Circle imagenAdministradorCirculo;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreAdministradorLabel;

    @FXML
    private TextField nombreEmpresaDisplay;

    @FXML
    private TextField bonoEmpleadosDisplay;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane todasLasActividadesGridPane;

    @FXML
    private ScrollPane todasLasEmpresasScroll;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private Label todosLosCentrosLabel;

    @FXML
    private Label administrarCentroLabel;

    @FXML
    private Label administrarEmpresaLabel;

    @FXML
    private Label todasLasEmpresasTitleLabel;

    private File fileImagen;

    private List<EmpresaObject> empresasList = new ArrayList<>();

    private List<CentroDeportivoObject> similarEmpresas = new ArrayList<>();

    private MyListenerEmpresa myListenerEmpresa;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagen/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
        todasEmpresas();
        desplegarEmpresaSeleccionada(null);
    }

    private List<EmpresaObject> todasLasEmpresas() {
        List<EmpresaObject> listaEmpresas = new ArrayList<>();
        EmpresaObject empresaObject;

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/allEmpresas").header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaEmpresas = mapper.readValue(json, new TypeReference<List<EmpresaObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpresas;
    }

    private List<EmpresaObject> similarEmpresas(String similar) {
        List<EmpresaObject> listaEmpresasSimilares = new ArrayList<>();
        EmpresaObject empresaObject;

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/similarEmpresa/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            //System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaEmpresasSimilares = mapper.readValue(json, new TypeReference<List<EmpresaObject>>() {});

            //System.out.println(actividad);
            System.out.println("Lista centro similares "/* + listaActividades*/);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpresasSimilares;
    }

    public void todasEmpresas() {
        todasLasActividadesGridPane = new GridPane();
        todasLasEmpresasScroll.setContent(todasLasActividadesGridPane);
        empresasList.clear();
        empresasList.addAll(todasLasEmpresas());

        this.myListenerEmpresa = new MyListenerEmpresa() {
            @Override
            public void onClickEmpresa(EmpresaObject empresaObject) {
                desplegarEmpresaSeleccionada(empresaObject);

            }
        };

        System.out.println("entro datos AdministrarEmpresa");

        int column = 0;
        int row = 1;
        try{
            for(EmpresaObject empresa : empresasList) {
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

                todasLasActividadesGridPane.add(empresaVBox, column++, row);
                GridPane.setMargin(empresaVBox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    public EmpresaObject empresaEnDisplay;

    public void desplegarEmpresaSeleccionada(@Nullable EmpresaObject empresaObject) {
        empresaEnDisplay = empresaObject;
        if (empresaObject != null) {
            if (empresaObject.getImagen() != null) {
                byte[] imageDecoded = Base64.getDecoder().decode(empresaObject.getImagen());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
                BufferedImage bImage = null;
                try {
                    bImage = ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image toAdd = SwingFXUtils.toFXImage(bImage, null);
                imagenEmpresaDisplay.setImage(toAdd);
            } else {
                Image imageView = new Image("/imagen/empresadefault.png");
                imagenEmpresaDisplay.setImage(imageView);
            }
            nombreEmpresaDisplay.setText(empresaObject.getNombre());
            bonoEmpleadosDisplay.setText(empresaObject.getBono());
            emailEmpresaLabel.setText(empresaObject.getMail());
            actualizarEmpresaBoton.setVisible(true);
            eliminarEmpresaBoton.setVisible(true);
            nombreEmpresaDisplay.setVisible(true);
            bonoEmpleadosDisplay.setVisible(true);
        } else {
            nombreEmpresaDisplay.clear();
            bonoEmpleadosDisplay.clear();
            emailEmpresaLabel.setText("");
            imagenEmpresaDisplay.setImage(null);
            actualizarEmpresaBoton.setVisible(false);
            eliminarEmpresaBoton.setVisible(false);
            nombreEmpresaDisplay.setVisible(false);
            bonoEmpleadosDisplay.setVisible(false);
        }
        empresaSeleccionadaVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onBusquedaKeyReleased(KeyEvent event) {

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

    public void datosEmpresa(String correoElectronico) {
        //fijarse main usuarios todas actividades

    }



    @FXML
    void onActualizarEmpresaButtonClick(MouseEvent event) {

    }

    @FXML
    void onEliminarEmpresaButtonClick(MouseEvent event) {

    }


    @FXML
    void onAdministrarCentroLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(AdministrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarCentro.fxml"));

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
    void onAdministrarEmpresaLabelClick(MouseEvent event) { }

    @FXML
    void onTodasLasEmpresasLabelClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(AdministrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarEmpresa.fxml"));

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
            Parent root1 = (Parent) fxmlLoader.load(AdministrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/MainAdminRegistrarCentro.fxml"));

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