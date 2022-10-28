package gym.Client.Controllers.Empresa.Pane;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class UsuarioEmpresaNuevoController {

    @FXML
    private Label apellidoUsuarioNuevo;

    @FXML
    private Label emailUsuarioNuevo;

    @FXML
    private HBox hboxUsuarioNuevo;

    @FXML
    private ImageView imagenUsuarioNuevo;

    @FXML
    private Label nombreUsuarioNuevo;

    private MyListener myListener;

    private EmpleadoObject empleado;

    private String [] colores = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void obtenerDatos(EmpleadoObject empleadoObject, MyListener myListener){
        this.myListener = myListener;
        this.empleado = empleadoObject;
        //Cambiar
        Image image = new Image(/*getClass().getResourceAsStream(actividadObject.getNombre()*/"/centro.jpg");
        imagenUsuarioNuevo.setImage(image);

        nombreUsuarioNuevo.setText(empleadoObject.getNombre());
        apellidoUsuarioNuevo.setText(empleadoObject.getApellido());
        emailUsuarioNuevo.setText(empleadoObject.getMail());
        hboxUsuarioNuevo.setStyle("-fx-background-color: #" + colores[(int) (Math.random()*(colores.length))] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");
    }

    @FXML
    void onUsuarioNuevoPanelClick(MouseEvent event) {
        myListener.onClickUsuario(empleado);

    }
}
