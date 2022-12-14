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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    @FXML
    private TextField busquedaTextField;

    public EmpresaObject empresa;

    public CentroDeportivoObject centro;

    private List<PagoEmpCentObject> misEmpresas = new ArrayList<>();

    private List<PagoEmpCentObject> similarEmpresas = new ArrayList<>();

    @FXML
    private Label todasLasActividadesLabel;



    @FXML
    void onBusquedaEmpleadoKeyReleased(KeyEvent keyEvent) {
        if (busquedaTextField.getText().isEmpty()) {
            todasLasEmpresasGridPane = new GridPane();
            liquidacionEmpresasScroll.setContent(todasLasEmpresasGridPane);
            misEmpresas.clear();
            misEmpresas.addAll(todasMisEmpresas());

            int column = 0;
            int row = 1;
            try{
                for(PagoEmpCentObject pago : misEmpresas) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/DeudaEmpCentPane.fxml"));

                    VBox DeudaEmpCentVbox = fxmlLoader.load();
                    DeudaEmpCentPaneController deudaEmpCentPaneController = fxmlLoader.getController();

                    deudaEmpCentPaneController.setearDatos(pago);


                    row++;

                    todasLasEmpresasGridPane.add(DeudaEmpCentVbox, column, row);
                    GridPane.setMargin(DeudaEmpCentVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        } else {
            todasLasEmpresasGridPane = new GridPane();
            liquidacionEmpresasScroll.setContent(todasLasEmpresasGridPane);
            similarEmpresas = similarPagoEmpresas(busquedaTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(PagoEmpCentObject pago : similarEmpresas) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/DeudaEmpCentPane.fxml"));

                    VBox DeudaEmpCentVbox = fxmlLoader.load();
                    DeudaEmpCentPaneController deudaEmpCentPaneController = fxmlLoader.getController();

                    deudaEmpCentPaneController.setearDatos(pago);


                    row++;

                    todasLasEmpresasGridPane.add(DeudaEmpCentVbox, column, row);
                    GridPane.setMargin(DeudaEmpCentVbox, new Insets(10));

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

    @FXML
    void onRegistrarIngresoUsuarioLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(CentroTodasActividadesController.class.getResourceAsStream("/gym/Client/nuevo/MainCentroRegistrarIngresoUsuario.fxml"));

            MainCentroRegistrarIngresoUsuarioController mainCentroRegistrarIngresoUsuarioController = fxmlLoader.getController();
            mainCentroRegistrarIngresoUsuarioController.datosCentro(centro.getMail());
            mainCentroRegistrarIngresoUsuarioController.actividadesProximasCentro();
            mainCentroRegistrarIngresoUsuarioController.actividadesCentro();
            mainCentroRegistrarIngresoUsuarioController.desplegarInfoActividadSeleccionada(null);


            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }

    }

    @FXML
    void onTodasLasActividadesLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(MainCentroRegistrarIngresoUsuarioController.class.getResourceAsStream("/gym/Client/nuevo/CentroTodasActividades.fxml"));

            CentroTodasActividadesController centroRegistrarActividadController = fxmlLoader.getController();
            centroRegistrarActividadController.datosCentro(centro);
            centroRegistrarActividadController.actividadesCentro();

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
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
            Image imageView = new Image("/imagenes/imagenesDefault/centrodefault.png");
            imagenCentroDeudaCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(centroDeportivoObject.getNombre());
    }


    private List<PagoEmpCentObject> todasMisEmpresas() {
        List<PagoEmpCentObject> listaMisEmpresas = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/pagos/allPagosCentro/" + centro.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisEmpresas = mapper.readValue(json, new TypeReference<List<PagoEmpCentObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpresas;
    }

    private List<PagoEmpCentObject> similarPagoEmpresas(String similar) {
        List<PagoEmpCentObject> listaEmpresasSimilares = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/pagos/pagosByCentroAndSearch/" + centro.getMail() + "/" + similar).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaEmpresasSimilares = mapper.readValue(json, new TypeReference<List<PagoEmpCentObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaEmpresasSimilares;
    }

    public void deudaEmpresa() {
        todasLasEmpresasGridPane = new GridPane();
        liquidacionEmpresasScroll.setContent(todasLasEmpresasGridPane);
        misEmpresas.clear();
        misEmpresas.addAll(todasMisEmpresas());
        int deudaTotal = 0;

        int column = 0;
        int row = 1;
        try{
            for(PagoEmpCentObject pago : misEmpresas) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/DeudaEmpCentPane.fxml"));

                VBox DeudaEmpCentVbox = fxmlLoader.load();
                DeudaEmpCentPaneController deudaEmpCentPaneController = fxmlLoader.getController();

                deudaEmpCentPaneController.setearDatos(pago);
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
