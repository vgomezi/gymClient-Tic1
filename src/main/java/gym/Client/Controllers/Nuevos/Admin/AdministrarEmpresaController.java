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
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdministrarEmpresaController {

    @FXML
    private VBox actividadSeleccionadaVBox;

    @FXML
    private Button actualizarEmpresaBoton;

    @FXML
    private TextField busquedaTextField;

    @FXML
    private TextField contrasenaEmpresaDisplay;

    @FXML
    private Button eliminarEmpresaBoton;

    @FXML
    private TextField emailEmpresaDisplay;

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