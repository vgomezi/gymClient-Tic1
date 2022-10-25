package gym.Client.Controllers.Usuario;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.TipoActivitidad.Canchas;
import gym.Client.Controllers.Usuario.Actividades.ActividadesPaneController;
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
public class BajarseUsuarioActividadController implements Initializable {


    private List<ActividadObject> listaActividades;

    //cambiar a lista de actividades reservadas

    public GridPane anchorPaneScroll;

    private List<ActividadObject> getDatos(){
        return null;
        //crear objeto falsos?
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaActividades = new ArrayList<>();
        listaActividades.addAll(getDatos());

        int row = 0;
        try{
            for (int i =0; i <listaActividades.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(BajarseUsuarioActividadController.class.getResourceAsStream("ReservadasPane.fxml"));

                ActividadesPaneController activityPane = fxmlLoader.getController();

                activityPane.datosCancha(listaActividades.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){

        }


    }
}