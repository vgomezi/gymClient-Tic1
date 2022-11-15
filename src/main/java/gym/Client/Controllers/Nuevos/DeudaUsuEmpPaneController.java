package gym.Client.Controllers.Nuevos;

import gym.Client.Classes.EmpleadoObject;
import gym.Client.Classes.PagoEmpCentObject;
import gym.Client.Classes.PagoUsuEmpObject;
import gym.Client.Controllers.Usuario.Actividades.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DeudaUsuEmpPaneController {


    @FXML
    private Label deudaLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label mailLabel;

    private MyListener myListener;

    private EmpleadoObject empleado;

    @FXML
    void onDeudaPaneClick(MouseEvent mouseEvent) {

    }

    public void setearDatos(EmpleadoObject empleadoObject, MyListener myListener) {
        this.myListener = myListener;
        this.empleado = empleadoObject;

        nombreLabel.setText("USUARIO:  " + empleadoObject.getNombre());
        mailLabel.setText("MAIL:  " + empleadoObject.getMail());
        deudaLabel.setText("DEUDA:  " + String.valueOf(empleadoObject.getDeuda()));


    }
}
