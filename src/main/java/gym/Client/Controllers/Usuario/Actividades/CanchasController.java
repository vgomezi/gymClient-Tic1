package gym.Client.Controllers.Usuario.Actividades;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.TipoActivitidad.Canchas;
import gym.Client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CanchasController implements Initializable {

    @FXML
    public GridPane anchorPaneScroll;
    private List<ActividadObject> listaActividades = new ArrayList<>();

    @FXML
    private Button volverBoton;

    private List<ActividadObject> getDatos(){
        List<ActividadObject> listaActividades = new ArrayList<>();
        ActividadObject actividadObject;

        for(int i = 0; i<20; i++) {

        }
        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/allActividades/").asObject(String.class);
            String json = apiResponse.getBody().toString();

            ObjectMapper mapper = new JsonMapper().builder().addModule(new JavaTimeModule()).build();
            listaActividades = mapper.readValue(json, new TypeReference<List<ActividadObject>>() {});

            System.out.println(actividad);
            System.out.println("Lista actividades CanchasController " + listaActividades);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listaActividades;

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listaActividades = new ArrayList<>();
        listaActividades.addAll(getDatos());
        System.out.println("entro initialize canchas");

        int row = 0;
        try{
            for (int i =0; i <listaActividades.size(); i++){
                System.out.println("Entro try");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/formularios/OpcionesUsuario/Actividades/ActividadesPane.fxml"));
                System.out.println("Carga FXMLLoader");

                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println("Anchor pane ");
                ActividadesPaneController activityPane = fxmlLoader.getController();
                System.out.println("Activity pane controller");

                activityPane.datosCancha(listaActividades.get(i));
                System.out.println("Datos cancha activitypane");

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }
    }

    @FXML
    protected void onVolverButtonClick(ActionEvent event) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/formularios/OpcionesUsuario/TipoActividades.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Volver");
            stage.setScene(new Scene(root1));
            stage.show();

            /*Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            //stage.hide();
            //stage.close();*/

        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Error");
        }
    }


}