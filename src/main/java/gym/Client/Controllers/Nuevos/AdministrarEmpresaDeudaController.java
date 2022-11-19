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
import java.util.List;

public class AdministrarEmpresaDeudaController {

    @FXML
    private VBox DeudaUsuEmpVbox;

    @FXML
    private Label administrarUsuariosLabel;

    @FXML
    private Label liquidacionTitleLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private BorderPane pantallaMainUsuario;

    @FXML
    private GridPane todosLosEmpleadosGridPane;

    @FXML
    private Circle imagenEmpresaDeudaCirculo;

    @FXML
    private ScrollPane liquidacionEmpleadoScroll;

    public EmpleadoObject empleado;

    public EmpresaObject empresa;

    private MyListener myListener;

    private List<EmpleadoObject> misEmpleados = new ArrayList<>();

    @FXML
    private Label todosLosUsuariosLabel;

    @FXML
    void onAdministrarUsuariosLabelClick(MouseEvent mouseEvent) {

    }

    @FXML
    void onTodosLosUsuariosLabelClick(MouseEvent mouseEvent) {

    }

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

    private List<EmpleadoObject> todosMisEmpleados() {
        List<EmpleadoObject> listaMisEmpleados = new ArrayList<>();
        PagoUsuEmpObject pagoUsuEmpObject;

        String pago = "";
        try {
            HttpResponse<String> apiResponse = null;
            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/empleadosEmpresa/" + empresa.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaMisEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

            System.out.println(pago);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpleados;
    }

    public void deudaEmpleado() {
        todosLosEmpleadosGridPane = new GridPane();
        liquidacionEmpleadoScroll.setContent(todosLosEmpleadosGridPane);
        misEmpleados.clear();
        misEmpleados.addAll(todosMisEmpleados());

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
            for(EmpleadoObject empleadoObject : misEmpleados) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/DeudaUsuEmpPane.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox DeudaUsuEmpVbox = fxmlLoader.load();
                DeudaUsuEmpPaneController deudaUsuEmpPaneController = fxmlLoader.getController();

                deudaUsuEmpPaneController.setearDatos(empleadoObject, myListener);
                deudaUsuEmpPaneController.actividadesEmpleado();

                row++;

                todosLosEmpleadosGridPane.add(DeudaUsuEmpVbox, column, row);
                GridPane.setMargin(DeudaUsuEmpVbox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    public EmpleadoObject getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoObject empleado) {
        this.empleado = empleado;
    }

    public EmpresaObject getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaObject empresa) {
        this.empresa = empresa;
    }

    public void datosEmpresa(EmpresaObject empresaObject) {
        this.empresa = empresaObject;
        if(empresaObject.getImagen() != null) {
            byte[] imageDecoded = java.util.Base64.getDecoder().decode(empresaObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenEmpresaDeudaCirculo.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/empresadefault.png");
            imagenEmpresaDeudaCirculo.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(empresaObject.getNombre());
    }
}
