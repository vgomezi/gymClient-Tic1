package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


import java.net.URL;
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

    private String [] colores = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(ActividadObject actividadObject, MyListener myListener){
        this.myListener = myListener;
        this.actividad = actividadObject;
        //Cambiar
        Image image = new Image(/*getClass().getResourceAsStream(actividadObject.getNombre()*/"/centro.jpg");
        imagenActividadReciente.setImage(image);

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
