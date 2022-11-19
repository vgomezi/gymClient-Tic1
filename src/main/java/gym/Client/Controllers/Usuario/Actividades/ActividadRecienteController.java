package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class ActividadRecienteController {

    @FXML
    private HBox hboxActividadReciente;

    @FXML
    private Label centroActividadReciente;

    @FXML
    private Label costoActividadReciente;

    @FXML
    private Label diaActividadReciente;

    @FXML
    private Label horaActividadReciente;

    @FXML
    private ImageView imagenActividadReciente;

    @FXML
    private Label nombreActividadReciente;

    private MyListener myListener;

    private ActividadObject actividad;

    private String [] colores = {"D96FE3", "B7E67E", "8AE3D4", "0DFF72", "B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(ActividadObject actividadObject, MyListener myListener){
        this.myListener = myListener;
        this.actividad = actividadObject;
        //Cambiar

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
            imagenActividadReciente.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagen/actividaddefault.png");
            imagenActividadReciente.setImage(imageView);
        }
        centroActividadReciente.setText(actividadObject.getCentroDeportivo().getNombre());
        costoActividadReciente.setText("$" + String.valueOf(actividadObject.getCosto()));
        diaActividadReciente.setText(actividadObject.getDia().toString());
        horaActividadReciente.setText(actividadObject.getHora().toString());
        nombreActividadReciente.setText(actividadObject.getNombre());
        hboxActividadReciente.setStyle("-fx-background-color: #" + colores[(int) (Math.random()*(colores.length))] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    public void onActividadRecientePanelClick(MouseEvent mouseEvent) {
        myListener.onClickActividad(actividad);
    }
}
