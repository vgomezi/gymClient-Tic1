package gym.Client.Controllers.Nuevos;

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

    private PagoUsuEmpObject pago;

    @FXML
    void onDeudaPaneClick(MouseEvent mouseEvent) {

    }

    public void setearDatos(PagoUsuEmpObject pagoUsuEmpObject, MyListener myListener) {
        this.myListener = myListener;
        this.pago = pagoUsuEmpObject;

        nombreLabel.setText("USUARIO" + String.valueOf(pagoUsuEmpObject.getEmpleado().getNombre()));
        mailLabel.setText("MAIL: " + String.valueOf(pagoUsuEmpObject.getEmpleadoMailPago()));
        deudaLabel.setText("DEUDA: " + String.valueOf(pagoUsuEmpObject.getMonto()));


    }
}
