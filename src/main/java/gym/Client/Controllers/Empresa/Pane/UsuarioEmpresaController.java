package gym.Client.Controllers.Empresa.Pane;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
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

public class UsuarioEmpresaController {

    @FXML
    private VBox usuarioEmpresaVbox;

    @FXML
    private Label apellidoUsuarioLabel;

    @FXML
    private Label emailUsuarioLabel;

    @FXML
    private ImageView imagenActividadTodaImage;

    @FXML
    private Label nombreUsuarioLabel;


    private MyListener myListener;

    private EmpleadoObject empleado;

    public void setearDatos(EmpleadoObject empleadoObject, MyListener myListener) {
        this.myListener = myListener;
        this.empleado = empleadoObject;

        if(empleadoObject.getImagen() != null) {
            byte[] imageDecoded = Base64.getDecoder().decode(empleadoObject.getImagen());
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
            Image imageView = new Image("/imagen/usuariodefault.png");
            imagenActividadTodaImage.setImage(imageView);
        }

        apellidoUsuarioLabel.setText(empleadoObject.getApellido().toUpperCase());
        nombreUsuarioLabel.setText(empleadoObject.getNombre().toUpperCase());
        emailUsuarioLabel.setText(empleadoObject.getMail());
        usuarioEmpresaVbox.setStyle("-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onUsuarioEmpresaPaneClick(MouseEvent event) {
        myListener.onClickUsuario(empleado);
    }
}
