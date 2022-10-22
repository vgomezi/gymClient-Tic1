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
public class ActividadesPaneController implements Initializable {


    @FXML
    private Label nombreLabel;

    @FXML
    private Label precioLabel;

    @FXML
    private Label capacidadLabel;

    @FXML
    private Label descripcionLabel;

    @FXML
    private Label diaLabel;

    @FXML
    private Label horaLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Button reservarBoton;

    @FXML
    protected void onReservarButtonClick() {
        //hacer que reserve la actividad el usuairo
    }


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

        descripcionLabel.setText(cancha.getDescripcion());

        diaLabel.setText("Día de actividad: " + cancha.getDia());

        horaLabel.setText("Hora de actividad: " + cancha.getHora());
    }

    private Nauticas nauticaSeleccionada;

    public void datosNautica(Nauticas nautica){
        nauticaSeleccionada = nautica;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(nautica.getNombre());

        descripcionLabel.setText(nautica.getDescripcion());

        precioLabel.setText(nautica.getPrecio().toString());

        capacidadLabel.setText(nautica.getCapacidad().toString());

        diaLabel.setText("Día de actividad: " + nautica.getDia());

        horaLabel.setText("Hora de actividad: " + nautica.getHora());
    }

    private GimnasioSala gimnasioSalaSeleccionada;

    public void datosGimnaisioSala(GimnasioSala gimnasioSala){
        gimnasioSalaSeleccionada = gimnasioSala;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(gimnasioSala.getNombre());

        descripcionLabel.setText(gimnasioSala.getDescripcion());

        precioLabel.setText(gimnasioSala.getPrecio().toString());

        capacidadLabel.setText(gimnasioSala.getCapacidad().toString());

        diaLabel.setText("Día de actividad: " + gimnasioSala.getDia());

        horaLabel.setText("Hora de actividad: " + gimnasioSala.getHora());
    }


}