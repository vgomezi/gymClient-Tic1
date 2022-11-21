package gym.Client.Controllers.Nuevos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class DeudaUsuEmpPaneController {


    @FXML
    private VBox actUsuVbox;

    @FXML
    private Label actividadesLabel;

    @FXML
    private Label deudaLabel;

    @FXML
    private ScrollPane deudaUsuEmpScroll;

    @FXML
    private VBox deudaUsuEmpVbox;

    @FXML
    private Label mailLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label vacio1;

    @FXML
    private Label vacio2;

    private MyListener myListener;

    private EmpleadoObject empleado;

    private ActividadObject actividad;

    private List<ActividadObject> misActividades = new ArrayList<>();



    public void setearDatos(EmpleadoObject empleadoObject, MyListener myListener) {
        this.myListener = myListener;
        this.empleado = empleadoObject;

        nombreLabel.setText("USUARIO:  " + empleadoObject.getNombre() + " " + empleadoObject.getApellido());
        mailLabel.setText("MAIL:  " + empleadoObject.getMail());
        deudaLabel.setText("DEUDA:  " + String.valueOf(empleadoObject.getDeuda()));
        actividadesLabel.setText("ACTIVIDADES: ");

    }

    private List<ActividadObject> todasMisActividades() {
        List<ActividadObject> listaMisActividades = new ArrayList<>();

        try {
            HttpResponse<String> apiResponse = null;
            apiResponse = Unirest.get("http://localhost:8987/inscripciones/inscripcionUsuario/" + empleado.getMail()).header("Content-Type", "application/json").asObject(String.class);
            String json = apiResponse.getBody();


            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaMisActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});


        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaMisActividades;
    }

    public void actividadesEmpleado() {
        GridPane deudaUsuEmpGridPane = new GridPane();
        deudaUsuEmpScroll.setContent(deudaUsuEmpGridPane);
        misActividades.clear();
        misActividades.addAll(todasMisActividades());

        int column = 0;
        int row = 1;
        try{
            for(ActividadObject actividadObject : misActividades) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gym/Client/nuevo/ActUsuEmpPane.fxml"));

                VBox ActUsuVbox = fxmlLoader.load();
                ActUsuEmpPaneController actUsuEmpPaneController = fxmlLoader.getController();

                actUsuEmpPaneController.setearDatos(actividadObject);

                row++;

                deudaUsuEmpGridPane.add(ActUsuVbox, column, row);
                GridPane.setMargin(ActUsuVbox, new Insets(5));

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);
        }
    }

}
