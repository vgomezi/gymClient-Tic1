package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.Canchas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class activitypaneController implements Initializable {


    @FXML
    private Label nombreLabel;

    @FXML
    private Label precioLabel;

    @FXML
    private Label capacidadLabel;

    @FXML
    private ImageView imageView;


    @FXML
    private Button reservarBoton;

    @FXML
    protected void onreservarButtonClick() {}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    private Canchas canchaSeleccionada;

    public void datosCancha(Canchas cancha){
        canchaSeleccionada = cancha;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(cancha.getNombre());

        precioLabel.setText(cancha.getPrecio().toString());

        capacidadLabel.setText(cancha.getCapacidad().toString());


    }


}