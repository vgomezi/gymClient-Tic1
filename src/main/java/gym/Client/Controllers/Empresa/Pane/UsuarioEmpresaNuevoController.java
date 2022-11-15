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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class UsuarioEmpresaNuevoController {

    @FXML
    private HBox usuarioNuevoHbox;

    @FXML
    private Label apellidoUsuarioNuevo;

    @FXML
    private Label emailUsuarioNuevo;

    @FXML
    private ImageView imagenUsuarioNuevo;

    @FXML
    private Label nombreUsuarioNuevo;

    private MyListener myListener;

    private EmpleadoObject empleado;

    private String [] colores = {"D96FE3", "B7E67E", "8AE3D4", "0DFF72", "B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(EmpleadoObject empleadoObject, MyListener myListener){
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
            imagenUsuarioNuevo.setImage(toAdd);
        } else {
            Image imageView = new Image("/imagen/usuariodefault.png");
            imagenUsuarioNuevo.setImage(imageView);
        }

        nombreUsuarioNuevo.setText(empleadoObject.getNombre());
        apellidoUsuarioNuevo.setText(empleadoObject.getApellido());
        emailUsuarioNuevo.setText(empleadoObject.getMail());
        usuarioNuevoHbox.setStyle("-fx-background-color: #" + colores[(int) (Math.random()*(colores.length))] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onUsuarioNuevoPanelClick(MouseEvent event) {
        myListener.onClickUsuario(empleado);

    }
}
