package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

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

    private ActividadObject actividad;

    private MyListener myListener;

    public void setearDatos(ActividadObject actividadObject, MyListener myListener) {
        this.myListener = myListener;
        this.actividad = actividadObject;

        if(actividadObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(actividadObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenActividadTodaImage.setImage(toAdd);
        } else {
            Image imageView = new Image("/centro.jpg");
            imagenActividadTodaImage.setImage(imageView);
        }

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
        tipoActividadTodaLabel.setText(actividadObject.getTipo().getTipo());
    }

    public void onActividadTodaPanelClick(MouseEvent mouseEvent) {
        myListener.onClickActividad(actividad);
    }
}
