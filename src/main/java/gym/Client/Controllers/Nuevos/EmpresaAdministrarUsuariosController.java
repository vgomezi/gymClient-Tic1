package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.EmpresaObject;
import gym.Client.Controllers.Nuevos.Admin.UsuarioEmpresaController;
import gym.Client.Controllers.LoginController;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

public class EmpresaAdministrarUsuariosController implements Initializable {

    @FXML
    public Label logOutLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    private ImageView imagenUsuarioDisplay;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private Label emailUsuarioLabel;

    @FXML
    private TextField saldoTextField;

    @FXML
    private TextField deudaTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private VBox empleadoSeleccionadoVBox;

    @FXML
    private Circle empresaImagenCircle;

    @FXML
    private TextField busquedaEmpleadoTextField;


    @FXML
    public ChoiceBox<String> deudoresChoiceBox;

    @FXML
    public ScrollPane todosLosEmpleadosScroll;

    @FXML
    public Button actualizarUsuarioBoton;

    @FXML
    public Button eliminarUsuarioBoton;

    @FXML
    private GridPane todosLosEmpleadosGridPane;

    public String empresaLogInMail;

    private MyListener myListener;

    private File fileImagen;

    private List<EmpleadoObject> misEmpleados = new ArrayList<>();

    private List<EmpleadoObject> empleadosLike;

    public EmpresaObject empresa;

    public void datosEmpresa(String correoElectronico) {
        EmpresaObject empresaObject = null;
        try {
            System.out.println("try obtener empresa");
            String empresa = "";
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/empresas/empresaMail/" + correoElectronico).asObject(String.class);
            empresa = apiResponse.getBody();
            System.out.println("Imprimo empresa");
            //System.out.println(empresa);


            if (!empresa.isBlank()) {
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("Entro if empresa");
                empresaObject = mapper.readValue(apiResponse.getBody(), EmpresaObject.class);
                this.empresa = empresaObject;
                //System.out.println(empresaObject);
            }
            System.out.println("Try obtener empresa hecho");
        } catch (Exception e) {
            System.out.println("Try obtener empresa error");
        }

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
            empresaImagenCircle.setFill(new ImagePattern(toAdd));
        } else {
            Image imageView = new Image("/imagen/empresadefault.png");
            empresaImagenCircle.setFill(new ImagePattern(imageView));
        }

        nombreLabel.setText(empresaObject.getNombre());
    }

    public EmpleadoObject empleadoEnDisplay;

    public void desplegarEmpleadoSeleccionado(@Nullable EmpleadoObject empleadoObject) {
        empleadoEnDisplay = empleadoObject;
        if (empleadoObject != null) {
            if (empleadoObject.getImagen() != null) {
                byte[] imageDecoded = Base64.getDecoder().decode(empleadoObject.getImagen());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
                BufferedImage bImage = null;
                try {
                    bImage = ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image toAdd = SwingFXUtils.toFXImage(bImage, null);
                imagenUsuarioDisplay.setImage(toAdd);
            } else {
                Image imageView = new Image("/imagen/usuariodefault.png");
                imagenUsuarioDisplay.setImage(imageView);
            }
            nombreTextField.setText(empleadoObject.getNombre());
            apellidoTextField.setText(empleadoObject.getApellido());
            emailUsuarioLabel.setText(empleadoObject.getMail());
            saldoTextField.setText(String.valueOf(empleadoObject.getSaldoDisponible()));
            deudaTextField.setText(String.valueOf(empleadoObject.getDeuda()));
            telefonoTextField.setText(empleadoObject.getTelefono());
            actualizarUsuarioBoton.setVisible(true);
            eliminarUsuarioBoton.setVisible(true);
            nombreTextField.setVisible(true);
            apellidoTextField.setVisible(true);
            saldoTextField.setVisible(true);
            deudaTextField.setVisible(true);
            telefonoTextField.setVisible(true);
        } else {
            nombreTextField.clear();
            apellidoTextField.clear();
            emailUsuarioLabel.setText("");
            saldoTextField.clear();
            deudaTextField.clear();
            telefonoTextField.clear();
            imagenUsuarioDisplay.setImage(null);
            actualizarUsuarioBoton.setVisible(false);
            eliminarUsuarioBoton.setVisible(false);
            nombreTextField.setVisible(false);
            apellidoTextField.setVisible(false);
            saldoTextField.setVisible(false);
            deudaTextField.setVisible(false);
            telefonoTextField.setVisible(false);

        }
        empleadoSeleccionadoVBox.setStyle("-fx-background-color : #9AC8F5;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    private List<EmpleadoObject> todosMisEmpleados() {
        List<EmpleadoObject> listaMisEmpleados = new ArrayList<>();
        EmpleadoObject empleadoObject;

        String empleado = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios/empleadosEmpresa/" + empresa.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");
            //System.out.println(json);

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            listaMisEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

            //System.out.println(empleado);
            //System.out.println("Lista mis empleados " + listaMisEmpleados);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpleados;
    }

    public void empleadosEmpresa() {
        todosLosEmpleadosGridPane = new GridPane();
        todosLosEmpleadosScroll.setContent(todosLosEmpleadosGridPane);
        misEmpleados.addAll(todosMisEmpleados());

        this.myListener = new MyListener() {

            @Override
            public void onClickActividad(ActividadObject actividadObject) {
            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {
                desplegarEmpleadoSeleccionado(empleadoObject);

            }
        };

        System.out.println("entro datos MainEmpresa");

        int column = 0;
        int row = 1;
        try{
            for(EmpleadoObject empleado : misEmpleados) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                System.out.println("Carga FXMLLoader");

                VBox usuarioEmpresaVbox = fxmlLoader.load();
                UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                usuarioEmpresaController.setearDatos(empleado, myListener);

                if (column == 2) {
                    column = 0;
                    ++row;
                }

                todosLosEmpleadosGridPane.add(usuarioEmpresaVbox, column++, row);
                GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
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

    public void onTodosLosUsuariosLabelClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 = (Parent) fxmlLoader.load(EmpresaAdministrarUsuariosController.class.getResourceAsStream("/gym/Client/nuevo/MainEmpresaTodosUsuarios.fxml"));

            MainEmpresaTodosUsuariosController mainEmpresaTodosUsuariosController = fxmlLoader.getController();
            System.out.println(empresa.getMail());
            mainEmpresaTodosUsuariosController.datosEmpresa(empresa.getMail());
            mainEmpresaTodosUsuariosController.empleadosEmpresa();

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }

    @FXML
    void onActualizarUsuarioButtonClick(ActionEvent actionEvent) {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String saldo = saldoTextField.getText();
        String deuda = deudaTextField.getText();
        String telefono = telefonoTextField.getText();
        String imagen = null;
        try {
            imagen = codificarImagenRegistroUsuario(fileImagen);
        } catch (Exception ignored) {

        }

        if (!nombre.isEmpty() && !apellido.isEmpty() && !saldo.isEmpty() && !deuda.isEmpty() && !telefono.isEmpty()) {
            try {
                int saldoInt = Integer.parseInt(saldo);
                int deudaInt = Integer.parseInt(deuda);

                empleadoEnDisplay.setNombre(nombre);
                empleadoEnDisplay.setApellido(apellido);
                empleadoEnDisplay.setSaldoDisponible(saldoInt);
                empleadoEnDisplay.setDeuda(deudaInt);
                empleadoEnDisplay.setTelefono(telefono);
                if (imagen != null) {
                    empleadoEnDisplay.setImagen(imagen);
                }

                String json = "";
                try {
                    ObjectMapper mapperEmpleado = new ObjectMapper();
                    json = mapperEmpleado.writeValueAsString(empleadoEnDisplay);
                    HttpResponse<JsonNode> apiResponse = null;
                    apiResponse = Unirest.put("http://localhost:8987/api/usuarios/actualizar/" + empleadoEnDisplay.getMail()).header("Content-Type", "application/json").body(json).asJson();
                    System.out.println("Put Hecho empleado");


                } catch (Exception e) {
                    System.out.println("Error actualizando put: " + e.getMessage());

                }

            } catch (Exception e) {

            }
        } else {

        }
        misEmpleados.clear();
        empleadosEmpresa();
        desplegarEmpleadoSeleccionado(null);
        fileImagen = null;
    }

    @FXML
    void onImagenDisplayMouseClick (MouseEvent event) {
        File file = tomarImagen(event);
        String base64 = codificarImagenRegistroUsuario(file);
        Image imagen = decodificarImagen(base64);
        imagenUsuarioDisplay.setImage(imagen);
    }

    public File tomarImagen (MouseEvent mouseEvent) {
        File file = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Elegir imagen centro");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            file = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
            if (file != null) {
                fileImagen = file;
            }
        } catch (Exception e) {
            System.out.println("No se selecciono ningun archivo");
        }
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
    void onAdministrarUsuariosLabelClick(MouseEvent event) {

    }

    private List<EmpleadoObject> misEmpleadosLike(String like) {
        List<EmpleadoObject> listaMisEmpleados = new ArrayList<>();
        EmpleadoObject empleadoObject;

        String empleado = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/usuarios//similarEmpleado/" + empresa.getMail() + "/" + like).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisEmpleados = mapper.readValue(json, new TypeReference<List<EmpleadoObject>>() {});

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisEmpleados;
    }

    @FXML
    void onBusquedaEmpleadoReleased(KeyEvent event) {
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {}

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {
                desplegarEmpleadoSeleccionado(empleadoObject);
            }
        };

        if (busquedaEmpleadoTextField.getText().isEmpty()) {
            todosLosEmpleadosGridPane = new GridPane();
            todosLosEmpleadosScroll.setContent(todosLosEmpleadosGridPane);
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : misEmpleados) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox usuarioEmpresaVbox = fxmlLoader.load();
                    UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                    usuarioEmpresaController.setearDatos(empleado, myListener);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todosLosEmpleadosGridPane.add(usuarioEmpresaVbox, column++, row);
                    GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);

            }
        } else {
            todosLosEmpleadosGridPane = new GridPane();
            todosLosEmpleadosScroll.setContent(todosLosEmpleadosGridPane);
            empleadosLike = misEmpleadosLike(busquedaEmpleadoTextField.getText());
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : empleadosLike) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox usuarioEmpresaVbox = fxmlLoader.load();
                    UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                    usuarioEmpresaController.setearDatos(empleado, myListener);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todosLosEmpleadosGridPane.add(usuarioEmpresaVbox, column++, row);
                    GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);
            }
        }
    }

    public void inicializoChoiceBox() {
        ObservableList<String> listaItems = FXCollections.observableArrayList();
        listaItems.add("TODOS");
        listaItems.add("DEUDORES");
        deudoresChoiceBox.setItems(listaItems);
        deudoresChoiceBox.setStyle("-fx-background-color : #FFFFFF;");
        DropShadow dropShadow = new DropShadow(10, Color.valueOf("#c7c9c9"));
        deudoresChoiceBox.setEffect(dropShadow);
        deudoresChoiceBox.setValue("TODOS");

        deudoresChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrarDeudores(observable.getValue());
            }
        });
    }

    public void filtrarDeudores (String filtro) {
        desplegarEmpleadoSeleccionado(null);
        this.myListener = new MyListener() {
            @Override
            public void onClickActividad(ActividadObject actividadObject) {

            }

            @Override
            public void onClickUsuario(EmpleadoObject empleadoObject) {
                desplegarEmpleadoSeleccionado(empleadoObject);
            }
        };
        System.out.println(filtro);

        if (filtro.equals("TODOS")) {
            todosLosEmpleadosGridPane = new GridPane();
            todosLosEmpleadosScroll.setContent(todosLosEmpleadosGridPane);
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : misEmpleados) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                    System.out.println("Carga FXMLLoader");

                    VBox usuarioEmpresaVbox = fxmlLoader.load();
                    UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                    usuarioEmpresaController.setearDatos(empleado, myListener);

                    if (column == 2) {
                        column = 0;
                        ++row;
                    }

                    todosLosEmpleadosGridPane.add(usuarioEmpresaVbox, column++, row);
                    GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                }
            } catch (Exception e){
                System.out.println("Error creando panel PARA FILTRO TODAS " + e);

            }
        } else {
            todosLosEmpleadosGridPane = new GridPane();
            todosLosEmpleadosScroll.setContent(todosLosEmpleadosGridPane);
            System.out.println("Empleados con deuda");
            int column = 0;
            int row = 1;
            try{
                for(EmpleadoObject empleado : misEmpleados) {
                    if (empleado.getDeuda() > 0) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/Admin/UsuarioEmpresa.fxml"));
                        System.out.println("Carga FXMLLoader");

                        VBox usuarioEmpresaVbox = fxmlLoader.load();
                        UsuarioEmpresaController usuarioEmpresaController = fxmlLoader.getController();

                        usuarioEmpresaController.setearDatos(empleado, myListener);

                        if (column == 2) {
                            column = 0;
                            ++row;
                        }

                        todosLosEmpleadosGridPane.add(usuarioEmpresaVbox, column++, row);
                        GridPane.setMargin(usuarioEmpresaVbox, new Insets(10));

                    }
                }
            } catch (Exception e){
                System.out.println("Error creando panel " + e);
            }
        }
    }



    @FXML
    void onEliminarUsuarioButtonClick(ActionEvent actionEvent) {
        String mailUsuario = emailUsuarioLabel.getText();

        List<ActividadObject> listaMisActividades = new ArrayList<>();
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcionUsuario/" + mailUsuario).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();
            System.out.println("Imprimo json");

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            //System.out.println(actividad);
            //System.out.println("Lista actividades mis actividades " + listaMisActividades);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        if (listaMisActividades.size() == 0) {
            try {
                HttpResponse<JsonNode> apiResponse = null;
                System.out.println(mailUsuario);
                apiResponse = Unirest.delete("http://localhost:8987/api/usuarios/deleteEmpleado/" + mailUsuario).asJson();
                System.out.println("Usuario borrado");
                misEmpleados.clear();
                empleadosEmpresa();
                desplegarEmpleadoSeleccionado(null);
                empleadoSeleccionadoVBox.setStyle("-fx-background-color : #1FDB5E;" +
                        "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

            } catch (Exception e) {
                System.out.println("Error borrando inscripcion: " + e);
            }
        } else {
            empleadoSeleccionadoVBox.setStyle("-fx-background-color : #E3350E;" +
                    "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("HOLA");
        inicializoChoiceBox();
    }

    public String getEmpresaLogInMail() {
        return empresaLogInMail;
    }

    public void setEmpresaLogInMail(String empresaLogInMail) {
        this.empresaLogInMail = empresaLogInMail;
    }
}
