package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActividadTodaController {

    @FXML
    private Label costoActividadTodaLabel;

    @FXML
    private Label cuposActividadTodaLabel;

    @FXML
    private Label diaActividadTodaLabel;

    @FXML
    private Label horaActividadTodaLabel;

    @FXML
    private ImageView imagenActividadTodaImage;

    @FXML
    private Label nombreActividadTodaLabel;

    @FXML
    private Label reservableActividadTodaLabel;

    @FXML
    private Label tipoActividadTodaLabel;

    public void setearDatos(ActividadObject actividadObject) {
        Image imageView = new Image("/centro.jpg");
        imagenActividadTodaImage.setImage(imageView);

        costoActividadTodaLabel.setText(String.valueOf(actividadObject.getCosto()));
        cuposActividadTodaLabel.setText(String.valueOf(actividadObject.getCupos()));
        diaActividadTodaLabel.setText(actividadObject.getDia().toString());
        horaActividadTodaLabel.setText(actividadObject.getHora().toString());
        nombreActividadTodaLabel.setText(actividadObject.getNombre());
        if (actividadObject.isReservable()) {
            reservableActividadTodaLabel.setText("CON RESERVA");
        } else {
            reservableActividadTodaLabel.setText("SIN RESERVA");
        }
        //tipoActividadTodaLabel.setText(actividadObject.getTipo().getTipo());
    }
}
