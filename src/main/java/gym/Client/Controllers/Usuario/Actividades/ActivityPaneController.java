package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.TipoActivitidad.Canchas;
import gym.Client.Classes.TipoActivitidad.GimnasioSala;
import gym.Client.Classes.TipoActivitidad.Nauticas;
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
public class ActivityPaneController implements Initializable {


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

    private Nauticas nauticaSeleccionada;

    public void datosNautica(Nauticas nautica){
        nauticaSeleccionada = nautica;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(nautica.getNombre());

        precioLabel.setText(nautica.getPrecio().toString());

        capacidadLabel.setText(nautica.getCapacidad().toString());
    }

    private GimnasioSala gimnasioSalaSeleccionada;

    public void datosGimnaisioSala(GimnasioSala gimnasioSala){
        gimnasioSalaSeleccionada = gimnasioSala;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(gimnasioSala.getNombre());

        precioLabel.setText(gimnasioSala.getPrecio().toString());

        capacidadLabel.setText(gimnasioSala.getCapacidad().toString());
    }


}