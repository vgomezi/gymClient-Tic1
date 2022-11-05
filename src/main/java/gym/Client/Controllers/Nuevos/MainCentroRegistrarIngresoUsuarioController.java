package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.CentroDeportivoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.util.ArrayList;
import java.util.List;

public class MainCentroRegistrarIngresoUsuarioController {
    // implements Initializable

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Label todasLasActividadesLabel;

    @FXML
    public Label registrarIngresoUsuarioLabel;

    @FXML
    public Button administrarCentroBoton;

    @FXML
    public Label nombreActividadDisplay;

    @FXML
    public Label reservableActividadDisplay;

    @FXML
    public ChoiceBox tipoActividadChoiceBox;

    ObservableList<String> tipoActividadChoiceBoxList = FXCollections.
            observableArrayList("Canchas", "Gimnasio/Sala", "Náuticas");

    @FXML
    private void initialize() {
        tipoActividadChoiceBox.setItems(tipoActividadChoiceBoxList);
        tipoActividadChoiceBox.setValue("Categoria");
    }

    @FXML
    public ImageView imagenActividadDisplay;

    @FXML
    public Label diaActividadDisplay;

    @FXML
    public Label horaActividadDisplay;


    @FXML
    public Label descripcionActividadDisplay;

    @FXML
    public Label cuposActividadDisplay;

    @FXML
    public Label duracionActividadDisplay;

    @FXML
    public Label costoActividadDisplay;

    @FXML
    public TextField mailUsuarioDisplay;

    @FXML
    public Button registrarIngresoUsuarioBoton;


    public String mailUsuarioIngreso;

    private MyListener myListener;

    private List<ActividadObject> todasLasActividades = new ArrayList<>();

    //Poner en lugar de anadidas recientemente las proximas actividades que estan por ocurrir, de forma que sea más
    //fácil encontrarlas
    private List<ActividadObject> proximasActividades = new ArrayList<>();

    private List<ActividadObject> similarActividades = new ArrayList<>();

    public void onEnterPressed(KeyEvent keyEvent) {
    }

    public void onMouseClickedLogOut(MouseEvent mouseEvent) {
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

    public void datosCentro(String correoElectronico) {
        //fijarse main usuarios todas actividades

    }

    public void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainCentroRegistrarIngresoUsuarioController.class.getResourceAsStream("/gym/Client/nuevo/CentroRegistrarActividad.fxml"));

            CentroRegistrarActividadController centroRegistrarActividadController = fxmlLoader.getController();
            System.out.println(centro.getMail());
            centroRegistrarActividadController.datosCentro(centro.getMail());

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    public void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {

    }

    public void onRegistrarIngresoUsuarioButtonClick(MouseEvent mouseEvent) {

    }
}