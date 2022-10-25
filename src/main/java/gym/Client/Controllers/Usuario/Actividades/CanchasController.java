package gym.Client.Controllers.Usuario.Actividades;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import gym.Client.Classes.TipoActivitidad.Canchas;
import gym.Client.Main;
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


    private List<Canchas> listaCanchas;

    public GridPane anchorPaneScroll;

    private List<Canchas> getDatos(){
        String actividad = "";
        try {
            HttpResponse<String> apiResponse = null;

            apiResponse = Unirest.get("http://localhost:8987/api/actividades/allActividades/").asObject(String.class);
            actividad = apiResponse.getBody().toString();
            System.out.println(actividad);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaCanchas = new ArrayList<>();
        listaCanchas.addAll(getDatos());
        System.out.println("entro initialize canchas");

        int row = 0;
        try{
            for (int i =0; i <listaCanchas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(CanchasController.class.getResourceAsStream("ActividadesPane.fxml"));

                ActividadesPaneController activityPane = fxmlLoader.getController();

                activityPane.datosCancha(listaCanchas.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){

        }


    }
}