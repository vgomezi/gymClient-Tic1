package gym.Client.Controllers.Nuevos.Admin;


import gym.Client.Classes.EmpresaObject;
import gym.Client.Controllers.LoginController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainAdminRegistrarEmpresaController {

    @FXML
    private VBox actividadSeleccionadaVBox;

    @FXML
    private Label administrarEmpresaLabel;

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
    private GridPane todasLasActividadesGridPane;

    @FXML
    private Label todasLasEmpresasLabel;

    @FXML
    private ScrollPane todasLasEmpresasScroll;

    @FXML
    private Label todasLasEmpresasTitleLabel;

    public void datosAdmin() {
        nombreAdministradorLabel.setText("ADMINISTRADOR");
        Image imageView = new Image("/imagen/imagenadministrador.png");
        imagenAdministradorCirculo.setFill(new ImagePattern(imageView));
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
    void onAdministrarEmpresaLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainAdminRegistrarEmpresaController.class.getResourceAsStream("/gym/Client/nuevo/Admin/AdministrarEmpresa.fxml"));

            AdministrarEmpresaController administrarEmpresaController = fxmlLoader.getController();
            administrarEmpresaController.datosAdmin();

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    @FXML
    void onTodasLasEmpresasLabelClick(MouseEvent event) {

    }

    @FXML
    void onRegistrarEmpresaButtonClick(MouseEvent event) {

    }

}
