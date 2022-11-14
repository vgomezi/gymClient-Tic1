package gym.Client.Controllers.Nuevos.Admin;

import gym.Client.Classes.EmpresaObject;
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

public class EmpresaTodaController {

    @FXML
    private Label emailEmpresaLabel;

    @FXML
    private VBox empresaVBox;

    @FXML
    private ImageView imagenEmpresa;

    @FXML
    private Label nombreEmpresaLabel;

    private MyListenerEmpresa myListenerEmpresa;

    private EmpresaObject empresa;

    public void setearDatos(EmpresaObject empresaObject, MyListenerEmpresa myListenerParam) {
        this.myListenerEmpresa = myListenerParam;
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
            imagenEmpresa.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagen/empresadefault.png");
            imagenEmpresa.setImage(imageView);
        }

        nombreEmpresaLabel.setText(empresaObject.getNombre().toUpperCase());
        emailEmpresaLabel.setText(empresaObject.getMail());
        empresaVBox.setStyle("-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onEmpresaTodaClick(MouseEvent event) {
        myListenerEmpresa.onClickEmpresa(empresa);

    }
}
