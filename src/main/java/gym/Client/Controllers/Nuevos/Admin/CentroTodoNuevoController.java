package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Classes.CentroDeportivoObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class CentroTodoNuevoController {

    @FXML
    private HBox centroNuevoHbox;

    @FXML
    private Label emailCentroNuevo;

    @FXML
    private ImageView imagenCentroNuevo;

    @FXML
    private Label nombreCentroNuevo;

    private CentroDeportivoObject centroDeportivo;

    private MyListenerCentro myListenerCentro;

    private String [] colores = {"D96FE3", "B7E67E", "8AE3D4", "0DFF72", "B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(CentroDeportivoObject centroDeportivoObject, MyListenerCentro myListener){
        this.myListenerCentro = myListener;
        this.centroDeportivo = centroDeportivoObject;

        if(centroDeportivoObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(centroDeportivoObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenCentroNuevo.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagenes/imagenesDefault/centrodefault.png");
            imagenCentroNuevo.setImage(imageView);
        }

        nombreCentroNuevo.setText(centroDeportivoObject.getNombre());
        emailCentroNuevo.setText(centroDeportivoObject.getMail());
        centroNuevoHbox.setStyle("-fx-background-color: #" + colores[(int) (Math.random()*(colores.length))] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onUsuarioNuevoPanelClick(MouseEvent event) {
        myListenerCentro.onClickCentro(centroDeportivo);
    }

}