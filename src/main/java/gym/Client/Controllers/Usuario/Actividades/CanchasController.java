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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
            System.out.println(listaActividades);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaActividades = new ArrayList<>();
        listaActividades.addAll(getDatos());
        System.out.println("entro initialize canchas");

        int row = 0;
        try{
            for (int i =0; i <listaActividades.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(CanchasController.class.getResourceAsStream("ActividadesPane.fxml"));

                ActividadesPaneController activityPane = fxmlLoader.getController();

                activityPane.datosCancha(listaActividades.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){
            System.out.println("Error creando panel " + e);

        }


    }
}