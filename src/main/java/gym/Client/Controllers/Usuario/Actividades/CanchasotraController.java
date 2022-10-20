package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.Canchas;
import gym.Client.GymClientApplication;
import gym.Client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CanchasotraController implements Initializable {


    private List<Canchas> listaCanchas;

    public GridPane anchorPaneScrolll;

    private List<Canchas> getDatos(){
        return null;
        //crear objeto falsos?
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaCanchas = new ArrayList<>();
        listaCanchas.addAll(getDatos());

        int row = 0;
        try{
            for (int i =0; i <listaCanchas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(CanchasotraController.class.getResourceAsStream("activitypane.fxml"));

                activitypaneController activityPane = fxmlLoader.getController();

                activityPane.datosCancha(listaCanchas.get(i));

                anchorPaneScrolll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){

        }


    }
}