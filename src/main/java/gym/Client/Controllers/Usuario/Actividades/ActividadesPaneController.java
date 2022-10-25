package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
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
public class ActividadesPaneController {


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

    private ActividadObject actividad;

    public void datosCancha(ActividadObject actividadObject){
        this.actividad = actividadObject;

        Image image = new Image("/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(actividad.getNombre());

        precioLabel.setText(String.valueOf(actividad.getCosto()));

        capacidadLabel.setText(String.valueOf(actividad.getCupos()));

        descripcionLabel.setText(actividad.getDescripcion());

        diaLabel.setText("Día de actividad: " + actividad.getDia().toString());

        horaLabel.setText("Hora de actividad: " + actividad.getHora().toString());
    }

    private ActividadObject nauticaSeleccionada;

    public void datosNautica(ActividadObject actividadObject){
        this.nauticaSeleccionada = actividadObject;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(actividadObject.getNombre());

        descripcionLabel.setText(actividadObject.getDescripcion());

        precioLabel.setText(String.valueOf(actividadObject.getCosto()));

        capacidadLabel.setText(String.valueOf(actividadObject.getCupos()));

        diaLabel.setText("Día de actividad: " + actividadObject.getDia().toString());

        horaLabel.setText("Hora de actividad: " + actividadObject.getHora().toString());
    }

    private ActividadObject gimnasioSalaSeleccionada;

    public void datosGimnaisioSala(ActividadObject actividadObject){
        this.gimnasioSalaSeleccionada = actividadObject;

        Image image = new Image("@/centro.jpg");
        //como converitr imagen y pasarlo desde base de datos

        imageView.setImage(image);

        nombreLabel.setText(actividadObject.getNombre());

        descripcionLabel.setText(actividadObject.getDescripcion());

        precioLabel.setText(String.valueOf(actividadObject.getCosto()));

        capacidadLabel.setText(String.valueOf(actividadObject.getCupos()));

        diaLabel.setText("Día de actividad: " + actividadObject.getDia());

        horaLabel.setText("Hora de actividad: " + actividadObject.getHora());
    }


}