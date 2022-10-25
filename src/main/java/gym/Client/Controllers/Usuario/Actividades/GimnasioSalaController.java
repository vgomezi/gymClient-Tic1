package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.TipoActivitidad.GimnasioSala;
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
public class GimnasioSalaController implements Initializable {


    private List<GimnasioSala> listaGimnasioSalas;

    public GridPane anchorPaneScroll;

    private List<GimnasioSala> getDatos(){
        return null;
        //crear objeto falsos?
    }

    @FXML
    private Button volverBoton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*listaGimnasioSalas = new ArrayList<>();
        listaGimnasioSalas.addAll(getDatos());

        int row = 0;
        try{
            for (int i =0; i <listaGimnasioSalas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(GimnasioSalaController.class.getResourceAsStream("/formularios/OpcionesUsuario/Actividades/ActividadesPane.fxml"));

                ActividadesPaneController activityPane = fxmlLoader.getController();

                activityPane.datosGimnaisioSala(listaGimnasioSalas.get(i));

                anchorPaneScroll.add(anchorPane, 0, row++);

            }
        } catch (Exception e){

        }*/

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