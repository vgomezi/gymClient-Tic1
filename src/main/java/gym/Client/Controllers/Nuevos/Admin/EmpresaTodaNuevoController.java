package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Classes.EmpresaObject;
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

public class EmpresaTodaNuevoController {

    @FXML
    private Label emailEmpresaNuevo;

    @FXML
    private HBox empresaNuevoHbox;

    @FXML
    private ImageView imagenEmpresaNuevo;

    @FXML
    private Label nombreEmpresaNuevo;

    private EmpresaObject empresa;

    private MyListenerEmpresa myListenerEmpresa;

    private String [] colores = {"D96FE3", "B7E67E", "8AE3D4", "0DFF72", "B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(EmpresaObject empresaObject, MyListenerEmpresa myListener){
        this.myListenerEmpresa = myListener;
        this.empresa = empresaObject;

        if(empresaObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(empresaObject.getImagen());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageDecoded);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Image toAdd = SwingFXUtils.toFXImage(bImage, null);
            imagenEmpresaNuevo.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagenes/imagenesDefault/empresadefault.png");
            imagenEmpresaNuevo.setImage(imageView);
        }

        nombreEmpresaNuevo.setText(empresaObject.getNombre());
        emailEmpresaNuevo.setText(empresaObject.getMail());
        empresaNuevoHbox.setStyle("-fx-background-color: #" + colores[(int) (Math.random()*(colores.length))] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onUsuarioNuevoPanelClick(MouseEvent event) {
        myListenerEmpresa.onClickEmpresa(empresa);
    }
}
