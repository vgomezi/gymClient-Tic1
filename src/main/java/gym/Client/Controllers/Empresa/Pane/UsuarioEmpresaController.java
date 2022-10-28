package gym.Client.Controllers.Empresa.Pane;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UsuarioEmpresaController {


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

        Image imageView = new Image("/error.jpg");
        imagenActividadTodaImage.setImage(imageView);

        apellidoUsuarioLabel.setText(empleadoObject.getApellido());
        nombreUsuarioLabel.setText(empleadoObject.getNombre());
        emailUsuarioLabel.setText(empleadoObject.getMail());
    }

    @FXML
    void onUsuarioEmpresaPaneClick(MouseEvent event) {
        myListener.onClickUsuario(empleado);
    }
}
