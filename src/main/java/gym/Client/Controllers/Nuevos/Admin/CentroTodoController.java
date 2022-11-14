package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Classes.CentroDeportivoObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class CentroTodoController {

    @FXML
    private VBox centroVBox;

    @FXML
    private Label emailCentroLabel;

    @FXML
    private ImageView imagenCentro;

    @FXML
    private Label nombreCentroLabel;

    private MyListenerCentro myListenerCentro;

    private CentroDeportivoObject centro;

    public void setearDatos(CentroDeportivoObject centroDeportivoObject, MyListenerCentro myListenerParam) {
        this.myListenerCentro = myListenerParam;
        this.centro = centroDeportivoObject;

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
            imagenCentro.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagen/centrodefault.png");
            imagenCentro.setImage(imageView);
        }

        nombreCentroLabel.setText(centroDeportivoObject.getNombre().toUpperCase());
        emailCentroLabel.setText(centroDeportivoObject.getMail());
        centroVBox.setStyle("-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onCentroTodoClick(MouseEvent event) {
        myListenerCentro.onClickCentro(centro);

    }


}
