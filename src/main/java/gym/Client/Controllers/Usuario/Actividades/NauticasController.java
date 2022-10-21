package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.TipoActivitidad.Nauticas;
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
public class NauticasController implements Initializable {


    private List<Nauticas> listaNauticas;

    public GridPane anchorPaneScroll;

    private List<Nauticas> getDatos() {
        return null;
        //crear objeto falsos?
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaNauticas = new ArrayList<>();
        listaNauticas.addAll(getDatos());

        int row = 0;
        try {
            for (int i = 0; i < listaNauticas.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(GimnasioSalaController.class.getResourceAsStream("ActivityPane.fxml"));

                ActivityPaneController activityPane = fxmlLoader.getController();

                activityPane.datosNautica(listaNauticas.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e) {

        }
    }
}