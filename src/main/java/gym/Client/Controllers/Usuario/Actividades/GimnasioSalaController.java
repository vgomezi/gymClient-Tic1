package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.TipoActivitidad.GimnasioSala;
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
public class GimnasioSalaController implements Initializable {


    private List<GimnasioSala> listaGimnasioSalas;

    public GridPane anchorPaneScroll;

    private List<GimnasioSala> getDatos(){
        return null;
        //crear objeto falsos?
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaGimnasioSalas = new ArrayList<>();
        listaGimnasioSalas.addAll(getDatos());

        int row = 0;
        try{
            for (int i =0; i <listaGimnasioSalas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(GimnasioSalaController.class.getResourceAsStream("ActividadesPane.fxml"));

                ActividadesPaneController activityPane = fxmlLoader.getController();

                activityPane.datosGimnaisioSala(listaGimnasioSalas.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){

        }


    }
}