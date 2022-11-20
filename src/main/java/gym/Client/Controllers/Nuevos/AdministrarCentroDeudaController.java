package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.*;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class AdministrarCentroDeudaController {

    @FXML
    private VBox DeudaEmpCentVbox;

    @FXML
    private Label liquidacionTitleLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Circle imagenCentroDeudaCirculo;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private Label registrarIngresoUsuarioLabel;

    @FXML
    private GridPane todasLasEmpresasGridPane;

    @FXML
    private ScrollPane liquidacionEmpresasScroll;

    @FXML
    private Label deudaTotalLabel;

    public EmpresaObject empresa;

    public CentroDeportivoObject centro;

    private MyListener myListener;

    private List<PagoEmpCentObject> misEmpresas = new ArrayList<>();

    @FXML
    private Label todasLasActividadesLabel;



    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent keyEvent) {

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
    void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {

    }

    @FXML
    void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {

    }

    public void datosCentro(CentroDeportivoObject centroDeportivoObject) {
        this.centro = centroDeportivoObject;
        if(centroDeportivoObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(centroDeportivoObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenCentroDeudaCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/centrodefault.png");
            imagenCentroDeudaCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(centroDeportivoObject.getNombre());
    }


    private List<PagoEmpCentObject> todasMisEmpresas() {
        List<PagoEmpCentObject> listaMisEmpresas = new ArrayList<>();
        PagoEmpCentObject pagoEmpCentObject;

        String pago = "";
        try {
            HttpResponse<String> apiResponse = null;

            //ver direccion http
            apiResponse = Unirest.get("http://localhost:8987/api/pagos/allPagosCentro/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaMisEmpresas = mapper.readValue(json, new TypeReference<List<PagoEmpCentObject>>() {});

            System.out.println(pago);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpresas;
    }

    public void deudaEmpresa() {
        todasLasEmpresasGridPane = new GridPane();
        liquidacionEmpresasScroll.setContent(todasLasEmpresasGridPane);
        misEmpresas.clear();
        misEmpresas.addAll(todasMisEmpresas());
        int deudaTotal = 0;

        this.myListener = new MyListener() {

            @Override
            public void onClickActividad(ActividadObject actividadObject) {
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {

            }
        };

        System.out.println("hola");

        int column = 0;
        int row = 1;
        try{
            for(PagoEmpCentObject pago : misEmpresas) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/DeudaEmpCentPane.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox DeudaEmpCentVbox = fxmlLoader.load();
                DeudaEmpCentPaneController deudaEmpCentPaneController = fxmlLoader.getController();

                deudaEmpCentPaneController.setearDatos(pago, myListener);
                deudaTotal = deudaTotal + pago.getMonto();


                row++;

                todasLasEmpresasGridPane.add(DeudaEmpCentVbox, column, row);
                GridPane.setMargin(DeudaEmpCentVbox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
        deudaTotalLabel.setText("Importe total: " + deudaTotal);
    }

    public CentroDeportivoObject getCentro() {
        return centro;
    }

    public void setCentro(CentroDeportivoObject centro) {
        this.centro = centro;
    }
}
